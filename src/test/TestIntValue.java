package test;

import logic.models.IntValue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestIntValue {
	@Test
	public void testConstructor() {
		IntValue iv = new IntValue(5);
		assertEquals(5, iv.getValue());
	}

	@Test
	public void testSetValue() {
		IntValue iv = new IntValue(0);
		iv.setValue(3);
		assertEquals(3, iv.getValue());
	}
}
