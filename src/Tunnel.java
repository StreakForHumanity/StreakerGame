import javafx.scene.canvas.GraphicsContext;
import java.util.Random;

public class Tunnel extends WorldItem {

    public Tunnel() {
        this.resetPosition();
    }
    public void handleSpeed(GraphicsContext gc) {
        this.setSpeed(0, Constants.STARTING_SPEED);
        this.updateSpeed();
        this.render(gc);
    }
    /*  currently spawns tunnels on top of each other
        to do: fix spawning so no overlap
    */
    public void resetPosition() {
        boolean left = new Random().nextBoolean();
        double y = - Constants.SCREEN_HEIGHT * Math.random();
        double x;
        if (left) {
            this.setImage(Paths.TUNNEL_PATHS[0]);
            x = Constants.STADIUM_MARGIN_LEFT;
        } else {
            this.setImage(Paths.TUNNEL_PATHS[1]);
            x = Constants.STADIUM_MARGIN_RIGHT;
        }
        this.setPosition(x, y);
    }
    /* implement
    */
    public void spawnGuard() {}
}
