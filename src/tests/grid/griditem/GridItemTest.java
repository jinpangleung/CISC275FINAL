package tests.grid.griditem;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Model;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Board;
import model.grid.Grid;
import model.grid.PixelGrid;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridColor;
import model.grid.griditem.GridItem;
import model.grid.griditem.trailitem.Oyster;

public class GridItemTest {

	@Test
	public void test() {
		Model m = new Model();
		m.initialize(100, 100);
		Animation.initialize();
		//Grid g = new Grid(0, 0, 100, 100);
		//Board board = new Board("board_text_files/gridTest.txt");
		//PixelGrid pg = new PixelGrid(board);
		Oyster o = new Oyster(new Coord(0, 0));
		//g.addItem(o);
		assertEquals(GridColor.BLUE, o.getGridColor());
		
		o.setGridColor(GridColor.GREEN);
		
		assertEquals(GridColor.GREEN, o.getGridColor());
		
		GridPosition gp = new GridPosition(1,1);
		
		o.setGridPosition(gp);
		
		assertEquals(gp, o.getGridPosition());
		
	}

}
