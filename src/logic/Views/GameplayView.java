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

public class GameplayView {

    private long startNanoTime;
    private LongValue lastNanoTime;
    private IntValue collected;
    private Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsController graphicsController;
    private WorldItemController worldItems;
    private KeyInputController keyController;
    
    public AnimationTimer getGameLoop() {
    	return new AnimationTimer() {
            public void handle(long currentNanoTime) {
            	double elapsedTime = (currentNanoTime - lastNanoTime.value) / Constants._PRECISION;
            	lastNanoTime.value = currentNanoTime;
            	double nanot = currentNanoTime - startNanoTime;
            	updateGameState(elapsedTime, keyController.input);
            	drawAll(getCurrentFrameTime(nanot), nanot);
            }
        };
    }
    
    public Scene setupGameScene() {
    	root = new Group();
        scene = new Scene(root);
        canvas = new Canvas(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        
        return scene;
    }
    
    public void startGame(Stage stage) {
        setupGameState();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
            	double elapsedTime = (currentNanoTime - lastNanoTime.value) / Constants._PRECISION;
            	lastNanoTime.value = currentNanoTime;
            	double nanot = currentNanoTime - startNanoTime;
            	updateGameState(elapsedTime, keyController.input);
            	drawAll(getCurrentFrameTime(nanot), nanot);
            }
        }.start();
    }

    private void setupGameState() {
        /*root = new Group();
        scene = new Scene(root);
        canvas = new Canvas(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        root.getChildren().add(canvas);*/
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
    	graphicsController.drawItem(worldItems.character.streaker, time);
    	
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
