package tests.grid.griditem.gabion;

import static org.junit.Assert.*;

import org.junit.Test;

import model.drawing.Coord;
import model.grid.griditem.gabion.Gabion;
import model.grid.griditem.gabion.ConcreteGabion;

public class ConcreteGabionTest {

	@Test
	public void test() {
		ConcreteGabion cg = new ConcreteGabion(new Coord(0,0));
		assertEquals(10, cg.getHealth());
		cg.setHealth(20);
		assertEquals(20, cg.getHealth());
		cg.takeDamage();
		assertEquals(10, cg.getHealth());
	}

}
