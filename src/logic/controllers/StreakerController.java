package logic.controllers;

import javafx.scene.image.Image;
import logic.configuration.Constants;
import logic.configuration.Paths;
import logic.models.Character;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;

public class StreakerController {

	public boolean isJumping;
	private boolean canJump;
	public Character streaker;

    public StreakerController() {
    	streaker = new Character();
        this.isJumping = false;
        this.canJump = true;
    }

    public void handleJump(ArrayList<String> input) {
        if (input.contains("SPACE")) {
            if(canJump) {
                updateCharForJump();
            }
        }
	}

	private void updateCharForJump() {
		if(!isJumping) {
			isJumping = true;
			// Change image of streaker
			Image[] imageArray = new Image[1];
			imageArray[0] = new Image(Paths.STREAKER_PATHS[4]);
			streaker.setFrame(imageArray);
			
			new Thread(new Runnable() {
				public void run() {
					double sT = System.currentTimeMillis();
					while((System.currentTimeMillis()-sT) < Constants.JUMP_TIME) {}
					Image[] imageArray = new Image[4];
					imageArray[0] = new Image(Paths.STREAKER_PATHS[0]);
			        imageArray[1] = new Image(Paths.STREAKER_PATHS[1]);
			        imageArray[2] = new Image(Paths.STREAKER_PATHS[2]);
			        imageArray[3] = new Image(Paths.STREAKER_PATHS[3]);
			        streaker.setFrame(imageArray);
					isJumping = false;
					sT = System.currentTimeMillis();
					canJump = false;
					while((System.currentTimeMillis()-sT) < Constants.COOLDOWN_TIME) {}
					canJump = true;
				}
			}).start();
		}
		
	}

	public boolean intersects(Rectangle2D s) {
		return (streaker.intersects(s) && !isJumping);
	}
}
