package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.tower.GreenTower;
import model.grid.griditem.tower.Tower;
import model.gui.path.Path;
import model.gui.path.TowerBehavior;

public class TowerBehaviorTest {

	@Test
	public void test() {
		
		Tower t = new GreenTower(new Coord(0,0));
		Path p = new Path(t, new Coord (0,0), new TowerBehavior());
		Grid.getInstance().addPath(p);
		assertEquals(t, Grid.getInstance().getTowers());
		
		
	}

}
