package logic.models;

import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import logic.configuration.Constants;
import logic.configuration.Paths;

import java.util.Random;

public class Guard extends AnimatedItem {
    private Random rand;

    public Guard(boolean left) {
        rand = new Random();
        if (left) {
            this.setSpeed(Constants.GUARD_SPEED, Constants.STARTING_SPEED);
        } else {
            this.setSpeed(-1.0 * Constants.GUARD_SPEED, Constants.STARTING_SPEED);
        }
        Image[] imageArray = new Image[4];
        imageArray[0] = new Image(Paths.GUARD_PATHS[0]);
        imageArray[1] = new Image(Paths.GUARD_PATHS[1]);
        imageArray[2] = new Image(Paths.GUARD_PATHS[2]);
        imageArray[3] = new Image(Paths.GUARD_PATHS[3]);
        this.setFrame(imageArray);
        this.setDuration(Constants.FRAME_DURATION);
        width = imageArray[0].getWidth();
        height = imageArray[0].getHeight();
    }
    
    public Guard(boolean left, int test) {
        rand = new Random();
        if (left) {
            this.setSpeed(Constants.GUARD_SPEED, Constants.STARTING_SPEED);
        } else {
            this.setSpeed(-1.0 * Constants.GUARD_SPEED, Constants.STARTING_SPEED);
        }
        frames = null;
        this.setDuration(Constants.FRAME_DURATION);
        width = 0;
        height = 0;
    }

    @Override
    public void updatePosition() {
    		double dx = (-0.5 + rand.nextDouble()) / 10;
        double dy = 2.0 * (-0.485 + rand.nextDouble());
        // maybe use predrawn parabolas instead of random
        this.incrementSpeed(dx, dy);
        super.updatePosition();
    }
    
    public void resetPosition() {
        double x = (Constants.SCREEN_WIDTH - 2 * Constants.COIN_BOUNDS) * Math.random() + Constants.COIN_BOUNDS;
        double y = - Constants.SCREEN_HEIGHT * Math.random();
        this.setPosition(x, y);
    }
    
    public Rotate getRotate() {
    	double rads = Math.atan2(getSpeedX(), -getSpeedY());
    	return new Rotate(Math.toDegrees(rads), this.getX() + getWidth()/2, this.getY() + getHeight()/2);
    }
}
