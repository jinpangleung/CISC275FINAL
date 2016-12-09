package tests.grid;

import static org.junit.Assert.*;

import org.junit.Test;

import model.drawing.Coord;
import model.grid.Board;
import model.grid.Grid;
import model.grid.PixelGrid;
import model.grid.gridcell.GridPosition;

public class PixelGridTest {

	@Test
	public void test() {
		new Grid(10, 10, 100, 100, "board_text_files/gridTest.txt");
		
		PixelGrid pg = PixelGrid.getInstance();
		assertEquals(25.0, pg.getSquareWidth(), 0.0001);
		assertEquals(25.0, pg.getSquareHeight(), 0.0001);
		
		Coord c1 = pg.getCoord(new GridPosition(0, 0));
		assertEquals(22.5, c1.getX(), 0.01);
		assertEquals(11.0, c1.getY(), 0.01);
		
		Coord c2 = pg.getCoord(new GridPosition(1, 1));
		assertEquals(47.5, c2.getX(), 0.01);
		assertEquals(36.0, c2.getY(), 0.01);
	}

}
