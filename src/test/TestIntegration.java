package test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import logic.controllers.StreakerController;
import logic.configuration.Constants;

public class TestIntegration {
	
	@Test
	public void TestCharacterAndControllerHealth() {
		StreakerController sc = new StreakerController(1);
		double health = sc.getStreaker().getHealth();
		assertEquals(Constants.CHAR_MAX_HEALTH, health, 0);
	}
	
	@Test
	public void TestCharacterAndControllerSpeedX() {
		StreakerController sc = new StreakerController(1);
		double speedX = sc.getStreaker().getSpeedX();
		assertEquals(0.0, speedX, 0.0);
	}
	
	@Test
	public void TestCharacterAndControllerSpeedY() {
		StreakerController sc = new StreakerController(1);
		double speedY = sc.getStreaker().getSpeedY();
		assertEquals(0.0, speedY, 0.0);
	}
	
	@Test
	public void TestCharacterAndControllerVeloX() {
		StreakerController sc = new StreakerController(1);
		double velX = sc.getStreaker().getVelocityX();
		assertEquals(0.0, velX, 0.0);
	}
	
	@Test
	public void TestCharacterAndControllerVeloY() {
		StreakerController sc = new StreakerController(1);
		double velX = sc.getStreaker().getVelocityX();
		assertEquals(0.0, velX, 0.0);
	}
	
	@Test
	public void TestCharacterAndControllerCooldown() {
		StreakerController sc = new StreakerController(1);
		double cooldown = sc.getStreaker().getCooldown();
		assertEquals((Constants.COOLDOWN_TIME / 1000.0), cooldown, 0.0);
	}
}

