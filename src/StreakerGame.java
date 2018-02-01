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

    private long startNanoTime;
    private LongValue lastNanoTime;
    private IntValue collected;
    private Group root;
    private Scene scene;
    private BackgroundItem background;
    private Canvas canvas;
    private ArrayList<String> input;
    private GraphicsContext gc;
    private ArrayList<Coin> coins;
    private ArrayList<Tunnel> tunnels;
    private ArrayList<Guard> guards;
    private Streaker character;
    private GraphicsController graphicsController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        setupGameState(stage);
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / Constants._PRECISION;
                lastNanoTime.value = currentNanoTime;
                double nanot = currentNanoTime - startNanoTime;
                double t = nanot / Constants._PRECISION;
                background.loop();
                background.setBackgroundSpeed(gc);
                character.handleVelocity(input);
                character.handleCharacterPosition();
                character.updateVelocity(elapsedTime);
                character.render(gc, t);
                handleCoinIntersects();
                handleTunnelLoop(t);
                handleGuardSpeed(t);
                graphicsController.showTime(nanot);
                showCoins();
            }
        }.start();
        stage.show();
    }

    private void setOnKeyPress() {
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

    private void setOnKeyRelease() {
        scene.setOnKeyReleased(
            new EventHandler<KeyEvent>() {
                public void handle(KeyEvent e) {
                    String code = e.getCode().toString();
                    input.remove(code);
                }
            }
        );
    }

    private void setupGameState(Stage stage) {
        stage.setTitle("Streaker");
        root = new Group();
        scene = new Scene(root);
        stage.setScene(scene);
        background = new BackgroundItem();
        canvas = new Canvas(background.getWidth(), Constants.SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        input = new ArrayList<String>();
        gc = canvas.getGraphicsContext2D();
        graphicsController = new GraphicsController(gc);
        character = new Streaker();
        setOnKeyPress();
        setOnKeyRelease();
        coins = Coin.createCoins();
        tunnels = Tunnel.createTunnels();
        guards = new ArrayList<Guard>();
        setInitialScore();
    }

    private void setInitialScore() {
        collected = new IntValue(0);
        lastNanoTime = new LongValue(System.nanoTime());
        startNanoTime = System.nanoTime();
    }

    private void showCoins() {
        String coinStr = "ButtCoin: $" + collected.value;
        gc.fillText(coinStr, background.getWidth() - 150, 70);
        gc.setStroke(Color.WHITE);
        gc.setFont(new Font("Serif", 20));
        gc.strokeText(coinStr , background.getWidth() - 150, 70);
    }

    private void handleCoinIntersects() {
        for (Coin coin : coins) {
            if (coin.getY() > Constants.SCREEN_HEIGHT) {
                coin.resetPosition();
            }
            if (character.intersects(coin.getBoundary())) {
                coin.resetPosition();
                collected.value += 10;
            }
            coin.handleSpeed(gc);
        }
    }
    private void handleTunnelLoop(double time) {
        for (Tunnel tunnel : tunnels) {
            if (tunnel.getY() > Constants.SCREEN_HEIGHT) {
                tunnel.resetPosition();
            } else {
                if (tunnel.noGuard()) {
                    guards.add(tunnel.spawnGuard(gc, time));
                }
            }
            tunnel.handleSpeed(gc);
        }
    }
    private void handleGuardSpeed(double t) {
        for (Guard guard : guards) {
            guard.handleSpeed(gc, t);
        }
    }
}
