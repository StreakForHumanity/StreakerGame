package logic.controllers;

import javafx.stage.Stage;
import javafx.scene.Scene;
import logic.views.*;

public class ViewController {
	
	private Stage stage;
	
	public ViewController(Stage stage) {
		this.stage = stage;
	}
		
	public void updateView(VIEW_TYPE vt) {
		StreakerView newView;
		switch(vt) {
		case MAIN_MENU:
			newView = new MainMenuView(this);
			updateView(newView.setupScene());
			break;
		case GAMEPLAY:
			newView = new GameplayView(this);
			updateView(newView.setupScene());
			((GameplayView)newView).startGameLoop();
			break;
		default:
			newView = new MainMenuView(this);
			updateView(newView.setupScene());
			break;
		}
	}
	
	private void updateView(Scene scene) {
		stage.setScene(scene);
		stage.show();
	}
	
	public enum VIEW_TYPE {
		MAIN_MENU,
		GAMEPLAY,
		PAUSE_MENU
	}
}
