package tests.grid.griditem.trailitem;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Model;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.tower.BlueTower;
import model.grid.griditem.tower.Tower;
import model.grid.griditem.trailitem.Oyster;

public class TrailItemTest {

	@Test
	public void test() {
		Model m = new Model();
		m.initialize(100, 100);
		Animation.initialize();
		
		Grid grid = new Grid(0, 0, 100, 100);
		Tower t = new BlueTower(new Coord(1,1));
		Oyster o = new Oyster(new Coord(1,1));
		grid.addItem(t);
		grid.addItem(o);

		assertEquals(true, o.click());
	}

}
