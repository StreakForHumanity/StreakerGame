package logic.client;

import javafx.application.Application;
import javafx.stage.Stage;

import logic.controllers.ViewFactory;
import logic.controllers.ViewFactory.VIEW_TYPE;

/*
 * The Client class acts as the environment within which the 
 * ViewController operates, updating the stage's scene when called by
 * view classes which extend StreakerView (e.g. MainMenuView, PauseMenuView,
 * and GameplayView). It launches the application and serves the initial view
 * (a MainMenuView) to the stage.
 */
public class Client extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		ViewFactory vc;
		stage.setTitle("Streakers");
		vc = new ViewFactory(stage);
		vc.updateView(VIEW_TYPE.MAIN_MENU);
	}
}
