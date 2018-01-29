import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.*;

public class StreakerGame extends Application {
    private static final int SCREEN_HEIGHT = 600;
    private static final double FRAME_DURATION = 0.150;
    private static final int NUM_COINS = 4;
    private static final int CHARACTER_VELOCITY = 550;
    private static final double _PRECISION = 1000000000.0;
    private static final int STADIUM_BORDER = 80;
    private static final int STARTING_SPEED = 7;
    private long startNanoTime;
    private LongValue lastNanoTime;
    private IntValue collected;
    private IntValue movingSpeed = new IntValue(STARTING_SPEED);

    private Group root;
    private Scene scene;
    private BackgroundItem background;
    private Canvas canvas;
    private ArrayList<String> input;
    private GraphicsContext gc;
    private ArrayList<Coin> coins;
    private AnimatedImage character;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {

        setupGameState(stage);
        setOnKeyPress();
        setOnKeyRelease();
        character = new Streaker(FRAME_DURATION, SCREEN_HEIGHT, background);
        createCoins();
        setInitialScore();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / _PRECISION;
                lastNanoTime.value = currentNanoTime;
                double nanot = currentNanoTime - startNanoTime;
                double t = nanot / _PRECISION;
                background.loop();
                background.setBackgroundSpeed(gc);
                handleVelocity();
                handleCharacterPosition();
                character.updateVelocity(elapsedTime);
                character.render(gc, t);
                for (Coin coin : coins) {
                    if (coin.getY() > SCREEN_HEIGHT) {
                        coin.resetPosition();
                    }
                    if (character.intersects(coin.getBoundary())) {
                        coin.resetPosition();
                        collected.value += 10;
                    }
                    coin.handleSpeed(gc);
                }
                showTime(nanot);
                showCoins();
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
        background = new BackgroundItem(SCREEN_HEIGHT, movingSpeed.value);
        canvas = new Canvas(background.getWidth(), SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        input = new ArrayList<String>();
        gc = canvas.getGraphicsContext2D();
    }
    public void createCoins() {
        coins = new ArrayList<Coin>();
        for(int i = 0; i < NUM_COINS; i++) {
            Coin coin = new Coin(background, movingSpeed.value);
            coins.add(coin);
        }
    }
    public void setInitialScore() {
        collected = new IntValue(0);
        lastNanoTime = new LongValue(System.nanoTime());
        startNanoTime = System.nanoTime();
    }
    public void handleVelocity() {
        character.setVelocity(0,0);
        if (input.contains("LEFT")) {
            character.addVelocity(-CHARACTER_VELOCITY,0);
        }
        if (input.contains("RIGHT")) {
            character.addVelocity(CHARACTER_VELOCITY,0);
        }
        if (input.contains("UP")) {
            character.addVelocity(0,-CHARACTER_VELOCITY);
        }
        if (input.contains("DOWN")) {
            character.addVelocity(0,CHARACTER_VELOCITY);
        }
    }
    public void handleCharacterPosition() {
        if (character.getX() < STADIUM_BORDER) {
            character.setPosition(STADIUM_BORDER, character.getY());
        }
        if (character.getX() > background.getWidth() - STADIUM_BORDER - character.getWidth()) {
            character.setPosition(background.getWidth() - STADIUM_BORDER - character.getWidth(), character.getY());
        }
        if (character.getY() < 0) {
            character.setPosition(character.getX(), 0);
        }
        if (character.getY() > SCREEN_HEIGHT - character.getHeight()) {
            character.setPosition(character.getX(), SCREEN_HEIGHT - character.getHeight());
        }
    }
    public void showTime(double nanot) {
        String hms = String.format("%02d:%02d:%02d ", TimeUnit.NANOSECONDS.toHours((long)nanot),
        TimeUnit.NANOSECONDS.toMinutes((long)nanot) - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours((long)nanot)),
        TimeUnit.NANOSECONDS.toSeconds((long)nanot) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes((long)nanot))); 
        gc.fillText(hms, background.getWidth() - 150, 40);
        gc.strokeText(hms , background.getWidth() - 150, 40);
    }
    public void showCoins() {
        String coinStr = "ButtCoin: $" + collected.value;
        gc.fillText(coinStr, background.getWidth() - 150, 70);
        gc.setStroke(Color.WHITE);
        gc.setFont(new Font("Serif", 20));
        gc.strokeText(coinStr , background.getWidth() - 150, 70);
    }
}
