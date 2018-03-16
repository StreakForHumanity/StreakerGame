package test;

import logic.models.Coin;
import logic.models.Tunnel;
import logic.controllers.WorldItemController;
import logic.configuration.Constants;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LoopTest {
	@Test
    public void testCreateCoins() {
        List<Coin> coins = Coin.createDumbCoins();
        assertEquals(Constants.NUM_COINS, coins.size());
    }
	
	@Test
	public void testCreateTunnels() {
		List<Tunnel> tunnels = Tunnel.createDumbTunnels();
		assertEquals(Constants.NUM_TUNNELS, tunnels.size());
	}

	@Test
	public void testWorldItemCoins() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.coins);
		List<Coin> coins = wic.getCoins();
		assertEquals(Constants.NUM_COINS, coins.size());
	}
	
	@Test
	public void testWorldItemTunnels() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.tunnels);
		List<Tunnel> tunnels = wic.getTunnels();
		assertEquals(Constants.NUM_TUNNELS, tunnels.size());
	}
	
	@Test
	public void testWorldItemCoinStates() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.coins);
		int s = wic.getCoins().size();
		assertEquals(s, wic.updateDumbCoinStates());
	}

	@Test
	public void testWorldItemTunnelStates() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.tunnels);
		int s = wic.getTunnels().size();
		assertEquals(s, wic.updateDumbTunnelStates());
	}
	
	@Test
	public void testWorldItemGuardStates() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.guards);
		int s = wic.getGuards().size();
		assertEquals(s, wic.updateDumbGuardStates());
	}

	@Test
	public void testWorldItemMud() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.guards);
		int s = wic.getTerrains().size();
		assertEquals(s, wic.charIsInMudTest());
	}

	@Test
	public void testWorldItemAlterLastCoin() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.coins);
		int s = wic.getCoins().size();
		assertEquals(s, wic.editLastCoin(3.2, 1.1));
	}
	
	@Test
	public void testWorldItemAlterLastCoinUninitiated() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.guards);
		int s = wic.getCoins().size();
		assertEquals(s, wic.editLastCoin(3.2, 1.1));
	}
}
