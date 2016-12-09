package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Model;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.trailitem.Oyster;
import model.gui.path.BackToGridBehavior;
import model.gui.path.Path;

public class BackToGridBehaviorTest {

	@Test
	public void test() {
		Model m = new Model();
		m.initialize(100, 100);
		Animation.initialize();
		
		
		Oyster gi = new Oyster(new Coord(0,0));
		Path p = new Path(gi, new Coord (0,0), new BackToGridBehavior());
		Grid.getInstance().addPath(p);
		assertEquals(gi, Grid.getInstance().getTrailItems());
		
		
	}

}
