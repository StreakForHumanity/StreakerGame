package test;
import logic.models.LongValue;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestLongValue {
	@Test
	public void testConstructor() {
		long l = (long) 4.3;
		LongValue lv = new LongValue(l);
		assertEquals((long) 4.3, lv.getValue(), 0);
	}

	@Test
    public void testSetValue() {
		long l = (long) 3.2;
        LongValue lv = new LongValue(l);
        long l1 = (long)9.4;
		lv.setValue(l1);
        assertEquals((long)9.4, lv.getValue(), 0);
    }
}
