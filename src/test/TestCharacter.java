package test;
import logic.models.Character;
import logic.configuration.Constants;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestCharacter {
	@Test
	public void TestInputVelocityNormalX() {
		Character c = new Character(1);
		List<String> inputs = new ArrayList<>();
		inputs.add("LEFT");
		c.applyUserInputToVelocity(inputs, false, false);
		assertEquals(-(Constants.CHARACTER_VELOCITY), c.getVelocityX(), 0);
	}
	
	@Test
	public void TestInputVelocityNormalY() {
		Character c = new Character(1);
		List<String> inputs = new ArrayList<>();
		inputs.add("LEFT");
		c.applyUserInputToVelocity(inputs, false, false);
		assertEquals(-(Constants.CHARACTER_VELOCITY), c.getVelocityY(), 0);
	}
	
	@Test
	public void TestInputVelocityInMudX() {
		Character c = new Character(1);
		List<String> inputs = new ArrayList<>();
		inputs.add("LEFT");
		c.applyUserInputToVelocity(inputs, true, false);
		assertEquals(-(Constants.CHARACTER_VELOCITY)/2, c.getVelocityX(), 0);
	}
	
	@Test
	public void TestInputVelocityInMudY() {
		Character c = new Character(1);
		List<String> inputs = new ArrayList<>();
		inputs.add("LEFT");
		c.applyUserInputToVelocity(inputs, true, false);
		assertEquals(-(Constants.CHARACTER_VELOCITY)/2, c.getVelocityY(), 0);
	}

	@Test
	public void TestInputVelocityInMudJumpingX() {
		Character c = new Character(1);
        List<String> inputs = new ArrayList<>();
        inputs.add("LEFT");
        c.applyUserInputToVelocity(inputs, true, true);
        assertEquals(-(Constants.CHARACTER_VELOCITY), c.getVelocityX(), 0);
	}
	
	@Test
	public void TestInputVelocityInMudJumpingY() {
		Character c = new Character(1);
        List<String> inputs = new ArrayList<>();
        inputs.add("LEFT");
        c.applyUserInputToVelocity(inputs, true, true);
        assertEquals(-(Constants.CHARACTER_VELOCITY), c.getVelocityY(), 0);
	}

	@Test
	public void TestChangeHealth() {
		Character c = new Character(1);
		double diff = -15.5;
		c.changeHealth(diff);
		assertEquals(Constants.CHAR_MAX_HEALTH+diff, c.getHealth(), 0);
	}

}
