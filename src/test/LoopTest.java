package test;

import logic.models.Coin;
import logic.configuration.Constants;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LoopTest {
	@Test
    public void TestCreateCoins() {
        List<Coin> coins = Coin.createDumbCoins();
        assertEquals(Constants.NUM_COINS, coins.size());
    }
	
	@Test
	public void TestCreateTunnels() {
		List<Tunnel> tunnels = Tunnel.createDumbTunnels();
		assertEquals(Constants.NUM_TUNNELS, tunnels.size());
	}

	@Test
	public void TestWorldItemCoins() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.Coins);
		List<Coin> coins = wic.getCoins();
		assertEquals(Constants.NUM_COINS, coins.size());
	}
	
	@Test
	public void TestWorldItemTunnels() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.Tunnels);
		List<Tunnel> tunnels = wic.getTunnels();
		assertEquals(Constants.NUM_TUNNELS, tunnels.size());
	}
	
	@Test
	public void TestWorldItemCoinStates() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.Coins);
		int s = wic.getCoins().size();
		assertEquals(s, wic.updateDumbCoinStates());
	}

	@Test
	public void TestWorldItemTunnelStates() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.Tunnels);
		int s = wic.getTunnels().size();
		assertEquals(s, wic.updateDumbTunnelStates());
	}
	
	@Test
	public void TestWorldItemGuardStates() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.Guards);
		int s = wic.getGuards().size();
		assertEquals(s, wic.updateDumbGuardStates());
	}

	@Test
	public void TestWorldItemMud() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.Guards);
		int s = wic.getTerrains().size();
		assertEquals(s, wic.charIsInMudTest());
	}

	@Test
	public void TestWorldItemAlterLastCoin() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.Coins);
		int s = wic.getCoins().size();
		assertEquals(s, wic.editLastCoin(3.2, 1.1));
	}
	
	@Test
	public void TestWorldItemAlterLastCoinUninitiated() {
		WorldItemController wic = new WorldItemController(WorldItemController.TEST_TYPE.Guards);
		int s = wic.getCoins().size();
		assertEquals(s, wic.editLastCoin(3.2, 1.1));
	}
}
