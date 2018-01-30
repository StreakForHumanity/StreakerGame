import javafx.scene.canvas.GraphicsContext;
import java.util.Random;

public class Tunnel extends WorldItem {
    private static final String _PATHS [] = {
        "./assets/tunnelLeft.png",
        "./assets/tunnelRight.png"
    };

    private BackgroundItem background;
    private int MOVING_SPEED;
    private final double POSITIONR = 695;
    private final double POSITIONL = 50;

    public Tunnel(BackgroundItem background, int MOVING_SPEED) {
        this.background = background;
        this.MOVING_SPEED = MOVING_SPEED;
        this.resetPosition();
    }
    public void handleSpeed(GraphicsContext gc) {
        this.setSpeed(0, MOVING_SPEED);
        this.updateSpeed();
        this.render(gc);
    }
    /*  currently spawns tunnels on top fo each other
        to do: fix spawning so no overlap
    */
    public void resetPosition() {
        boolean left = new Random().nextBoolean();
        double y = - background.getHeight() * Math.random();
        double x;
        if (left) {
            this.setImage(_PATHS[0]);
            x = POSITIONL;
        } else {
            this.setImage(_PATHS[1]);
            x = POSITIONR;
        }
        this.setPosition(x, y);
    }
    /* implement
    */
    public void spawnGuard() {}
}
