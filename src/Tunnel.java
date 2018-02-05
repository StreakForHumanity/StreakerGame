import javafx.scene.canvas.GraphicsContext;
import java.util.Random;
import java.util.ArrayList;

public class Tunnel extends WorldItem {

    private boolean noGuard;
    private boolean left;

    public Tunnel() {
        this.resetPosition();
        noGuard = true;
    }
    public void handleSpeed(GraphicsContext gc) {
        this.setSpeed(0, Constants.STARTING_SPEED);
        this.updateSpeed();
        this.render(gc);
    }
    public void resetPosition() {
        left = new Random().nextBoolean();
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
        this.noGuard = true;
    }
    public Guard spawnGuard(GraphicsContext gc, double time) {
        Guard g = new Guard(left);
        g.setPosition(getX(), getY());
        g.handleSpeed(gc, time);
        noGuard = false;
        return g;
    }

    public static ArrayList<Tunnel> createTunnels() {
        ArrayList<Tunnel> tunnels = new ArrayList<Tunnel>();
        for(int i = 0; i < Constants.NUM_TUNNELS; i++) {
            Tunnel tunnel = new Tunnel();
            tunnels.add(tunnel);
        }
        return tunnels;
    }
    public boolean noGuard() {
        return noGuard;
    }
}
