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
    private static final int SCREEN_HEIGHT = 600;
    private static final int MOVING_SPEED = 8;

    private Group root;
    private Scene scene;
    private BackgroundItem background;
    private Canvas canvas;
    ArrayList<String> input;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        setupGameState(stage);
        setOnKeyPress();
        setOnKeyRelease();
        // create character
        GraphicsContext gc = canvas.getGraphicsContext2D();
        AnimatedImage character = new AnimatedImage();
        Image[] imageArray = new Image[4];
        imageArray[0] = new Image("../assets/images/guyForward.png");
        imageArray[1] = new Image("../assets/images/guyLeft.png");
        imageArray[2] = new Image("../assets/images/guyForward.png");
        imageArray[3] = new Image("../assets/images/guyRight.png");
        character.setFrame(imageArray);
        character.duration = 0.150; // change to global variable
        character.setPosition((background.getWidth() / 2) - 40, SCREEN_HEIGHT / 2);
        //
        // create coins
        ArrayList<Sprite> coins = new ArrayList<Sprite>();
        for(int i = 0; i < 4; i++) {
            Sprite coin = new Sprite();
            coin.setImage("../assets/images/coin.png");
            double x = background.getWidth() * Math.random();
            double y = - background.getHeight() * Math.random();
            coin.setPosition(x, y);
            coins.add(coin);
        }
        //
        // initialize time and score
        IntValue collected = new IntValue(0);
        LongValue lastNanoTime = new LongValue( System.nanoTime() );
        final long startNanoTime = System.nanoTime();
        //
        // run game
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;
                double nanot = currentNanoTime - startNanoTime;
                double t = nanot / 1000000000.0;
                background.loop();
                background.setBackgroundSpeed(gc);
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
                if (character.getY() > (SCREEN_HEIGHT - character.getHeight())) {
                    character.setPosition(character.getX(), SCREEN_HEIGHT - character.getHeight());
                }
               character.updateV(elapsedTime);
               character.render(gc, t);
               Iterator<Sprite> coinsIter = coins.iterator();
               while (coinsIter.hasNext()) {
                   Sprite coin = coinsIter.next();
                   if (coin.getY() > SCREEN_HEIGHT) {
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
                   coin.setSpeed(0, MOVING_SPEED);
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
        stage.show();
    }
    public void setOnKeyPress() {
        scene.setOnKeyPressed(
            new EventHandler<KeyEvent>() {
                public void handle(KeyEvent e) {
                    String code = e.getCode().toString();
                    if (!input.contains(code)) {
                        input.add(code);
                    }
                }
            }
        );
    }
    public void setOnKeyRelease() {
        scene.setOnKeyReleased(
            new EventHandler<KeyEvent>() {
                public void handle(KeyEvent e) {
                    String code = e.getCode().toString();
                    input.remove(code);
                }
            }
        );
    }
    public void setupGameState(Stage stage) {
        stage.setTitle("Streaker");
        root = new Group();
        scene = new Scene(root);
        stage.setScene(scene);
        background = new BackgroundItem(SCREEN_HEIGHT, MOVING_SPEED);
        canvas = new Canvas(background.getWidth(), SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        input = new ArrayList<String>();
    }
}
