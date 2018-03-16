package test;

import logic.models.AnimatedItem;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestAnimatedItem {
	@Test
	public void testSetDuration() {
		AnimatedItem a = new AnimatedItem();
		a.setDuration(4.0);
		assertEquals(4.0, a.getDuration(), 0);
	}
	@Test
	public void testAlterDuration() {
		AnimatedItem a = new AnimatedItem();
		a.setDuration(4.0);
		a.alterDuration(.3);
		assertEquals(4.3, a.getDuration(), 0);
	}
}
