package logic.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import logic.controllers.ViewController;
import logic.controllers.ViewController.VIEW_TYPE;

import javafx.scene.Scene;
import logic.configuration.Constants;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainMenuView extends StreakerView {
	
	public MainMenuView(ViewController vc) {
		super(vc);
	}
	
	public Scene setupScene() {
		/*
		AnchorPane root = new AnchorPane();
		Button startButton = new Button("Start New Game");
		
		startButton.setOnAction(this::startGame);
		
		AnchorPane.setBottomAnchor(startButton, 300.0);
		AnchorPane.setTopAnchor(startButton, 300.0);
		AnchorPane.setLeftAnchor(startButton, 425.0);
		AnchorPane.setRightAnchor(startButton, 425.0);
		
		root.getChildren().add(startButton);

		return new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		*/
		//FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/LoginForm.fxml"));
		Parent root1 = null;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/sample.fxml"));
			root1 = (Parent) fxmlLoader.load();
			//Stage stage = new Stage();
			//stage.setScene(new Scene(root1));
			//stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return new Scene(root1);
	}
	
	public void startGame(ActionEvent click) {
		viewController.updateView(VIEW_TYPE.GAMEPLAY);
	}
}
