package tests.grid.gridcell;

import static org.junit.Assert.*;

import org.junit.Test;

import model.grid.gridcell.Direction;
import model.grid.gridcell.GridCell;
import model.grid.gridcell.GridPosition;

public class GridCellTest {

	@Test
	public void test() {
		GridCell.initialize();
		GridCell gc1 = new GridCell(new GridPosition(0, 0), Direction.NORTH, false, false, false);
		GridCell gc2 = new GridCell(new GridPosition(1, 1), Direction.SOUTH, false, false, false);
		GridCell gc3 = new GridCell(new GridPosition(0, 1), Direction.EAST, false, false, false);
		GridCell gc4 = new GridCell(new GridPosition(1, 0), Direction.WEST, false, false, false);
		GridCell gc5 = new GridCell(new GridPosition(2, 2), Direction.NORTHEAST, false, false, false);
		GridCell gc6 = new GridCell(new GridPosition(2, 1), Direction.NORTHWEST, false, false, false);
		GridCell gc7 = new GridCell(new GridPosition(1, 2), Direction.SOUTHEAST, false, false, false);
		GridCell gc8 = new GridCell(new GridPosition(2, 0), Direction.SOUTHWEST, false, false, false);
		
		double a = GridCell.ACCELERATION_BY_NANOSECOND;
		double diag = a * Math.sin(Math.toRadians(45));
		
		assertEquals(0, gc1.getAcceleration().getX(), 0.0001);
		assertEquals(-a, gc1.getAcceleration().getY(), 0.0001);
		assertEquals(0, gc2.getAcceleration().getX(), 0.0001);
		assertEquals(a, gc2.getAcceleration().getY(), 0.0001);
		assertEquals(a, gc3.getAcceleration().getX(), 0.0001);
		assertEquals(0, gc3.getAcceleration().getY(), 0.0001);
		assertEquals(-a, gc4.getAcceleration().getX(), 0.0001);
		assertEquals(0, gc4.getAcceleration().getY(), 0.0001);
		assertEquals(diag, gc5.getAcceleration().getX(), 0.0001);
		assertEquals(-diag, gc5.getAcceleration().getY(), 0.0001);
		assertEquals(-diag, gc6.getAcceleration().getX(), 0.0001);
		assertEquals(-diag, gc6.getAcceleration().getY(), 0.0001);
		assertEquals(diag, gc7.getAcceleration().getX(), 0.0001);
		assertEquals(diag, gc7.getAcceleration().getY(), 0.0001);
		assertEquals(-diag, gc8.getAcceleration().getX(), 0.0001);
		assertEquals(diag, gc8.getAcceleration().getY(), 0.0001);
	}

}
