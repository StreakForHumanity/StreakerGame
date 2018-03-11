package logic.views;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import logic.controllers.ViewController;
import logic.controllers.ViewController.VIEW_TYPE;
import javafx.scene.Scene;
import logic.configuration.Constants;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
import java.io.IOException;

public class MainMenuView extends StreakerView {

	
	public MainMenuView(ViewController vc) {
		super(vc);
	}
	
	public Scene setupScene() {
		AnchorPane root = new AnchorPane();
		BackgroundImage bgi = new BackgroundImage(new Image(Paths.MAINMENU_BACKGROUND_PATH), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		root.setBackground(new Background(bgi));

		VBox buttons = setupButtons();
		setButtonAnchors(buttons);

		root.getChildren().add(buttons);
		return new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

	}

	private VBox setupButtons() {
		VBox buttons = new VBox();
		buttons.setSpacing(30);

		ImageButton newGame = new ImageButton();
		newGame.updateImages(new Image(Paths.MAIN_MENU_BUTTONS[0]), new Image(Paths.MAIN_MENU_BUTTONS[0]));
		newGame.setOnAction(this::startGame);
		buttons.getChildren().add(newGame);

		ImageButton settings = new ImageButton();
		settings.updateImages(new Image(Paths.MAIN_MENU_BUTTONS[1]), new Image(Paths.MAIN_MENU_BUTTONS[1]));
		settings.setOnAction(this::goToSettings);
		buttons.getChildren().add(settings);

		ImageButton help = new ImageButton();
		help.updateImages(new Image(Paths.MAIN_MENU_BUTTONS[2]), new Image(Paths.MAIN_MENU_BUTTONS[2]));
		help.setOnAction(this::goToHelp);
		buttons.getChildren().add(help);

		return buttons;
	}

	private void setButtonAnchors(VBox buttons) {
		AnchorPane.setBottomAnchor(buttons, 300.0);
		AnchorPane.setTopAnchor(buttons, 200.0);
		AnchorPane.setLeftAnchor(buttons, 450.0);
		AnchorPane.setRightAnchor(buttons, 550.0);
	}

	public void startGame(ActionEvent click) {
		viewController.updateView(VIEW_TYPE.GAMEPLAY);
	}

	public void goToSettings(ActionEvent click) {
		viewController.updateView(VIEW_TYPE.SETTINGS);
	}

	public void goToHelp(ActionEvent click) {
		viewController.updateView(VIEW_TYPE.HELP);
	}


}
