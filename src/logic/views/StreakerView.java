package logic.views;

import javafx.scene.Scene;
import logic.controllers.ViewController;

public abstract class StreakerView {
	
	protected ViewController viewController;
	
	
	/* 
	 * The constructor for a class which
	 * extends StreakerView includes a reference
	 * to the ViewController instantiated initially in 
	 * the Client class.
	 */
	public StreakerView(ViewController v) {
		viewController = v;
	}

	public StreakerView(){

	}
	/*
	 * extending view classes must implement this method.
	 * It creates a new instance of a Scene and 
	 * sets up any necessary elements in the scene 
	 * (e.g. loading from an fxml file, or manually adding
	 * javafx elements) before returning the scene reference.
	 */
	public abstract Scene setupScene();
}
