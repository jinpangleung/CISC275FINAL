/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Model;
import model.drawing.Coord;
import model.grid.griditem.tower.RedTower;
import model.inventory.Inventory;

/**
 * @author Roy Cheng
 *
 */
public class ModelTest {
	Model model = new Model();
	
	@Test
	public void testInitialize() {
		model.initialize(100, 100);
	}
	
	
	@Test
	public void getScreenWidthTest(){
		model.initialize(100, 100);
		assertEquals(model.getScreenWidth(), 100);
	}
	
	@Test
	public void getScreenHeightTest(){
		model.initialize(100, 100);
		assertEquals(model.getScreenHeight(), 100);
	}
	
	@Test
	public void endTitleScreenTest(){
		model.endTitleScreen();
		assertEquals(model.getTitleScreen(),false);
	}
	
	@Test
	public void mouseClickedTest(){
		//don't think I can test
	}
	
	@Test
	public void mouseReleasedTest(){
		//don't think I can test
	}
	
	@Test
	public void mouseDraggedTest(){
		//don't think I can test
	}
	
	@Test
	public void updateTutorialTest(){
		//don't think I can test
	}
	
}
