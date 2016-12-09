package tests.grid.griditem.tower;

import static org.junit.Assert.*;

import org.junit.Test;

import model.drawing.Coord;
import model.grid.griditem.tower.BlueTower;
import model.grid.griditem.tower.Tower;

public class BlueTowerTest {

	@Test
	public void test() {
		BlueTower bTower = new BlueTower(new Coord(0, 0));
		Tower.initialize(0, 0);
		
		assertEquals(0, BlueTower.getRange());
		assertTrue(bTower.isInRange(new Coord(0, 0)));
		assertFalse(bTower.isInRange(new Coord(100,100)));
		assertFalse(bTower.update(0));
		
	}

}
