package test;

import logic.controllers.StreakerController;
import logic.configuration.Constants;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestStreakerController {
	@Test
	public void TestStreakerInitializationIsJumping() {
		StreakerController sc = new StreakerController(1);
		assertEquals(false, sc.getIsJumping());
	}
	
	@Test
	public void TestStreakerInitializationHealth() {
		StreakerController sc = new StreakerController(1);
		logic.models.Character c = sc.getStreaker();
		assertEquals(Constants.CHAR_MAX_HEALTH, c.getHealth(), 0);
	}
}
