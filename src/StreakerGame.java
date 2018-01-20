import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class StreakerGame extends Application {
    private static final int ScreenHeight = 600;
    private static final int movingSpeed = 8;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage theStage) {
        theStage.setTitle( "Streaker" );
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene(theScene);
        Sprite background = new Sprite();
        Sprite background2 = new Sprite();
        background.setImage("../assets/images/background.png");
        background.setPosition(0, ScreenHeight - background.getHeight());
        background2.setImage("../assets/images/background.png");
        background2.setPosition(0, ScreenHeight -(2 * background.getHeight()));
        Canvas canvas = new Canvas(background.getWidth(), ScreenHeight);
        root.getChildren().add(canvas);
        ArrayList<String> input = new ArrayList<String>();
        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>() {
                public void handle(KeyEvent e) {
                    String code = e.getCode().toString();
                    if (!input.contains(code)) {
                        input.add(code);
                    }
                }
            }
        );
        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>() {
                public void handle(KeyEvent e) {
                    String code = e.getCode().toString();
                    input.remove(code);
                }
            }
        );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        AnimatedImage character = new AnimatedImage();
        Image[] imageArray = new Image[4];
        imageArray[0] = new Image("../assets/images/guyForward.png");
        imageArray[1] = new Image("../assets/images/guyLeft.png");
        imageArray[2] = new Image("../assets/images/guyForward.png");
        imageArray[3] = new Image("../assets/images/guyRight.png");
        character.setFrame(imageArray);
        character.duration = 0.150;
        character.setPosition((background.getWidth() / 2) - 40, ScreenHeight / 2);
        ArrayList<Sprite> coins = new ArrayList<Sprite>();
        for(int i = 0; i < 4; i++) {
            Sprite coin = new Sprite();
            coin.setImage("../assets/images/coin.png");
            double x = background.getWidth() * Math.random();
            double y = - background.getHeight() * Math.random();
            coin.setPosition(x, y);
            coins.add(coin);
         }
        IntValue collected = new IntValue(0);
        LongValue lastNanoTime = new LongValue( System.nanoTime() );
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;
                double nanot = currentNanoTime - startNanoTime;
                double t = nanot / 1000000000.0;
                if (background.getY() > ScreenHeight) {
                    background.setPosition(0, background2.getY() - background.getHeight());
                }
                if (background2.getY() > ScreenHeight) {
                    background2.setPosition(0, background.getY() - background2.getHeight());
                }
                background.setSpeed(0, movingSpeed);
                background.updateS();
                background.render(gc);
                background2.setSpeed(0, movingSpeed);
                background2.updateS();
                background2.render(gc);
                character.setVelocity(0,0);
                if (input.contains("LEFT")) {
                    character.addVelocity(-550,0);
                }
                if (input.contains("RIGHT")) {
                    character.addVelocity(550,0);
                }
                if (input.contains("UP")) {
                    character.addVelocity(0,-550);
                }
                if (input.contains("DOWN")) {
                    character.addVelocity(0,550);
                }
                if (character.getX() < 0) {
                    character.setPosition(0, character.getY());
                }
                if (character.getX() > (background.getWidth() - character.getWidth())) {
                    character.setPosition((background.getWidth() - character.getWidth()), character.getY());
                }
                if (character.getY() < 0) {
                    character.setPosition(character.getX(), 0);
                }
                if (character.getY() > (ScreenHeight - character.getHeight())) {
                    character.setPosition(character.getX(), ScreenHeight - character.getHeight());
                }
               character.updateV(elapsedTime);
               character.render(gc, t);
               Iterator<Sprite> coinsIter = coins.iterator();
               while (coinsIter.hasNext()) {
                   Sprite coin = coinsIter.next();
                   if (coin.getY() > ScreenHeight) {
                       double x = background.getWidth() * Math.random();
                       double y = - background.getHeight() * Math.random();
                       coin.setPosition(x, y);
                   }
                   if (character.intersects(coin.getBoundary())) {
                       double x = background.getWidth() * Math.random();
                       double y = - background.getHeight() * Math.random();
                       coin.setPosition(x, y);
                       collected.value += 10;
                   }
               }
               for (Sprite coin : coins) {
                   coin.setSpeed(0, movingSpeed);
                   coin.updateS();
                   coin.render(gc);
               }
               String hms = String.format("%02d:%02d:%02d", TimeUnit.NANOSECONDS.toHours((long)nanot),
               TimeUnit.NANOSECONDS.toMinutes((long)nanot) - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours((long)nanot)),
               TimeUnit.NANOSECONDS.toSeconds((long)nanot) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes((long)nanot)));
               gc.fillText( hms, background.getWidth() - 100, 20 );
               gc.strokeText( hms , background.getWidth() - 100, 20 );

               String coinStr = "Coins: " + collected.value;
               gc.fillText( coinStr, background.getWidth() - 100, 40 );
               gc.strokeText( coinStr , background.getWidth() - 100, 40 );
            }
        }.start();
        theStage.show();
    }
}
