package logic.Views;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;

import logic.Configuration.Constants;
import logic.Controllers.*;
import logic.Model.*;

public class StreakerGame extends Application {

    private long startNanoTime;
    private LongValue lastNanoTime;
    private IntValue collected;
    private Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsController graphicsController;
    private WorldItemController worldItems;
    private KeyInputController keyController;

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
            	updateGameState(elapsedTime, keyController.input);
            	drawAll(getCurrentFrameTime(nanot), nanot);
            }
        }.start();
        stage.show();
    }
    
    /* Returns instance of StreakerGame; useful for testing,
     * 	allows us to test individual methods and object interactions.
     * 
     * Note: essentially sets up all necessary parts of game without actually launching
     * 	the javafx application.
     */
    public StreakerGame() {
    	setupGameState(new Stage());
    }

    private void setupGameState(Stage stage) {
    	stage.setTitle("logic.Controllers.StreakerController");
        root = new Group();
        scene = new Scene(root);
        stage.setScene(scene);
        canvas = new Canvas(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        graphicsController = new GraphicsController(canvas.getGraphicsContext2D());
        worldItems = new WorldItemController();
        keyController = new KeyInputController(scene);
        setInitialScore();
    }

    private void setInitialScore() {
        collected = new IntValue(0);
        lastNanoTime = new LongValue(System.nanoTime());
        startNanoTime = System.nanoTime();
    }
    
    //for use with drawItem calls to animatedItems
    private double getCurrentFrameTime(double nanot) {
        return (nanot / Constants._PRECISION);
    }
    
    /* wrapper function for use inside of AnimationTimer's handle() -
     * 	handles calls for updating object positions, checking
     * 	collisions, updating speeds/velocities, and 
     * 	updating tunnel/guard spawns and paths
     * 
     * Note: we could set the access to public for the sake of testing
     */
    private void updateGameState(double elapsedTime, ArrayList<String> input) {
    	worldItems.updateBackgroundState();
    	worldItems.updateCharacterState(elapsedTime, input);
		collected.value += worldItems.updateCoinStates();
		worldItems.updateTunnelStates();
		worldItems.updateGuardStates();
    }
    
    /* dispatches GraphicsController to draw all relevant objects
     * to its GraphicsContext on the canvas.
     * 
     * time - this parameter corresponds to that returned each loop by getCurrentFrameTime(nanot),
     * 		where nanot is defined at the top of the loop already.
     */
    private void drawAll(double time, double nanot) {
    	//draw background to screen
    	graphicsController.drawItem(worldItems.background);
    	
    	//draw character to screen
    	graphicsController.drawItem(worldItems.character, time);
    	
    	//draw coins to screen
    	for (Coin c : worldItems.coins) {
    		graphicsController.drawItem(c);
    	}
    	
    	//draw tunnels to screen
    	for (Tunnel t : worldItems.tunnels) {
    		graphicsController.drawItem(t);
    	}
    	
    	//draw guards to screen
    	for (Guard g : worldItems.guards) {
    		graphicsController.drawItem(g, time);
    	}
    	
    	//update coin stuff
    	graphicsController.showCoins(worldItems.background.getWidth(), collected.value);
    	
    	//update displayed game time
    	graphicsController.showTime(nanot);
    }
}
