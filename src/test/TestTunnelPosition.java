package test;

import logic.models.TunnelPosition;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestTunnelPosition {
	@Test
	public void testGetPosition() {
		TunnelPosition tp = new TunnelPosition(4.2);
		assertEquals(4.2, tp.getPosition(), 0);
	}

	@Test
	public void testTunnelEquality() {
		TunnelPosition tp1 = new TunnelPosition(3.2);
		TunnelPosition tp2 = new TunnelPosition(3.2);
		boolean isEqual = tp1.equals(tp2);
		assertEquals(true, isEqual);
	}
}
