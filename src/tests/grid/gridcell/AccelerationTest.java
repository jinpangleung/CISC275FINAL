package tests.grid.gridcell;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Time;
import model.grid.gridcell.Acceleration;

public class AccelerationTest {

	@Test
	public void test() {
		Acceleration a1 = new Acceleration(100, -100);
		Acceleration a2 = a1.accelerationByTime(Time.nanosecond);
		
		assertEquals(100L*Time.nanosecond, a2.getX(), 0.01);
		assertEquals(-100L*Time.nanosecond, a2.getY(), 0.01);
	}

}
