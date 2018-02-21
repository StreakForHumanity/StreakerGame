package logic.views;

import logic.controllers.ViewController;
import logic.controllers.ViewController.VIEW_TYPE;

import javafx.scene.Scene;
import logic.configuration.Constants;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainMenuView extends StreakerView {
	
	public MainMenuView(ViewController vc) {
		super(vc);
	}
	
	public Scene setupScene() {
		AnchorPane root = new AnchorPane();
		Button startButton = new Button("Start New Game");
		
		startButton.setOnAction(click -> startGame(click));
		
		AnchorPane.setBottomAnchor(startButton, 300.0);
		AnchorPane.setTopAnchor(startButton, 300.0);
		AnchorPane.setLeftAnchor(startButton, 425.0);
		AnchorPane.setRightAnchor(startButton, 425.0);
		
		root.getChildren().add(startButton);
		
		Scene scene = new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		return scene;
	}
	
	public void startGame(ActionEvent click) {
		viewController.updateView(VIEW_TYPE.GAMEPLAY);
	}
}
