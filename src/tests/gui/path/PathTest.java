package tests.gui.path;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Time;
import model.drawing.*;
import model.grid.Grid;
import model.grid.griditem.*;
import model.grid.griditem.trailitem.*;
import model.gui.path.*;

public class PathTest {

	@Test
	public void testConstruction() {
		new Grid(0, 0, 1000, 1000);
		Oyster o = new Oyster(new Coord(0, 0));
		Path p1 = new Path(o, new Coord(0, 100), new BackToGridBehavior());
		
		assertTrue(p1.getSpeed() == 0.0000001);
		assertEquals(Math.toRadians(90), p1.getAngle(), 0.00000001);
		assertEquals(p1.getVelocity().getX(), Double.valueOf(0), 0.00000001);
		assertEquals(p1.getVelocity().getY(), Double.valueOf(0.0000001), 0.00000001);
	}
	
	@Test
	public void testUpdate(){
		new Grid(0, 0, 1000, 1000);
		GridItem o = new Oyster(new Coord(0, 0));
		Path p1 = new Path(o, new Coord(0, 100), new BackToGridBehavior());
		
		assertTrue(Grid.getInstance().getTrailItems().isEmpty());
		
		p1.update(Time.nanosecond);
		
		assertEquals(o.getCoord().getX(), Double.valueOf(0), 0.00000001);
		assertEquals(o.getCoord().getY(), Double.valueOf(100), 0.0000001);
		assertEquals(Grid.getInstance().getTrailItems().size(), 1);
		
		Grid.getInstance().removeItem(o);
		
		o = new Oyster(new Coord(0, 0));
		Path p2 = new Path(o, new Coord(100, 0), new BackToGridBehavior());
		
		p2.update(Time.nanosecond / 2);
		assertEquals(o.getCoord().getX(), Double.valueOf(50), 0.00000001);
		assertEquals(o.getCoord().getY(), Double.valueOf(0), 0.0000001);
		assertTrue(Grid.getInstance().getTrailItems().isEmpty());
		
		o = new Oyster(new Coord(0, 0));
		Path p3 = new Path(o, new Coord(100, 100), new BackToGridBehavior());
		
		p3.update(Time.nanosecond);
		assertEquals(o.getCoord().getX(), Double.valueOf(100), 0.00000001);
		assertEquals(o.getCoord().getY(), Double.valueOf(100), 0.0000001);
		assertEquals(Grid.getInstance().getTrailItems().size(), 1);
		
		Grid.getInstance().removeItem(o);
		
		o = new Oyster(new Coord(100, 100));
		Path p4 = new Path(o, new Coord(0, 0), new BackToGridBehavior());
		
		p4.update(Time.nanosecond);
		assertEquals(o.getCoord().getX(), Double.valueOf(0), 0.00000001);
		assertEquals(o.getCoord().getY(), Double.valueOf(0), 0.0000001);
		assertEquals(Grid.getInstance().getTrailItems().size(), 1);
	}

}
