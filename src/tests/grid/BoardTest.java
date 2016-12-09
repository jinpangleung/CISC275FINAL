package tests.grid;
/*
 * will test grid position and board read-in through char
 */
import static org.junit.Assert.*;

import org.junit.Test;

import model.grid.Board;
import model.grid.gridcell.Direction;
import model.grid.gridcell.GridCell;

public class BoardTest {

	@Test
	public void test() {
		Board board = new Board("board_text_files/gridTest.txt");
		
		//these test 
		GridCell gc1 = board.getGridCell(0,0);
		assertEquals(Integer.valueOf(0), gc1.getGridPosition().getX());
		assertEquals(Integer.valueOf(0), gc1.getGridPosition().getY());
		assertEquals(Direction.SOUTH, gc1.getDirection());
		assertTrue(gc1.isTrail());
		assertFalse(gc1.isCanPlaceTower());
		assertFalse(gc1.isCanPlaceGabion());
		assertEquals(gc1.getGridPosition(), board.getSpawnPositions().get(0));
	
		GridCell gc2 = board.getGridCell(1,0);
		assertEquals(Integer.valueOf(1), gc2.getGridPosition().getX());
		assertEquals(Integer.valueOf(0), gc2.getGridPosition().getY());
		assertEquals(Direction.NONE, gc2.getDirection());
		assertFalse(gc2.isTrail());
		assertTrue(gc2.isCanPlaceTower());
		assertFalse(gc2.isCanPlaceGabion());
		
		GridCell gc3 = board.getGridCell(2,0);
		assertEquals(Integer.valueOf(2), gc3.getGridPosition().getX());
		assertEquals(Integer.valueOf(0), gc3.getGridPosition().getY());
		assertEquals(Direction.NONE, gc3.getDirection());
		assertFalse(gc3.isTrail());
		assertTrue(gc3.isCanPlaceTower());
		assertFalse(gc3.isCanPlaceGabion());
		
		GridCell gc4 = board.getGridCell(3,0);
		assertEquals(Integer.valueOf(3), gc4.getGridPosition().getX());
		assertEquals(Integer.valueOf(0), gc4.getGridPosition().getY());
		assertEquals(Direction.SOUTH, gc4.getDirection());
		assertTrue(gc4.isTrail());
		assertFalse(gc4.isCanPlaceTower());
		assertFalse(gc4.isCanPlaceGabion());
		System.out.println(board.getSpawnPositions());
		assertEquals(gc4.getGridPosition(), board.getSpawnPositions().get(1));
		
		
		GridCell gc5 = board.getGridCell(0,1);
		assertEquals(Integer.valueOf(0), gc5.getGridPosition().getX());
		assertEquals(Integer.valueOf(1), gc5.getGridPosition().getY());
		assertEquals(Direction.EAST, gc5.getDirection());
		assertTrue(gc5.isTrail());
		assertFalse(gc5.isCanPlaceTower());
		assertFalse(gc5.isCanPlaceGabion());
		
		GridCell gc6 = board.getGridCell(1,1);
		assertEquals(Integer.valueOf(1), gc6.getGridPosition().getX());
		assertEquals(Integer.valueOf(1), gc6.getGridPosition().getY());
		assertEquals(Direction.WEST, gc6.getDirection());
		assertTrue(gc6.isTrail());
		assertFalse(gc6.isCanPlaceTower());
		assertFalse(gc6.isCanPlaceGabion());
		
		GridCell gc7 = board.getGridCell(2,1);
		assertEquals(Integer.valueOf(2), gc7.getGridPosition().getX());
		assertEquals(Integer.valueOf(1), gc7.getGridPosition().getY());
		assertEquals(Direction.SOUTH, gc7.getDirection());
		assertTrue(gc7.isTrail());
		assertFalse(gc7.isCanPlaceTower());
		assertFalse(gc7.isCanPlaceGabion());
		
		GridCell gc8 = board.getGridCell(3,1);
		assertEquals(Integer.valueOf(3), gc8.getGridPosition().getX());
		assertEquals(Integer.valueOf(1), gc8.getGridPosition().getY());
		assertEquals(Direction.NORTHEAST, gc8.getDirection());
		assertTrue(gc8.isTrail());
		assertFalse(gc8.isCanPlaceTower());
		assertFalse(gc8.isCanPlaceGabion());
		
		GridCell gc9 = board.getGridCell(0,2);
		assertEquals(Integer.valueOf(0), gc9.getGridPosition().getX());
		assertEquals(Integer.valueOf(2), gc9.getGridPosition().getY());
		assertEquals(Direction.NORTHWEST, gc9.getDirection());
		assertTrue(gc5.isTrail());
		assertFalse(gc5.isCanPlaceTower());
		assertFalse(gc5.isCanPlaceGabion());
		
		GridCell gc10 = board.getGridCell(1,2);
		assertEquals(Integer.valueOf(1), gc10.getGridPosition().getX());
		assertEquals(Integer.valueOf(2), gc10.getGridPosition().getY());
		assertEquals(Direction.SOUTHEAST, gc10.getDirection());
		assertTrue(gc6.isTrail());
		assertFalse(gc6.isCanPlaceTower());
		assertFalse(gc6.isCanPlaceGabion());
		
		GridCell gc11 = board.getGridCell(2,2);
		assertEquals(Integer.valueOf(2), gc11.getGridPosition().getX());
		assertEquals(Integer.valueOf(2), gc11.getGridPosition().getY());
		assertEquals(Direction.SOUTHWEST, gc11.getDirection());
		assertTrue(gc11.isTrail());
		assertFalse(gc11.isCanPlaceTower());
		assertFalse(gc11.isCanPlaceGabion());
		
		GridCell gc12 = board.getGridCell(3,2);
		assertEquals(Integer.valueOf(3), gc12.getGridPosition().getX());
		assertEquals(Integer.valueOf(2), gc12.getGridPosition().getY());
		assertEquals(Direction.NONE, gc12.getDirection());
		assertFalse(gc12.isTrail());
		assertTrue(gc12.isCanPlaceTower());
		assertFalse(gc12.isCanPlaceGabion());
		
		GridCell gc13 = board.getGridCell(0,3);
		assertEquals(Integer.valueOf(0), gc13.getGridPosition().getX());
		assertEquals(Integer.valueOf(3), gc13.getGridPosition().getY());
		assertEquals(Direction.NONE, gc13.getDirection());
		assertFalse(gc13.isTrail());
		assertFalse(gc13.isCanPlaceTower());
		assertTrue(gc13.isCanPlaceGabion());
		
		GridCell gc14 = board.getGridCell(1,3);
		assertEquals(Integer.valueOf(1), gc14.getGridPosition().getX());
		assertEquals(Integer.valueOf(3), gc14.getGridPosition().getY());
		assertEquals(Direction.NONE, gc14.getDirection());
		assertFalse(gc14.isTrail());
		assertFalse(gc14.isCanPlaceTower());
		assertTrue(gc14.isCanPlaceGabion());

		GridCell gc15 = board.getGridCell(2,3);
		assertEquals(Integer.valueOf(2), gc15.getGridPosition().getX());
		assertEquals(Integer.valueOf(3), gc15.getGridPosition().getY());
		assertEquals(Direction.SOUTH, gc15.getDirection());
		assertTrue(gc15.isTrail());
		assertFalse(gc15.isCanPlaceTower());
		assertFalse(gc15.isCanPlaceGabion());
		
		GridCell gc16 = board.getGridCell(3,3);
		assertEquals(Integer.valueOf(3), gc16.getGridPosition().getX());
		assertEquals(Integer.valueOf(3), gc16.getGridPosition().getY());
		assertEquals(Direction.NONE, gc16.getDirection());
		assertFalse(gc16.isTrail());
		assertFalse(gc16.isCanPlaceTower());
		assertTrue(gc16.isCanPlaceGabion());
		
		assertEquals(4, board.getHeight());
		assertEquals(4, board.getWidth());
	}

}
