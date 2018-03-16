package test;
import logic.models.Coin;
import logic.configuration.Constants;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestCoin {
	@Test 
	public void testConstructorPositionX() {
		Coin c = new Coin(1);
		assertEquals(true, c.getX() > 0.0 && c.getX() < Constants.SCREEN_WIDTH);
	}
	
	@Test 
	public void testConstructorPositionY() {
		Coin c = new Coin(1);
		assertEquals(true, c.getY() < 0.0 && c.getY() > -(Constants.SCREEN_HEIGHT));
	}

	@Test
	public void testUpdatePositionX() {
		Coin c = new Coin(1);
		double x = c.getX();
		c.updatePosition();
		assertEquals(x, c.getX(), 0);
	}
	
	@Test
	public void testUpdatePositionY() {
		Coin c = new Coin(1);
		double y = c.getY();
		c.updatePosition();
		assertEquals(y+Constants.STARTING_SPEED, c.getY(), 0);
	}
}
