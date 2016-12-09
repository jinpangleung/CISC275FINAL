package tests.grid.griditem.tower;

import static org.junit.Assert.*;

import org.junit.Test;

import model.drawing.Coord;
import model.grid.griditem.tower.RedTower;
import model.grid.griditem.tower.Tower;

public class RedTowerTest {

	@Test
	public void test() {
		RedTower rTower = new RedTower(new Coord(0, 0));
		Tower.initialize(0, 0);
		
		assertEquals(0, RedTower.getRange());
		assertTrue(rTower.isInRange(new Coord(0, 0)));
		assertFalse(rTower.isInRange(new Coord(100,100)));
		assertFalse(rTower.update(0));
		
		assertFalse(rTower.getOpening());
		rTower.mouseDragged(0,0);
		assertTrue(rTower.getOpening());
		rTower.mouseDragged(100,100);
		assertFalse(rTower.getOpening());
		rTower.mouseDragged(0,0);
		rTower.close();
		assertFalse(rTower.getOpening());
	}

}
