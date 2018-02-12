package logic;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class Guard extends AnimatedImage {
    private boolean left;
    private Random rand;

    public Guard(boolean left) {
        rand = new Random();
        if (left) {
            this.setSpeed(Constants.STARTING_SPEED, Constants.STARTING_SPEED);
        } else {
            this.setSpeed(-1 * Constants.STARTING_SPEED, Constants.STARTING_SPEED);
        }
        Image[] imageArray = new Image[4];
        imageArray[0] = new Image(Paths.GUARD_PATHS[0]);
        imageArray[1] = new Image(Paths.GUARD_PATHS[1]);
        imageArray[2] = new Image(Paths.GUARD_PATHS[2]);
        imageArray[3] = new Image(Paths.GUARD_PATHS[3]);
        this.setFrame(imageArray);
        this.duration = Constants.FRAME_DURATION;
        width = imageArray[0].getWidth();
        height = imageArray[0].getHeight();
    }

    public void handleSpeed(GraphicsContext gc, double time) {
        double dx = (-0.5 + rand.nextDouble()) / 10;
        double dy = 1.5 * (-0.55 + rand.nextDouble());
        
        this.modifySpeed(dx, dy);
        this.updateSpeed();
        this.render(gc, time);
    }

    public void resetPosition() {
        double x = (Constants.SCREEN_WIDTH - 2 * Constants.COIN_BOUNDS) * Math.random() + Constants.COIN_BOUNDS;
        double y = - Constants.SCREEN_HEIGHT * Math.random();
        this.setPosition(x, y);
    }
}
