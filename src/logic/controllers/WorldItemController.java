package logic.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logic.configuration.Constants;
import logic.models.*;

public class WorldItemController {
	
	public ArrayList<Coin> coins;
	public ArrayList<Tunnel> tunnels;
	public ArrayList<Guard> guards;
	public ArrayList<Terrain> terrains;
	public StreakerController characterController;
	public BackgroundItem background;
	
	public WorldItemController() {
		coins = Coin.createCoins();
		tunnels = Tunnel.createTunnels();
		guards = new ArrayList<>();
		terrains = new ArrayList<>();
		//what the fuck???
		characterController = new StreakerController();
		background = new BackgroundItem();
	}
	
	public void updateCharacterState(double elapsedTime, List<String> input) {
		characterController.streaker.updatePosition(elapsedTime, input, charIsInMud(), characterController.isJumping);
        characterController.streaker.handleCharacterPosition();
        handleGuardCollisions();
        characterController.handleJump(input);
	}
	
	
	//currently only checks for collisions w mud, but could add guards
	private boolean charIsInMud() {
		boolean isInMud = false;
		for (Terrain t : terrains) {
			if (characterController.streaker.intersects(t.getBoundary())) {
				isInMud = true;
				break;
			}
		}
		return isInMud;
	}
	
	private void handleGuardCollisions() {
		boolean isTouching = false;
		for (Guard g : guards) {
			if (characterController.streaker.intersects(g.getBoundary())) {
				isTouching = true;
				characterController.streaker.changeHealth(Constants.GUARD_DAMAGE);
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
                if (tunnel.noGuard()) {
                    //There should be a better way to do this
                    if (Math.random() < Constants.GUARD_SPAWN_RATE) {
                        guards.add(tunnel.spawnGuard());
                    }
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
                    guard.getY() < - Constants.SCREEN_HEIGHT) {
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
		Terrain ter = new Terrain(); {
			while (!positionIsSafe) {
				ter = new Terrain();
				for (Terrain t : terrains) {
					if (ter.intersects(t.getBoundary())) {
						break;
					}
				}
				positionIsSafe = true;
			}
		}
		
		return ter;
	}
}
