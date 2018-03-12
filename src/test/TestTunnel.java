package test;

import logic.models.Tunnel;
import logic.configuration.Constants;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestTunnel {
	@Test
	public void TestUpdatePosition() {
		Tunnel t = new Tunnel(1);
		t.setPosition(0, 0);
		t.updatePosition();
        assertEquals(Constants.STARTING_SPEED, t.getY(), 0);
	}

	@Test
	public void TestNoGuard() {
		Tunnel t = new Tunnel(1);
		assertEquals(true, t.noGuard());
	}
}
