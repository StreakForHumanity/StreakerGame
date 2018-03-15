package logic.models;

import java.util.List;
import java.util.Random;

import logic.configuration.Constants;
import logic.configuration.Globals;
import logic.configuration.Paths;

import java.util.ArrayList;

public class Tunnel extends WorldItem {

    private boolean noGuard;
    private boolean left;
    private Random rand;
    private static ArrayList<TunnelPosition> tunnelPositions;

    public Tunnel() {
        rand = new Random();
        noGuard = true;
        this.establishPosition();
    }

    public Tunnel(int test) {
    		rand = new Random();
        noGuard = true;
    }

    @Override
    public void updatePosition() {
    		this.setSpeed(0, Constants.STARTING_SPEED);
    		super.updatePosition();
    }

    public void resetPosition() {
        left = rand.nextBoolean();
        double x;
        if(left) {
            this.setImage(Paths.TUNNEL_PATHS[0]);
            x = Constants.STADIUM_MARGIN_LEFT;
        }
        else {
            this.setImage(Paths.TUNNEL_PATHS[1]);
            x = Constants.SCREEN_WIDTH - (double)Constants.STADIUM_MARGIN_RIGHT;
        }
        this.setPosition(x, - (3 * Constants.SCREEN_HEIGHT / 4));
        this.noGuard = true;

    }

    public void establishPosition() {
        left = rand.nextBoolean();
        double y = - (Constants.SCREEN_HEIGHT * rand.nextDouble());
        while (tunnelPositions.contains(new TunnelPosition(y))) {
            y = - (Constants.SCREEN_HEIGHT * rand.nextDouble());
        }
        TunnelPosition initialY = new TunnelPosition(y);
        tunnelPositions.add(initialY);

        double x;
        if (left) {
            this.setImage(Paths.TUNNEL_PATHS[0]);
            x = Constants.STADIUM_MARGIN_LEFT;
        } else {
            this.setImage(Paths.TUNNEL_PATHS[1]);
            x = Constants.SCREEN_WIDTH - (double)Constants.STADIUM_MARGIN_RIGHT;
        }
        this.setPosition(x, y);
        this.noGuard = true;
    }

    public Guard spawnGuard() {
        Guard g = new Guard(left);
        g.setPosition(getX(), getY());
        noGuard = false;
        return g;
    }


    public static List<Tunnel> createTunnels() {
        tunnelPositions = new ArrayList<>();
        List<Tunnel> tunnels = new ArrayList<>();
        for (int i = 0; i < Constants.NUM_TUNNELS + Globals.TUNNELS_MODIFIER; i++) {
            Tunnel tunnel = new Tunnel();
            tunnels.add(tunnel);
        }
        return tunnels;
    }

    public boolean noGuard() {
        return noGuard;
    }
}
