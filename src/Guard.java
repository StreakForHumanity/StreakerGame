import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class Guard extends AnimatedImage {
    private boolean left;

    public Guard(boolean left) {
        this.left = left;
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
        if (left) {
            this.setSpeed(Constants.STARTING_SPEED, Constants.STARTING_SPEED);
        } else {
            this.setSpeed(-1 * Constants.STARTING_SPEED, Constants.STARTING_SPEED);
        }
        this.updateSpeed();
        this.render(gc, time);
    }

    public void resetPosition() {
        double x = (Constants.SCREEN_WIDTH - 2 * Constants.COIN_BOUNDS) * Math.random() + Constants.COIN_BOUNDS;
        double y = - Constants.SCREEN_HEIGHT * Math.random();
        this.setPosition(x, y);
    }
}
