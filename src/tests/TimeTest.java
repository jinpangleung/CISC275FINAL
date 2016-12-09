package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Time;

public class TimeTest {

	@Test
	public void test() {
		Time t = new Time();
		
		assertEquals(5000000000L, t.secondToNano(5));
		
		assertEquals(5, t.nanoToSecond(5000000000L));
		
		assertEquals(5000L, t.secondToMilli(5));
		
		assertEquals(5, t.milliToSecond(5000L));
		
		assertEquals(5000000000L, t.milliToNano(5000L));
		
		assertEquals(5000L, t.nanoToMilli(5000000000L));
	}

}
