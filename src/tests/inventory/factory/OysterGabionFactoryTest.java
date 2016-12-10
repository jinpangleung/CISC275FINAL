package tests.inventory.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Model;
import model.drawing.Animation;
import model.grid.griditem.GridItem;
import model.grid.griditem.gabion.ConcreteGabion;
import model.grid.griditem.gabion.OysterGabion;
import model.gui.touch.Touch;
import model.inventory.factory.OysterGabionFactory;

public class OysterGabionFactoryTest {

	@Test
	public void testMouseClicked() {
		Model m = new Model();
		m.initialize(100, 100);
		Animation.initialize();
		
		OysterGabionFactory fac = new OysterGabionFactory(0,0,100,100);
		
		// Test with enough currency
		fac.setCurrency(3);
		fac.setCostPer(1);
		fac.mouseClicked(50, 60);
		
		assertEquals(2, fac.getCurrency());
		GridItem item = Touch.getInstance().getHolding();
		assertEquals(OysterGabion.class, item.getClass());
		assertEquals(0, item.getCoord().getX(), .1);
		assertEquals(0, item.getCoord().getY(), .1);
		Touch.getInstance().unClamp();
		
		// Test without enough currency
		fac.setCurrency(0);
		fac.mouseClicked(50, 60);
		assertEquals(false, Touch.getInstance().isHolding());
	}

}
