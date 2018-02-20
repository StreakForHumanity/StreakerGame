package logic.Controllers;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

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
