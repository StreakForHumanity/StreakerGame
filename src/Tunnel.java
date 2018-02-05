import javafx.scene.canvas.GraphicsContext;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class Tunnel extends WorldItem {

    private boolean noGuard;
    private boolean left;
    private TunnelPosition initialY;
    private static ArrayList<TunnelPosition> tunnelPositions = new ArrayList<>();

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
        Random rand = new Random();
        left = rand.nextBoolean();
        double y = - (Constants.SCREEN_HEIGHT * rand.nextDouble());
        while(tunnelPositions.contains(new TunnelPosition(y))) {
            y = - (Constants.SCREEN_HEIGHT * rand.nextDouble());
        }
        System.out.println("y: " + y + " | left?: " + left);
        double x;
        if (left) {
            this.setImage(Paths.TUNNEL_PATHS[0]);
            x = Constants.STADIUM_MARGIN_LEFT;
        } else {
            this.setImage(Paths.TUNNEL_PATHS[1]);
            x = Constants.STADIUM_MARGIN_RIGHT;
        }
        tunnelPositions.remove(this.initialY);
        this.initialY = new TunnelPosition(y);
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
    

    /*public static HashMap<Double, Tunnel> createTunnels() {
        HashMap<Double, Tunnel> tunnels = new HashMap<>();
        Random rand = new Random();
        for(int i = 0; i < Constants.NUM_TUNNELS; i++) {
            Tunnel tunnel = new Tunnel();
            double ypos = tunnel.getY();
            tunnels.put(ypos, tunnel);
        }

        tunnelPositions = tunnels;

        return tunnels;
    }*/

    public boolean noGuard() {
        return noGuard;
    }
}
