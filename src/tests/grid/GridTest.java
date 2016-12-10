package tests.grid;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Model;
import model.Time;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.tower.*;
import model.grid.griditem.trailitem.*;
import model.gui.path.BackToGridBehavior;
import model.gui.path.Path;

public class GridTest {
	
	@Test
	public void removeGridItemTest(){
		(new Model()).initialize(100, 100);
		new Grid(0, 0, 100, 100);
		Animation.initialize();
		
		Oyster o = new Oyster(new Coord(0, 0));
		Larvae l = new Larvae(new Coord(0, 0));
		Pollutant p = new Pollutant(new Coord(0, 0));
		InvasiveItem i = new InvasiveItem(new Coord(0, 0));
		assertTrue(Grid.getInstance().getTrailItems().isEmpty());
		
		Grid.getInstance().addItem(o);
		Grid.getInstance().update(0);
		assertEquals(Grid.getInstance().getTrailItems().size(), 1);
		
		Grid.getInstance().addItem(l);
		Grid.getInstance().update(0);
		assertEquals(Grid.getInstance().getTrailItems().size(), 2);
		
		Grid.getInstance().addItem(p);
		Grid.getInstance().update(0);
		assertEquals(Grid.getInstance().getTrailItems().size(), 3);
		
		Grid.getInstance().addItem(i);
		Grid.getInstance().update(0);
		assertEquals(Grid.getInstance().getTrailItems().size(), 4);
		
		/////////////////////////////////////////////////////////////
		
		Grid.getInstance().removeItem(o);
		Grid.getInstance().update(0);
		assertEquals(Grid.getInstance().getTrailItems().size(), 3);
		
		Grid.getInstance().removeItem(p);
		Grid.getInstance().update(0);
		assertEquals(Grid.getInstance().getTrailItems().size(), 2);
		
		Grid.getInstance().removeItem(l);
		Grid.getInstance().update(0);
		assertEquals(Grid.getInstance().getTrailItems().size(), 1);
		
		Grid.getInstance().removeItem(i);
		Grid.getInstance().update(0);
		assertTrue(Grid.getInstance().getTrailItems().isEmpty());
		
	}
	
	@Test
	public void removePathTest(){
		(new Model()).initialize(100, 100);
		new Grid(0, 0, 10000, 10000);
		Animation.initialize();
		
		Grid.getInstance().addPath(new Path(new Oyster(new Coord(0, 0)), new Coord(1, 1), new BackToGridBehavior()));
		assertTrue(Grid.getInstance().getPaths().isEmpty());
		Grid.getInstance().update(0);
		assertEquals(1, Grid.getInstance().getPaths().size());
		Grid.getInstance().update(100L * Time.nanosecond);
		assertEquals(0, Grid.getInstance().getPaths().size());
		Grid.getInstance().update(0);
		assertTrue(Grid.getInstance().getPaths().isEmpty());
	}
	
	@Test
	public void clickTest(){
		(new Model()).initialize(100, 100);
		new Grid(0, 0, 10000, 10000);
		Animation.initialize();
		assertTrue(Grid.getInstance().getTrailItems().isEmpty());
		
		Grid.getInstance().addItem(new Oyster(new Coord(9999, 9999)));
		Grid.getInstance().update(0);
		assertEquals(Grid.getInstance().getTrailItems().size(), 1);
		
		Grid.getInstance().addItem(new BlueTower(new Coord(9999, 9999)));
		Grid.getInstance().update(0);
		assertEquals(Grid.getInstance().getTowers().size(), 1);
		
		Grid.getInstance().mouseClicked(0, 0);
		Grid.getInstance().update(0);
		assertEquals(Grid.getInstance().getTrailItems().size(), 1);
		
		Grid.getInstance().mouseClicked(9999, 9999);
		Grid.getInstance().update(0);
		assertEquals(Grid.getInstance().getTrailItems().size(), 0);
	}

}
