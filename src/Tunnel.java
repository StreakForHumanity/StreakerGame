import javafx.scene.canvas.GraphicsContext;
import java.util.Random;

public class Tunnel extends WorldItem {
    private static final String _PATHS [] = {
        "./assets/tunnelLeft.png",
        "./assets/tunnelRight.png"
    };

    public Tunnel() {
        this.resetPosition();
    }
    public void handleSpeed(GraphicsContext gc) {
        this.setSpeed(0, Constants.getStartingSpeed());
        this.updateSpeed();
        this.render(gc);
    }
    /*  currently spawns tunnels on top of each other
        to do: fix spawning so no overlap
    */
    public void resetPosition() {
        boolean left = new Random().nextBoolean();
        double y = - Constants.getScreenHeight() * Math.random();
        double x;
        if (left) {
            this.setImage(_PATHS[0]);
            x = Constants.getStadMargLeft();
        } else {
            this.setImage(_PATHS[1]);
            x = Constants.getStadMargRight();
        }
        this.setPosition(x, y);
    }
    /* implement
    */
    public void spawnGuard() {}
}
