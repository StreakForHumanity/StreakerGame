package test;

import logic.models.BackgroundItem;
import logic.models.WorldItem;
import logic.configuration.Constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestBackgroundItem {
	@Test
	public void TestGetSection() {
		BackgroundItem bi = new BackgroundItem(1);
		assertTrue(bi.getSection(0) instanceof WorldItem);
	}

	@Test
	public void TestUpdateBackgroundPositionX() {
		BackgroundItem bi = new BackgroundItem(1);
		WorldItem w1 = bi.getSection(0);
		WorldItem w2 = bi.getSection(1);
		w1.setPosition(0, 0);
		w2.setPosition(0, 0);
		bi.updateBackgroundPosition();
		assertEquals(0, bi.getSection(0).getX(), 0);
	}
	
	@Test
	public void TestUpdateBackgroundPositionY() {
		BackgroundItem bi = new BackgroundItem(1);
		WorldItem w1 = bi.getSection(0);
		WorldItem w2 = bi.getSection(1);
		w1.setPosition(0, 0);
		w2.setPosition(0, 0);
		bi.updateBackgroundPosition();
		assertEquals(Constants.STARTING_SPEED, bi.getSection(0).getY(), 0);
	}
}
