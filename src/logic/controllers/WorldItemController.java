package logic.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logic.configuration.Constants;
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
        characterController = new StreakerController();
        background = new BackgroundItem();
    }
    
    public enum TEST_TYPE {
        COINS,
        GUARDS,
        TERRAINS,
        TUNNELS
    }

    //testing contructor
    public WorldItemController(TEST_TYPE type) {
        coins = new ArrayList<>();
        tunnels = new ArrayList<>();
        guards = new ArrayList<>();
        terrains = new ArrayList<>();
        characterController = new StreakerController(1);
        background = null;
        switch(type) {
            case COINS:
                coins = Coin.createDumbCoins();
                break;
            case TUNNELS:
                tunnels = Tunnel.createDumbTunnels();
                break;
            case TERRAINS:
            		
            case GUARDS:

            default:
            	
        }

    }
    
    // takes values to edit position of last coin
    // returns number of iterations
    public int editLastCoin(double x, double y) {
    		int count = 0;
    		for(int i = 0; i < coins.size(); i++) {
    			if(i == coins.size() -1) {
    				Coin c = coins.get(i);
    				c.setPosition(x, y);
    			}
    			count++;
    		}
    		return count;
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


    //currently only checks for collisions w mud, but could add GUARDS
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
    
    // test
    public int charIsInMudTest() {
        int count = 0;
        for (Terrain t : terrains) {
        		count ++;
            if (characterController.getStreaker().intersects(t.getBoundary())) {
                break;
            }
        }
        return count;
    }

    private void handleGuardCollisions() {
        for (Guard g : guards) {
            if (characterController.getStreaker().intersects(g.getBoundary())) {
                characterController.getStreaker().changeHealth(Constants.GUARD_DAMAGE);
                break;
            }
        }
    }

    /* checks COINS for intersection with characterController - if so,
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
    
    // test method
    public int updateDumbCoinStates() {
    		int i = 0;
        for (Coin coin : coins) {
        		i++;
            if (coin.getY() > Constants.SCREEN_HEIGHT) {
                coin.resetPosition();
            }
            coin.updatePosition();
        }
        return i;
    }

    /* updates tunnel positions according to time, checks
     * whether TUNNELS are still contained in bounds of screen.
     * intermittently spawns GUARDS from TUNNELS, adding a
     * guard reference to the controller's GUARDS list.
     */
    public void updateTunnelStates() {
        for (Tunnel tunnel : tunnels) {
            if (tunnel.getY() > Constants.SCREEN_HEIGHT) {
                tunnel.resetPosition();
            } else {
                if (tunnel.noGuard() && Math.random() < Constants.GUARD_SPAWN_RATE) {
                    //There should be a better way to do this
                    guards.add(tunnel.spawnGuard());
                }
            }
            tunnel.updatePosition();
        }
    }
    
    //test 
    public int updateDumbTunnelStates() {
    	int count = 0;
        for (Tunnel tunnel : tunnels) {
        		count++;
            if (tunnel.getY() > Constants.SCREEN_HEIGHT) {
                tunnel.resetPosition();
            } 
            tunnel.updatePosition();
        }
        return count;
    }

    public void updateGuardStates() {
        Iterator<Guard> iter = guards.iterator();
        while (iter.hasNext()) {
            Guard guard = iter.next();
            guard.updatePosition();
            //added getY() tests to account for GUARDS' new motion abilities
            if (guard.getX() < 0.0 || guard.getX() > Constants.SCREEN_WIDTH ||
                    guard.getY() > Constants.SCREEN_HEIGHT ||
                    guard.getY() < -Constants.SCREEN_HEIGHT) {
                iter.remove();
            }
        }
    }
    
    //test
    public int updateDumbGuardStates() {
        int count = 0;
        for(Guard g: guards){
        		count++;
            Guard guard = g;
            guard.updatePosition();
            //added getY() tests to account for GUARDS' new motion abilities
        }
        return count;
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
