package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.OrderedPair;
import model.drawing.Coord;

public class OrderedPairTest {

	@Test
	public void test() {
		OrderedPair op = new Coord(10,10);
		
		op.setX(1);
		op.setY(2);
		assertEquals(1, op.getX());
		assertEquals(2, op.getY());
	}

}
