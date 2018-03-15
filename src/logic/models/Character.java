package logic.models;

import javafx.scene.image.Image;
import logic.configuration.Constants;
import logic.configuration.Paths;
import logic.models.AnimatedItem;

import java.util.List;

public class Character extends AnimatedItem {
	
	private double charHealth;
	private double cooldownTime;

	public Character() {
		Image[] imageArray = new Image[4];
        imageArray[0] = new Image(Paths.getStreakerPaths()[0]);
        imageArray[1] = new Image(Paths.getStreakerPaths()[1]);
        imageArray[2] = new Image(Paths.getStreakerPaths()[2]);
        imageArray[3] = new Image(Paths.getStreakerPaths()[3]);
        this.setFrame(imageArray);
        this.setDuration(Constants.FRAME_DURATION);
        this.setPosition((Constants.SCREEN_WIDTH / 2.0) - 40.0, Constants.SCREEN_HEIGHT / 2.0);
        width = imageArray[0].getWidth();
        height = imageArray[0].getHeight();
        this.setSpeed(0, 0);
        charHealth = Constants.CHAR_MAX_HEALTH;
        cooldownTime = Constants.COOLDOWN_TIME / 1000.0;
	}
	
	public Character(int test) {
        this.setDuration(Constants.FRAME_DURATION);
        this.setPosition((Constants.SCREEN_WIDTH / 2.0) - 40.0, Constants.SCREEN_HEIGHT / 2.0);
        width = 0;
        height = 0;
        this.setSpeed(0, 0);
        charHealth = Constants.CHAR_MAX_HEALTH;
        cooldownTime = Constants.COOLDOWN_TIME / 1000.0;
	}
	
	// ensures characterController is in bounds of stadium background
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
    
    public void applyUserInputToVelocity(List<String> input, boolean inMud, boolean isJumping) {
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
    
    public void updatePosition(double elapsedTime, List<String> input, boolean inMud, boolean isJumping) {
    	applyUserInputToVelocity(input, inMud, isJumping);
    	super.applyVelocity(elapsedTime);
    	if (inMud && !isJumping) {
    		this.setSpeed(0, Constants.STARTING_SPEED / 2.0);
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
    
    public double getCooldown() {
    		return cooldownTime;
    }
    
    public void changeCooldown(double dC) {
    		cooldownTime += dC;
    }
    
    public void resetCooldown() {
    		cooldownTime = 0;
    }
    
    
}
