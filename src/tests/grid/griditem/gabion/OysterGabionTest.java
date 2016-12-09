package tests.grid.griditem.gabion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.drawing.Coord;
import model.grid.griditem.gabion.OysterGabion;

public class OysterGabionTest {
	@Test
	public void test() {
		OysterGabion og = new OysterGabion(new Coord(0,0));
		assertEquals(10, og.getHealth());
		og.setHealth(20);
		assertEquals(20, og.getHealth());
		og.takeDamage();
		assertEquals(10, og.getHealth());
	}
}
