package tests.drawing;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Model;
import model.drawing.Animation;
import model.drawing.Coord;
import model.drawing.DrawableObject;
import model.grid.griditem.trailitem.Oyster;

public class DrawableObjectTest {

	@Test
	public void test() {
		Model m = new Model();
		m.initialize(1000, 1000);
		DrawableObject d = new Oyster(new Coord(300, 300));
		assertEquals(d.getCoord().getX(), 300, 0.0001);
		assertEquals(d.getCoord().getY(), 300, 0.00001);
		
		d.update(1);
		assertEquals(d.getAnimation().getElapsedTime(), 0);
		
		assertTrue(d.isWithin(300, 300));
		assertTrue(d.isWithin(new Coord(300, 300)));
		assertFalse(d.isWithin(1000, 1000));
		
		d.setCoord(new Coord(301, 301));
		assertEquals(d.getCoord().getX(), 301, 0.0001);
		assertEquals(d.getCoord().getY(), 301, 0.00001);
		
		d.setCoord(300, 300);
		assertEquals(d.getCoord().getX(), 300, 0.0001);
		assertEquals(d.getCoord().getY(), 300, 0.00001);
		
		d.setAnimation(new Animation("Pollutant1"));
		
		assertTrue(d.toString().equals(d.toString()));
	}

}
