package tests.inventory.factory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import model.Model;
import model.TutorialStep;
import model.drawing.Animation;
import model.grid.Grid;
import model.gui.touch.Touch;
import model.inventory.factory.BlueTowerFactory;
import model.inventory.factory.NegativeCurrencyException;
import model.inventory.factory.TowerFactory;

public class TowerFactoryTest {
	
	TowerFactory bluefac;

	@Before
	public void setup(){
		Model m = new Model();
		m.initialize(100, 100);
		Animation.initialize();
		bluefac = new BlueTowerFactory(0,0,100,100);
	}
	
	@Test
	public void testGetSetCurrency(){
		bluefac.setCurrency(10);
		assertEquals(10, bluefac.getCurrency());
	}

	@Test
	public void testGetSetCostPer(){
		bluefac.setCostPer(10);
		assertEquals(10, bluefac.getCostPer());
	}
	
	@Test
	public void testReplaceTower(){
		bluefac.setCurrency(10);
		bluefac.setCostPer(1);
		bluefac.replaceTower();
		assertEquals(11, bluefac.getCurrency());
	}
	
	@Test
	public void testIncreaseCurrency(){
		bluefac.setCurrency(0);
		bluefac.increaseCurrency(10);
		assertEquals(10, bluefac.getCurrency());
	}
	
	@Test
	public void testReduceCurrency(){
		bluefac.setCurrency(10);
		bluefac.reduceCurrency(1);
		assertEquals(9, bluefac.getCurrency());
		try{
			bluefac.reduceCurrency(20);
		}
		catch(Exception e){
			assertEquals(NegativeCurrencyException.class, e.getClass());
		}
	}
	
	@Test
	public void testMouseReleased(){
		
		// Test tutorial parts
		Grid.getInstance().setStep(TutorialStep.PLACE_TOWER);
		Controller.setRunningTutorial(true);
		bluefac.setCurrency(3);
		bluefac.setCostPer(1);
		bluefac.mouseClicked(50, 50);
		bluefac.mouseReleased(0, 0);
		assertEquals(TutorialStep.CLICK_TOWER, Grid.getInstance().getStep());
		
		Grid.getInstance().setStep(TutorialStep.PLACE_GABBION);
		bluefac.mouseClicked(50, 50);
		bluefac.mouseReleased(0, 0);
		assertEquals(TutorialStep.CLICK_GABBION, Grid.getInstance().getStep());
		
		// Test path.snap executing
		bluefac.setCurrency(3);
		bluefac.setCostPer(1);
		bluefac.mouseClicked(50, 50);
		bluefac.mouseReleased(0, 0);
		assertEquals(false, Touch.getInstance().isHolding());
	}
}
