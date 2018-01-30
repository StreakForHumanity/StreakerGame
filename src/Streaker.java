import javafx.scene.image.Image;

import java.util.ArrayList;

public class Streaker extends AnimatedImage {
    private static final String[] _PATHS = {
            "./assets/guyForward.png",
            "./assets/guyLeft.png",
            "./assets/guyForward.png",
            "./assets/guyRight.png"
    };
    private double FRAME_DURATION;
    private double SCREEN_HEIGHT;
    private static Constants constants;

    public Streaker(double FRAME_DURATION, int SCREEN_HEIGHT) {
        this.FRAME_DURATION = FRAME_DURATION;
        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
        Image[] imageArray = new Image[4];
        imageArray[0] = new Image(_PATHS[0]);
        imageArray[1] = new Image(_PATHS[1]);
        imageArray[2] = new Image(_PATHS[2]);
        imageArray[3] = new Image(_PATHS[3]);
        this.setFrame(imageArray);
        this.duration = FRAME_DURATION;
        this.setPosition((constants.getBackground().getWidth() / 2) - 40, SCREEN_HEIGHT / 2);
        width = imageArray[0].getWidth();
        height = imageArray[0].getHeight();
    }

    public void handleCharacterPosition() {
        if (this.getX() < constants.getStadiumBorder()) {
            this.setPosition(constants.getStadiumBorder(), this.getY());
        }
        if (this.getX() > constants.getBackground().getWidth() - constants.getStadiumBorder() - this.getWidth()) {
            this.setPosition(constants.getBackground().getWidth() - constants.getStadiumBorder() - this.getWidth(), this.getY());
        }
        if (this.getY() < 0) {
            this.setPosition(this.getX(), 0);
        }
        if (this.getY() > SCREEN_HEIGHT - this.getHeight()) {
            this.setPosition(this.getX(), SCREEN_HEIGHT - this.getHeight());
        }
    }

    public void handleVelocity(ArrayList<String> input) {
        this.setVelocity(0,0);
        if (input.contains("LEFT")) {
            this.addVelocity(-constants.getCharacterVelocity(),0);
        }
        if (input.contains("RIGHT")) {
            this.addVelocity(constants.getCharacterVelocity(),0);
        }
        if (input.contains("UP")) {
            this.addVelocity(0,-constants.getCharacterVelocity());
        }
        if (input.contains("DOWN")) {
            this.addVelocity(0,constants.getCharacterVelocity());
        }
    }
}
