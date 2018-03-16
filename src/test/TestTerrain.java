package test;

import logic.models.Terrain;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.configuration.Constants;

public class TestTerrain {
	@Test
	public void testUpdatePositionX() {
		Terrain t = new Terrain(1);
		t.setPosition(0, 0);
		t.updatePosition();
		assertEquals(0.0, t.getX(), 0);
	}
	
	@Test
	public void testUpdatePositionY() {
		Terrain t = new Terrain(1);
		t.setPosition(0, 0);
		t.updatePosition();
		assertEquals(Constants.STARTING_SPEED, t.getY(), 0);
	}
}
