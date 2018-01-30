import javafx.scene.image.Image;

import java.util.ArrayList;

public class Streaker extends AnimatedImage {
    private static final String[] _PATHS = {
            "./assets/guyForward.png",
            "./assets/guyLeft.png",
            "./assets/guyForward.png",
            "./assets/guyRight.png"
    };

    public Streaker() {
        Image[] imageArray = new Image[4];
        imageArray[0] = new Image(_PATHS[0]);
        imageArray[1] = new Image(_PATHS[1]);
        imageArray[2] = new Image(_PATHS[2]);
        imageArray[3] = new Image(_PATHS[3]);
        this.setFrame(imageArray);
        this.duration = Constants.getFrameDuration();
        this.setPosition((Constants.getScreenWidth() / 2) - 40, Constants.getScreenHeight() / 2);
        width = imageArray[0].getWidth();
        height = imageArray[0].getHeight();
    }

    public void handleCharacterPosition() {
        if (this.getX() < Constants.getStadiumBorder()) {
            this.setPosition(Constants.getStadiumBorder(), this.getY());
        }
        if (this.getX() > Constants.getScreenWidth() - Constants.getStadiumBorder() - this.getWidth()) {
            this.setPosition(Constants.getScreenWidth() - Constants.getStadiumBorder() - this.getWidth(), this.getY());
        }
        if (this.getY() < 0) {
            this.setPosition(this.getX(), 0);
        }
        if (this.getY() > Constants.getScreenHeight() - this.getHeight()) {
            this.setPosition(this.getX(), Constants.getScreenHeight() - this.getHeight());
        }
    }

    public void handleVelocity(ArrayList<String> input) {
        this.setVelocity(0,0);
        if (input.contains("LEFT")) {
            this.addVelocity(-Constants.getCharacterVelocity(),0);
        }
        if (input.contains("RIGHT")) {
            this.addVelocity(Constants.getCharacterVelocity(),0);
        }
        if (input.contains("UP")) {
            this.addVelocity(0,-Constants.getCharacterVelocity());
        }
        if (input.contains("DOWN")) {
            this.addVelocity(0,Constants.getCharacterVelocity());
        }
    }
}
