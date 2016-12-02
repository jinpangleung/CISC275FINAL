package tests.gui.touch;

import static org.junit.Assert.*;

import org.junit.Test;

import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.GridItem;
import model.grid.griditem.trailitem.*;
import model.gui.touch.DefaultTouchObject;
import model.gui.touch.Touch;

public class TouchTest {

	@Test
	public void test() {
		new Grid(0, 0, 100, 100);
		assertEquals(Touch.getInstance().getHolding().getCoord().getX(), Double.valueOf(0));
		assertEquals(Touch.getInstance().getHolding().getCoord().getY(), Double.valueOf(0));
		assertEquals(Touch.getInstance().getHolding().getGridPosition().getX(), Integer.valueOf(0));
		assertEquals(Touch.getInstance().getHolding().getGridPosition().getY(), Integer.valueOf(0));
		
		assertFalse(Touch.getInstance().isHolding());
		
		Oyster o = new Oyster(new Coord(100, 100));
		Touch.getInstance().clamp(o);
		
		assertEquals(Touch.getInstance().getHolding().getCoord().getX(), Double.valueOf(100.0));
		assertEquals(Touch.getInstance().getHolding().getCoord().getY(), Double.valueOf(100.0));
		assertEquals(Touch.getInstance().getHolding().getGridPosition().getX(), Integer.valueOf(0));
		assertEquals(Touch.getInstance().getHolding().getGridPosition().getY(), Integer.valueOf(0));
		
		assertEquals(Touch.getInstance().getStartPosition().getX(), Double.valueOf(100.0));
		assertEquals(Touch.getInstance().getStartPosition().getY(), Double.valueOf(100.0));
		
		assertTrue(Touch.getInstance().getHolding() instanceof Oyster);
		
		assertTrue(Touch.getInstance().isHolding());
		
		Touch.getInstance().mouseDragged(101, 101);
		
		assertEquals(Touch.getInstance().getHolding().getCoord().getX(), Double.valueOf(101.0));
		assertEquals(Touch.getInstance().getHolding().getCoord().getY(), Double.valueOf(101.0));
		assertEquals(Touch.getInstance().getHolding().getGridPosition().getX(), Integer.valueOf(0));
		assertEquals(Touch.getInstance().getHolding().getGridPosition().getY(), Integer.valueOf(0));
		
		assertEquals(Touch.getInstance().getStartPosition().getX(), Double.valueOf(100.0));
		assertEquals(Touch.getInstance().getStartPosition().getY(), Double.valueOf(100.0));
		
		assertTrue(Touch.getInstance().getHolding() instanceof Oyster);
		
		assertTrue(Touch.getInstance().isHolding());
		
		GridItem gi = Touch.getInstance().unClamp();
		
		assertTrue(gi.equals(o));
		assertFalse(Touch.getInstance().isHolding());
		assertTrue(Touch.getInstance().getHolding() instanceof DefaultTouchObject);
		
	}

}
