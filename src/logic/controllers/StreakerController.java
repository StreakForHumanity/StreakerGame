package logic.controllers;

import javafx.scene.image.Image;
import logic.configuration.Constants;
import logic.configuration.Paths;
import logic.models.Character;
import javafx.geometry.Rectangle2D;
import java.util.List;

public class StreakerController {

	private boolean isJumping;
	private boolean canJump;
	private Character streaker;

    protected StreakerController() {
    		streaker = new Character();
        this.isJumping = false;
        this.canJump = true;
    }
    
    public StreakerController(int i) {
    		streaker = new Character(1);
    		this.isJumping = false;
    		this.canJump = true;
    }

	public Character getStreaker() {
		return streaker;
	}

	protected void handleJump(List<String> input) {
        if (input.contains("SPACE") && canJump) {
                updateCharForJump();
        }
	}
	
	public boolean getIsJumping() {
		return isJumping;
	}

	protected boolean isJumping() {
		return isJumping;
	}

	private void updateCharForJump() {
		if(!isJumping) {
			isJumping = true;
			// Change image of streaker
			Image[] imageArray = new Image[1];
			imageArray[0] = new Image(Paths.STREAKER_PATHS[4]);
			streaker.setFrame(imageArray);
			
			new Thread(() -> {
                double sT = System.currentTimeMillis();
                while((System.currentTimeMillis()-sT) < Constants.JUMP_TIME) {
                	continue;
				}
                Image[] imageArray1 = new Image[4];
                imageArray1[0] = new Image(Paths.STREAKER_PATHS[0]);
                imageArray1[1] = new Image(Paths.STREAKER_PATHS[1]);
                imageArray1[2] = new Image(Paths.STREAKER_PATHS[2]);
                imageArray1[3] = new Image(Paths.STREAKER_PATHS[3]);
                streaker.setFrame(imageArray1);
                isJumping = false;
                sT = System.currentTimeMillis();
                canJump = false;
                while((System.currentTimeMillis()-sT) < Constants.COOLDOWN_TIME) {
                	continue;
				}
                canJump = true;
            }).start();
		}
		
	}

	protected boolean intersects(Rectangle2D s) {
		return (streaker.intersects(s) && !isJumping);
	}
}
