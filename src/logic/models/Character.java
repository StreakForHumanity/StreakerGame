package logic.models;

import javafx.scene.image.Image;
import logic.configuration.Constants;
import logic.configuration.Paths;
import logic.models.AnimatedItem;

import java.util.ArrayList;

public class Character extends AnimatedItem {
	
	private double charHealth;

	public Character() {
		Image[] imageArray = new Image[4];
        imageArray[0] = new Image(Paths.STREAKER_PATHS[0]);
        imageArray[1] = new Image(Paths.STREAKER_PATHS[1]);
        imageArray[2] = new Image(Paths.STREAKER_PATHS[2]);
        imageArray[3] = new Image(Paths.STREAKER_PATHS[3]);
        this.setFrame(imageArray);
        this.duration = Constants.FRAME_DURATION;
        this.setPosition((Constants.SCREEN_WIDTH / 2) - 40, Constants.SCREEN_HEIGHT / 2);
        width = imageArray[0].getWidth();
        height = imageArray[0].getHeight();
        this.setSpeed(0, 0);
        charHealth = Constants.CHAR_MAX_HEALTH;
	}
	
	// ensures character is in bounds of stadium background
    public void handleCharacterPosition() {
        if (this.getX() < Constants.STADIUM_BORDER) {
            this.setPosition(Constants.STADIUM_BORDER, this.getY());
        }
        if (this.getX() > Constants.SCREEN_WIDTH - Constants.STADIUM_BORDER - this.getWidth()) {
            this.setPosition(Constants.SCREEN_WIDTH - Constants.STADIUM_BORDER - this.getWidth(), this.getY());
        }
        if (this.getY() < 0) {
            this.setPosition(this.getX(), 0);
        }
        if (this.getY() > Constants.SCREEN_HEIGHT - this.getHeight()) {
            this.setPosition(this.getX(), Constants.SCREEN_HEIGHT - this.getHeight());
        }
    }
    
    public void applyUserInputToVelocity(ArrayList<String> input, boolean inMud, boolean isJumping) {
    	double vel = Constants.CHARACTER_VELOCITY;
    	if(inMud && !isJumping) {
    		vel = vel/2;
    	}
        this.setVelocity(0,0);
        if (input.contains("LEFT")) {
            this.incrementVelocity(-vel, 0);
        }
        if (input.contains("RIGHT")) {
            this.incrementVelocity(vel, 0);
        }
        if (input.contains("UP")) {
            this.incrementVelocity(0, -vel);
        }
        if (input.contains("DOWN")) {
            this.incrementVelocity(0, vel);
        }
    }
    
    public void updatePosition(double elapsedTime, ArrayList<String> input, boolean inMud, boolean isJumping) {
    	applyUserInputToVelocity(input, inMud, isJumping);
    	super.applyVelocity(elapsedTime);
    	if (inMud && !isJumping) {
    		this.setSpeed(0, Constants.STARTING_SPEED / 2);
    	}
    	else {
    		this.setSpeed(0, 0);
    	}
    	super.updatePosition();
    }
    
    public double getHealth() {
    	return charHealth;
    }
    
    //increment or decrement health
    public void changeHealth(double dH) {
    	charHealth += dH;
    }
}
