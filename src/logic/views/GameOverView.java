package logic.views;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import logic.configuration.Constants;
import logic.controllers.ViewController;
import logic.controllers.ViewController.VIEW_TYPE;

public class GameOverView extends StreakerView {
	
	private int coins;
	private String hms;
	
	public GameOverView(ViewController vc) {
		super(vc);
	}
	
	public GameOverView(ViewController vc, String hms, int coins) {
		/*
		 * TODO: make the game over screen relatively pritteh with 
		 * text and stuff about how long player lasted and how many
		 * coins they got. maybe have a picture of the background
		 * up in there, who knows?
		 */
		super(vc);
		
	}
	
	public Scene setupScene() {
		AnchorPane root = new AnchorPane();
		Button startButton = new Button("Go To Main Menu");
		
		startButton.setOnAction(click -> goToMainMenu(click));
		
		AnchorPane.setBottomAnchor(startButton, 100.0);
		AnchorPane.setTopAnchor(startButton, 500.0);
		AnchorPane.setLeftAnchor(startButton, 425.0);
		AnchorPane.setRightAnchor(startButton, 425.0);
		
		root.getChildren().add(startButton);
		
		Scene scene = new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		return scene;
	}
	
	public void goToMainMenu(ActionEvent click) {
		viewController.updateView(VIEW_TYPE.MAIN_MENU);
	}
}
