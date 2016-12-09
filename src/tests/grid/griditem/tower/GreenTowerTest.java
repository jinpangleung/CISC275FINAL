package tests.grid.griditem.tower;

import static org.junit.Assert.*;

import org.junit.Test;

import model.drawing.Coord;
import model.grid.griditem.tower.GreenTower;
import model.grid.griditem.tower.Tower;

public class GreenTowerTest {

	@Test
	public void test() {
		GreenTower gTower = new GreenTower(new Coord(0, 0));
		Tower.initialize(0, 0);
		
		assertEquals(0, GreenTower.getRange());
		assertTrue(gTower.isInRange(new Coord(0, 0)));
		assertFalse(gTower.isInRange(new Coord(100,100)));
		assertFalse(gTower.update(0));
		
	}

}
