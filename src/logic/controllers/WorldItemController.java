package logic.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logic.configuration.Constants;
import logic.configuration.Globals;
import logic.models.*;

public class WorldItemController {

    private List<Coin> coins;
    private List<Tunnel> tunnels;
    private List<Guard> guards;
    private List<Terrain> terrains;
    private StreakerController characterController;
    private BackgroundItem background;

    public WorldItemController() {
        coins = Coin.createCoins();
        tunnels = Tunnel.createTunnels();
        guards = new ArrayList<>();
        terrains = new ArrayList<>();
        //what the fuck???
        characterController = new StreakerController();
        background = new BackgroundItem();
    }

    public BackgroundItem getBackground() {
        return background;
    }

    public StreakerController getCharacterController() {
        return characterController;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public List<Tunnel> getTunnels() {
        return tunnels;
    }

    public List<Guard> getGuards() {
        return guards;
    }

    public List<Terrain> getTerrains() {
        return terrains;
    }

    public void updateCharacterState(double elapsedTime, List<String> input) {
        characterController.getStreaker().updatePosition(elapsedTime, input, charIsInMud(), characterController.isJumping());
        characterController.getStreaker().handleCharacterPosition();
        handleGuardCollisions();
        characterController.handleJump(input);
    }


    //currently only checks for collisions w mud, but could add guards
    private boolean charIsInMud() {
        boolean isInMud = false;
        for (Terrain t : terrains) {
            if (characterController.getStreaker().intersects(t.getBoundary())) {
                isInMud = true;
                break;
            }
        }
        return isInMud;
    }

    private void handleGuardCollisions() {
        for (Guard g : guards) {
            if (characterController.getStreaker().intersects(g.getBoundary())) {
                characterController.getStreaker().changeHealth(Constants.GUARD_DAMAGE);
                break;
            }
        }
    }

    /* checks coins for intersection with characterController - if so,
     * resets their positions randomly and increments
     * collected point tally, returning total upon
     * method's completion
     */
    public int updateCoinStates() {
        int collected = 0;
        for (Coin coin : coins) {
            if (coin.getY() > Constants.SCREEN_HEIGHT) {
                coin.resetPosition();
            }
            if (characterController.intersects(coin.getBoundary())) {
                coin.resetPosition();
                collected += 10;
            }
            coin.updatePosition();
        }
        return collected;
    }

    /* updates tunnel positions according to time, checks
     * whether tunnels are still contained in bounds of screen.
     * intermittently spawns guards from tunnels, adding a
     * guard reference to the controller's guards list.
     */
    public void updateTunnelStates() {
        for (Tunnel tunnel : tunnels) {
            if (tunnel.getY() > Constants.SCREEN_HEIGHT) {
                tunnel.resetPosition();
            } else {
                if (tunnel.noGuard() && Math.random() < Constants.GUARD_SPAWN_RATE*Globals.SETTINGS_MULTIPLIER) {
                    //There should be a better way to do this
                    guards.add(tunnel.spawnGuard());
                }
            }
            tunnel.updatePosition();
        }
    }

    public void updateGuardStates() {
        Iterator<Guard> iter = guards.iterator();
        while (iter.hasNext()) {
            Guard guard = iter.next();
            guard.updatePosition();
            //added getY() tests to account for guards' new motion abilities 
            if (guard.getX() < 0.0 || guard.getX() > Constants.SCREEN_WIDTH ||
                    guard.getY() > Constants.SCREEN_HEIGHT ||
                    guard.getY() < -Constants.SCREEN_HEIGHT) {
                iter.remove();
            }
        }
    }

    public void updateBackgroundState() {
        background.loop();
        background.updateBackgroundPosition();
    }

    public void updateTerrainStates() {
        Iterator<Terrain> iter = terrains.iterator();
        while (iter.hasNext()) {
            Terrain terrain = iter.next();
            if (terrain.getY() > Constants.SCREEN_HEIGHT) {
                iter.remove();
                continue;
            }
            terrain.updatePosition();
        }
        if (Math.random() < Constants.TERRAIN_SPAWN_RATE) {
            terrains.add(createTerrain());
        }
    }

    // ensures that terrain items do not overlap
    private Terrain createTerrain() {
        boolean positionIsSafe = false;
        Terrain ter = new Terrain();
        return createTerrainHelper(positionIsSafe, ter);
    }

    private Terrain createTerrainHelper(boolean positionIsSafe, Terrain ter) {
        while (!positionIsSafe) {
            ter = new Terrain();
            for (Terrain t : terrains) {
                if (ter.intersects(t.getBoundary())) {
                    break;
                }
            }
            positionIsSafe = true;
        }
        return ter;
    }
}
