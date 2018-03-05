package logic.views;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.configuration.Constants;
import logic.configuration.Paths;
import logic.controllers.ViewController;
import logic.controllers.ViewController.VIEW_TYPE;
import logic.models.ImageButton;

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
		this.coins = coins;
		this.hms = hms;
	}
	
	public Scene setupScene() {
		AnchorPane root = new AnchorPane();
		BackgroundImage bgi = new BackgroundImage(new Image(Paths.GAME_OVER_PATH), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		root.setBackground(new Background(bgi));
		
		Text time = new Text(225, 290, hms);
		time.setFont(new Font(20));
		
		Text collected = new Text(480, 290, "$" + Integer.toString(coins));
		collected.setFont(new Font(20));
		
		Text totScore = new Text(730, 290, calculateScore());
		totScore.setFont(new Font(20));
		
		root.getChildren().add(time);
		root.getChildren().add(collected);
		root.getChildren().add(totScore);
	
		VBox buttons = setupButtons();
		setButtonAnchors(buttons);
		
		root.getChildren().add(buttons);
		
		Scene scene = new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		return scene;
	}
	
	private VBox setupButtons() {
		VBox buttons = new VBox();
		buttons.setSpacing(30);
		
		ImageButton newGame = new ImageButton();
		newGame.updateImages(new Image(Paths.GAME_OVER_BUTTONS[0]), new Image(Paths.GAME_OVER_BUTTONS_PRESSED[0]));
		newGame.setOnAction(click -> goToNewGame(click));
		buttons.getChildren().add(newGame);
		
		ImageButton mainMenu = new ImageButton();
		mainMenu.updateImages(new Image(Paths.GAME_OVER_BUTTONS[1]), new Image(Paths.GAME_OVER_BUTTONS_PRESSED[1]));
		mainMenu.setOnAction(click -> goToMainMenu(click));
		buttons.getChildren().add(mainMenu);
		
		ImageButton exitGame = new ImageButton();
		exitGame.updateImages(new Image(Paths.GAME_OVER_BUTTONS[2]), new Image(Paths.GAME_OVER_BUTTONS_PRESSED[2]));
		exitGame.setOnAction(click -> exitGame(click));
		buttons.getChildren().add(exitGame);
		
		return buttons;
	}
	
	private void setButtonAnchors(VBox buttons) {
		AnchorPane.setBottomAnchor(buttons, 25.0);
		AnchorPane.setTopAnchor(buttons, 345.0);
		AnchorPane.setLeftAnchor(buttons, 140.0);
		AnchorPane.setRightAnchor(buttons, 550.0);
	}
	
	//to be used once I actually add high scores lmao
	private void setHighScoreAnchors(VBox scores) {
		AnchorPane.setBottomAnchor(scores, 25.0);
		AnchorPane.setTopAnchor(scores, 345.0);
		AnchorPane.setLeftAnchor(scores, 550.0);
		AnchorPane.setRightAnchor(scores, 150.0);
	}
	
	private void goToMainMenu(ActionEvent click) {
		viewController.updateView(VIEW_TYPE.MAIN_MENU);
	}
	
	private void goToNewGame(ActionEvent click) {
		viewController.updateView(VIEW_TYPE.GAMEPLAY);
	}
	
	private void exitGame(ActionEvent click) {
		Platform.exit();
	}
	
	private String calculateScore() {
		int finalScore = 0;
		String[] partials = hms.split(":");
		
		finalScore += Integer.parseInt(partials[0]) * 1000;
		finalScore += Integer.parseInt(partials[1]) * 100;
		finalScore += Integer.parseInt(partials[2]);
		finalScore += coins * 3;
		
		return Integer.toString(finalScore);
	}
}
