package tests.inventory;

import static org.junit.Assert.*;

import org.junit.Test;

import model.drawing.Coord;
import model.grid.griditem.tower.*;
import model.grid.Grid;
import model.grid.griditem.gabion.*;
import model.inventory.Inventory;

public class InventoryTest {

	@Test
	public void testInitialize() {
		Inventory inventory = new Inventory();
		inventory.initialize(200, 100);
		// TODO
		// Maybe have Gifan write this since he's a bit more familiar with the percents and how it is set up
		// Or I can do the math if I have time to write it out
	}
	
	@Test
	public void replaceTowerTest(){
		new Grid(0, 0, 100, 100);
		(new Inventory()).initialize(200, 100);
		
		Inventory.getInstance().replaceTower(new RedTower(new Coord(0, 0)));
		Inventory.getInstance().replaceTower(new BlueTower(new Coord(0, 0)));
		Inventory.getInstance().replaceTower(new GreenTower(new Coord(0, 0)));
		Inventory.getInstance().replaceTower(new OysterGabion(new Coord(0, 0)));
		Inventory.getInstance().replaceTower(new ConcreteGabion(new Coord(0, 0)));
		
		assertEquals(3, Inventory.getInstance().getRtf().getCurrency());
		assertEquals(3, Inventory.getInstance().getBtf().getCurrency());
		assertEquals(3, Inventory.getInstance().getGtf().getCurrency());
		assertEquals(4, Inventory.getInstance().getOgf().getCurrency());
		assertEquals(4, Inventory.getInstance().getCgf().getCurrency());
	}

}
