package logic.Views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import logic.Configuration.Constants;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainMenuView extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		AnchorPane root = new AnchorPane();
		Button startButton = new Button("Start New Game");
		
		startButton.setOnAction(click -> startGame(click));
		
		AnchorPane.setBottomAnchor(startButton, 300.0);
		AnchorPane.setTopAnchor(startButton, 300.0);
		AnchorPane.setLeftAnchor(startButton, 425.0);
		AnchorPane.setRightAnchor(startButton, 425.0);
		
		root.getChildren().add(startButton);
		
		Scene scene = new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		stage.setTitle("Streakers");
		stage.setScene(scene);
		stage.show();
	}
	
	public void startGame(ActionEvent click) {
		GameplayView streakerGame = new GameplayView();
		Scene gameScene = streakerGame.setupGameScene();
        
		Stage window = (Stage)((Node)click.getSource()).getScene().getWindow();
		window.setScene(gameScene);
		window.show();
		
		streakerGame.startGame(window);
	}
}
