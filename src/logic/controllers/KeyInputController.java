package logic.controllers;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;


/*
 * Note: each view in which we want to process key input
 * will need a KeyInputController (e.g. MainMenuView, GameplayView).
 * It takes a reference to the active scene.
 * When a class has a reference to a KeyInputController,
 * it can obtain key input at any given point (independent of 
 * an AnimationTimer).
 */
public class KeyInputController {
	
	private Scene scene;
	public ArrayList<String> input;
	
	public KeyInputController(Scene scene) {
		this.scene = scene;
		input = new ArrayList<>();
		setOnKeyPress();
		setOnKeyRelease();
	}
	
	private void setOnKeyPress() {
		scene.setOnKeyPressed(
            new EventHandler<KeyEvent>() {
                public void handle(KeyEvent e) {
                    String code = e.getCode().toString();
                    if (!input.contains(code)) {
                        input.add(code);
                    }
                }
            }
		);
	}
	
	private void setOnKeyRelease() {
        scene.setOnKeyReleased(
            new EventHandler<KeyEvent>() {
                public void handle(KeyEvent e) {
                    String code = e.getCode().toString();
                    input.remove(code);
                }
            }
        );
    }

}
