package logic.views;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.animation.AnimationTimer;

import java.util.List;

import logic.controllers.ViewController;
import logic.configuration.Constants;
import logic.controllers.*;
import logic.models.*;

public class GameplayView extends StreakerView {

    private long startNanoTime;
    private LongValue lastNanoTime;
    private IntValue collected;
    private Scene scene;
    private Canvas canvas;
    private GraphicsController graphicsController;
    private WorldItemController worldItems;
    private KeyInputController keyController;
    
    public GameplayView(ViewController vc) {
    	super(vc);
    }
    
    public Scene setupScene() {
        Group root = new Group();
        scene = new Scene(root);
        canvas = new Canvas(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        
        return scene;
    }
    
    private void setupGameState() {
        graphicsController = new GraphicsController(canvas.getGraphicsContext2D());
        worldItems = new WorldItemController();
        keyController = new KeyInputController(scene);
        setInitialScore();
    }
    
    public void startGameLoop() {
        setupGameState();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
            	double nanot = currentNanoTime - (double)startNanoTime;
            	if (worldItems.getCharacterController().getStreaker().getHealth() < 0.0) {
            		viewController.updateViewGameOver(graphicsController.getHMS(nanot).trim(), collected.getValue());
            		this.stop();
            	}
            	
            	double elapsedTime = (currentNanoTime - lastNanoTime.getValue()) / Constants.PRECISION;
            	lastNanoTime.setValue(currentNanoTime);
            	updateGameState(elapsedTime, keyController.getInput());
            	drawAll(getCurrentFrameTime(nanot), nanot);
            }
        }.start();
    }

    private void setInitialScore() {
        collected = new IntValue(0);
        lastNanoTime = new LongValue(System.nanoTime());
        startNanoTime = System.nanoTime();
    }
    
    //for use with drawItem calls to animatedItems
    private double getCurrentFrameTime(double nanot) {
        return (nanot / Constants.PRECISION);
    }
    
    /* wrapper function for use inside of AnimationTimer's handle() -
     * 	handles calls for updating object positions, checking
     * 	collisions, updating speeds/velocities, and 
     * 	updating tunnel/guard spawns and paths
     * 
     * Note: we could set the access to public for the sake of testing
     */
    private void updateGameState(double elapsedTime, List<String> input) {
    	worldItems.updateBackgroundState();
    	worldItems.updateCharacterState(elapsedTime, input);
		collected.setValue(collected.getValue()+worldItems.updateCoinStates());
		worldItems.updateTunnelStates();
		worldItems.updateGuardStates();
		worldItems.updateTerrainStates();
    }
    
    /* dispatches GraphicsController to draw all relevant objects
     * to its GraphicsContext on the canvas.
     * 
     * time - this parameter corresponds to that returned each loop by getCurrentFrameTime(nanot),
     * 		where nanot is defined at the top of the loop already.
     * 
     * HUGE MUCHO IMPORANTE NOTE: the calls within are order-dependent! Those called last
     * 		will overlap those called previously - keep this in mind when modifying!
     */
    private void drawAll(double time, double nanot) {
    	//draw background to screen
    	graphicsController.drawItem(worldItems.getBackground());
    	
    	//draw terrain items to screen
    	for (Terrain t : worldItems.getTerrains()) {
    		graphicsController.drawItem(t);
    	}
    	
    	//draw characterController to screen
    	graphicsController.drawItem(worldItems.getCharacterController().getStreaker(), time);
    	
    	//draw coins to screen
    	for (Coin c : worldItems.getCoins()) {
    		graphicsController.drawItem(c);
    	}
    	
    	//draw tunnels to screen
    	for (Tunnel t : worldItems.getTunnels()) {
    		graphicsController.drawItem(t);
    	}
    	
    	//draw guards to screen
    	for (Guard g : worldItems.getGuards()) {
    		graphicsController.drawItem(g, time);
    	}
    	
    	//update coin stuff
    	graphicsController.showCoins(worldItems.getBackground().getWidth(), collected.getValue());
    	
    	//update displayed game time
    	graphicsController.showTime(nanot);
    	
    	//display characterController health
    	graphicsController.drawHealth(worldItems.getCharacterController().getStreaker().getHealth());
    }
}
