package logic;

import javafx.scene.image.Image;
import logic.AnimatedImage;
import logic.Constants;
import logic.Paths;

import java.util.ArrayList;

public class Streaker extends AnimatedImage {

    public Streaker() {
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
    }

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

    public void handleVelocity(ArrayList<String> input) {
        this.setVelocity(0,0);
        if (input.contains("LEFT")) {
            this.addVelocity(-Constants.CHARACTER_VELOCITY, 0);
        }
        if (input.contains("RIGHT")) {
            this.addVelocity(Constants.CHARACTER_VELOCITY, 0);
        }
        if (input.contains("UP")) {
            this.addVelocity(0, -Constants.CHARACTER_VELOCITY);
        }
        if (input.contains("DOWN")) {
            this.addVelocity(0, Constants.CHARACTER_VELOCITY);
        }
    }
}
