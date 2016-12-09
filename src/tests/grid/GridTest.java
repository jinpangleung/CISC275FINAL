package tests.grid;

import static org.junit.Assert.*;

import org.junit.Test;

import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.tower.*;
import model.grid.griditem.trailitem.*;

public class GridTest {
	
	@Test
	public void removeGridItemTest(){
		new Grid(0, 0, 100, 100);
		
		Oyster o = new Oyster(new Coord(0, 0));
		Larvae l = new Larvae(new Coord(0, 0));
		Pollutant p = new Pollutant(new Coord(0, 0));
		InvasiveItem i = new InvasiveItem(new Coord(0, 0));
		assertTrue(Grid.getInstance().getTrailItems().isEmpty());
		
		Grid.getInstance().addItem(o);
		assertEquals(Grid.getInstance().getTrailItems().size(), 1);
		
		Grid.getInstance().addItem(l);
		assertEquals(Grid.getInstance().getTrailItems().size(), 2);
		
		Grid.getInstance().addItem(p);
		assertEquals(Grid.getInstance().getTrailItems().size(), 3);
		
		Grid.getInstance().addItem(i);
		assertEquals(Grid.getInstance().getTrailItems().size(), 4);
		
		/////////////////////////////////////////////////////////////
		
		Grid.getInstance().removeItem(o);
		assertEquals(Grid.getInstance().getTrailItems().size(), 3);
		
		Grid.getInstance().removeItem(p);
		assertEquals(Grid.getInstance().getTrailItems().size(), 2);
		
		Grid.getInstance().removeItem(l);
		assertEquals(Grid.getInstance().getTrailItems().size(), 1);
		
		Grid.getInstance().removeItem(i);
		assertTrue(Grid.getInstance().getTrailItems().isEmpty());
		
	}
	
	@Test
	public void removePathTest(){
		new Grid(0, 0, 100, 100);
		
		// TODO
		// Create some Paths, add them to the Grid, remove them
	}
	
	@Test
	public void clickTest(){
		new Grid(0, 0, 10000, 10000);
		assertTrue(Grid.getInstance().getTrailItems().isEmpty());
		
		Grid.getInstance().addItem(new Oyster(new Coord(9999, 9999)));
		assertEquals(Grid.getInstance().getTrailItems().size(), 1);
		
		Grid.getInstance().addItem(new BlueTower(new Coord(9999, 9999)));
		assertEquals(Grid.getInstance().getTrailItems().size(), 1);
		
		Grid.getInstance().mouseClicked(0, 0);
		assertEquals(Grid.getInstance().getTrailItems().size(), 1);
		
		Grid.getInstance().mouseClicked(9999, 9999);
		assertEquals(Grid.getInstance().getTrailItems().size(), 0);
	}

}
