package tests.drawing;

import static org.junit.Assert.*;
import model.drawing.Coord;
import model.moving.Velocity;

import org.junit.Test;

public class CoordTest {

	@Test
	public void test() {
		
		Coord c1 = new Coord(0, 0);
		Coord c2 = new Coord(0.00001, 0.00001);
		Coord c3 = new Coord(10000.1, 1.9999);
		
		assertEquals(c1.getX(), Double.valueOf(0));
		assertEquals(c1.getY(), Double.valueOf(0));
		assertEquals(c2.getX(), Double.valueOf(0.00001));
		assertEquals(c2.getY(), Double.valueOf(0.00001));
		assertEquals(c3.getX(), Double.valueOf(10000.1));
		assertEquals(c3.getY(), Double.valueOf(1.9999));
		
		c1.add(0.1, 0.2);
		assertEquals(c1.getX(), Double.valueOf(0.1));
		assertEquals(c1.getY(), Double.valueOf(0.2));
		
		c3.add(-10000.1, -1.9999);
		assertEquals(c3.getX(), Double.valueOf(0));
		assertEquals(c3.getY(), Double.valueOf(0));
		
		c2.add(-0.00002, -0.00002);
		assertEquals(c2.getX(), Double.valueOf(-0.00001));
		assertEquals(c2.getY(), Double.valueOf(-0.00001));
		
	}
	
	@Test
	public void testEquals(){
		Coord c1 = new Coord(10, 10);
		Coord c2 = new Coord(10, 10);
		Coord c3 = new Coord(10, 9);
		Coord c4 = new Coord(9, 10);
		Coord c5 = new Coord(9, 9);
		Velocity v1 = new Velocity(10, 10);
		
		assertTrue(c1.equals(c1));
		assertTrue(c1.equals(c2));
		assertTrue(c2.equals(c1));
		assertFalse(c1.equals(c3));
		assertFalse(c1.equals(c4));
		assertFalse(c1.equals(c5));
		assertFalse(c1.equals(v1));
		assertFalse(c3.equals(c4));
		assertFalse(v1.equals(c1));
	}

}
