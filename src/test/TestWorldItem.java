package test;
import logic.models.WorldItem;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestWorldItem {

	@Test
	public void TestSetPosition() {
		WorldItem w = new WorldItem(1);
		w.setPosition(1.6, 6.3);
		assertEquals(1.6, w.getX(), 0);
	}

	@Test
	public void TestUpdatePosition() {
		WorldItem w = new WorldItem(1);
		w.setPosition(1.6, 6.3);
		w.setSpeed(7.3, 1.2);
		w.updatePosition();
		assertEquals(7.5, w.getY(), 0);
	}

}
