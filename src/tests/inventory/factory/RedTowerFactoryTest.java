package tests.inventory.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;
import model.Model;
import model.TutorialStep;
import model.drawing.Animation;
import model.grid.Grid;
import model.grid.griditem.GridItem;
import model.grid.griditem.tower.GreenTower;
import model.grid.griditem.tower.RedTower;
import model.gui.touch.Touch;
import model.inventory.factory.RedTowerFactory;

public class RedTowerFactoryTest {

	@Test
	public void testMouseClicked() {
		Model m = new Model();
		m.initialize(100, 100);
		Animation.initialize();
		RedTowerFactory fac = new RedTowerFactory(0,0,100,100);
		
		// Test tutorial part
		fac.setCurrency(0);
		Grid.getInstance().setStep(TutorialStep.CLICK_TOWER);
		Controller.setRunningTutorial(true);
		fac.mouseClicked(50, 60);
		assertEquals(TutorialStep.PLACE_TOWER, Grid.getInstance().getStep());
		Controller.setRunningTutorial(false);
		
		// Test with enough currency
		fac.setCurrency(3);
		fac.setCostPer(1);
		fac.mouseClicked(50, 60);
		
		assertEquals(2, fac.getCurrency());
		GridItem item = Touch.getInstance().getHolding();
		assertEquals(RedTower.class, item.getClass());
		assertEquals(0, item.getCoord().getX(), .1);
		assertEquals(0, item.getCoord().getY(), .1);
		Touch.getInstance().unClamp();
		
		// Test without enough currency
		fac.setCurrency(0);
		fac.mouseClicked(50, 60);
		assertEquals(false, Touch.getInstance().isHolding());
	}

}
