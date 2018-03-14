package test;

import logic.models.Guard;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestGuard {
	// Test assumes the random method
	@Test
	public void TestUpdatePositionX() {
		Guard g = new Guard(false, 1);
		g.setPosition(0,0);
		g.updatePosition();
		double speed = g.getSpeedX();
		assertEquals(speed, g.getX(), 0.0);
	}

	// Test assumes the random method
    @Test
    public void TestUpdatePositionY() {
        Guard g = new Guard(false, 1);
        g.setPosition(0,0);
        g.updatePosition();
        double speed = g.getSpeedY();
        assertEquals(speed, g.getY(), 0.0);
    }
}
