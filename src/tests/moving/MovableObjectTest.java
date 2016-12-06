package tests.moving;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import model.Model;
import model.Time;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.PixelGrid;
import model.grid.gridcell.GridCell;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridItem;
import model.grid.griditem.trailitem.Pollutant;
import model.moving.MovableObject;
import model.moving.Velocity;

/**
 * Tests MovableObject
 * 
 * @author Gifan Thadathil
 *
 */
public class MovableObjectTest {

	Grid grid;
	GridItem pollutantN;
	GridItem pollutantS;
	GridItem pollutantE;
	GridItem pollutantW;
	GridItem pollutantA;
	GridItem pollutantB;
	GridItem pollutantD;
	GridItem pollutantC;
	
	@Before
	public void setup(){
		// Initialize game
		Model model = new Model();
		model.initialize(900, 1300);
		Animation.initialize();
		GridCell.initialize();
				
		// Create grid
		grid = new Grid(0,0,900,1300, "board_text_files/move_test_grid.txt");
		
		// Create items to go into grid
		pollutantN = new Pollutant(new Coord(350, 350)); // North
		pollutantS = new Pollutant(new Coord(150, 150)); // South
		pollutantE = new Pollutant(new Coord(550, 150)); // East
		pollutantW = new Pollutant(new Coord(750, 350)); // West
		pollutantA = new Pollutant(new Coord(150, 750)); // North East
		pollutantB = new Pollutant(new Coord(750, 750)); // North West
		pollutantD = new Pollutant(new Coord(350, 950)); // South West
		pollutantC = new Pollutant(new Coord(550, 950)); // South East
		
		// Set GridPositions of the items
		pollutantN.setGridPosition(new GridPosition(3,3));
		pollutantS.setGridPosition(new GridPosition(1,1));
		pollutantE.setGridPosition(new GridPosition(5,1));
		pollutantW.setGridPosition(new GridPosition(7,3));
		pollutantA.setGridPosition(new GridPosition(1,7));
		pollutantB.setGridPosition(new GridPosition(7,7));
		pollutantD.setGridPosition(new GridPosition(3,9));
		pollutantC.setGridPosition(new GridPosition(5,9));
		
		// Set velocities of the items and make max velocities very high for easy testing
		((MovableObject) pollutantN).setVelocity(new Velocity(0,0));
		((MovableObject) pollutantS).setVelocity(new Velocity(0,0));
		((MovableObject) pollutantE).setVelocity(new Velocity(0,0));
		((MovableObject) pollutantW).setVelocity(new Velocity(0,0));
		((MovableObject) pollutantA).setVelocity(new Velocity(0,0));
		((MovableObject) pollutantB).setVelocity(new Velocity(0,0));
		((MovableObject) pollutantD).setVelocity(new Velocity(0,0));
		((MovableObject) pollutantC).setVelocity(new Velocity(0,0));
		
		((MovableObject) pollutantN).setMaxVelocity(500);
		((MovableObject) pollutantS).setMaxVelocity(500);
		((MovableObject) pollutantE).setMaxVelocity(500);
		((MovableObject) pollutantW).setMaxVelocity(500);
		((MovableObject) pollutantA).setMaxVelocity(500);
		((MovableObject) pollutantB).setMaxVelocity(500);
		((MovableObject) pollutantD).setMaxVelocity(500);
		((MovableObject) pollutantC).setMaxVelocity(500);
		
		// Add the items into the grid
		grid.addItem(pollutantN);
		grid.addItem(pollutantS);
		grid.addItem(pollutantE);
		grid.addItem(pollutantW);
		grid.addItem(pollutantA);
		grid.addItem(pollutantB);
		grid.addItem(pollutantD);
		grid.addItem(pollutantC);
	}
	
	@Test
	public void testItemsInGrid() {
		Collection c = grid.getItems();
		assertEquals(true, c.contains(pollutantN));
		assertEquals(true, c.contains(pollutantS));
		assertEquals(true, c.contains(pollutantE));
		assertEquals(true, c.contains(pollutantW));
		assertEquals(true, c.contains(pollutantA));
		assertEquals(true, c.contains(pollutantB));
		assertEquals(true, c.contains(pollutantD));
		assertEquals(true, c.contains(pollutantC));
	}
	
	@Test
	public void testGridPositionAccuracy() {
		Collection<GridItem> c = grid.getItems();
		for(GridItem g : c){
			GridPosition itemPos = g.getGridPosition();
			Coord itemCoord = g.getCoord();
			GridPosition itemPosFromPixel = PixelGrid.getInstance().getGridPosition((int) Math.round(itemCoord.getX()), 
					(int) Math.round(itemCoord.getY()));
			assertEquals(itemPos.getX(), itemPosFromPixel.getX());
			assertEquals(itemPos.getY(), itemPosFromPixel.getY());
		}
	}

	@Test
	public void testUpdate1Sec(){
		// Save original item coords and grid positions
		Coord [] itemCoords = new Coord[8];
		GridPosition [] itemPoses = new GridPosition[8];
		
		itemCoords[0] = new Coord(pollutantN.getCoord().getX(), pollutantN.getCoord().getY());
		itemCoords[1] = new Coord(pollutantS.getCoord().getX(), pollutantS.getCoord().getY());
		itemCoords[2] = new Coord(pollutantE.getCoord().getX(), pollutantE.getCoord().getY());
		itemCoords[3] = new Coord(pollutantW.getCoord().getX(), pollutantW.getCoord().getY());
		itemCoords[4] = new Coord(pollutantA.getCoord().getX(), pollutantA.getCoord().getY());
		itemCoords[5] = new Coord(pollutantB.getCoord().getX(), pollutantB.getCoord().getY());
		itemCoords[6] = new Coord(pollutantD.getCoord().getX(), pollutantD.getCoord().getY());
		itemCoords[7] = new Coord(pollutantC.getCoord().getX(), pollutantC.getCoord().getY());
		
		itemPoses[0] = new GridPosition(pollutantN.getGridPosition().getX(), pollutantN.getGridPosition().getY());
		itemPoses[1] = new GridPosition(pollutantS.getGridPosition().getX(), pollutantS.getGridPosition().getY());
		itemPoses[2] = new GridPosition(pollutantE.getGridPosition().getX(), pollutantE.getGridPosition().getY());
		itemPoses[3] = new GridPosition(pollutantW.getGridPosition().getX(), pollutantW.getGridPosition().getY());
		itemPoses[4] = new GridPosition(pollutantA.getGridPosition().getX(), pollutantA.getGridPosition().getY());
		itemPoses[5] = new GridPosition(pollutantB.getGridPosition().getX(), pollutantB.getGridPosition().getY());
		itemPoses[6] = new GridPosition(pollutantD.getGridPosition().getX(), pollutantD.getGridPosition().getY());
		itemPoses[7] = new GridPosition(pollutantC.getGridPosition().getX(), pollutantC.getGridPosition().getY());
		
		// Update the items once with 1 second passed
		Collection<GridItem> c = grid.getItems();
		long elapsedTime = Time.nanosecond;
		for(GridItem g : c){
			g.update(elapsedTime);
		}
		
		// Test if coords are correctly changed
		double delta = .01;
		double itemVel = 1;
		double itemDiagVel = Math.sqrt(2)/2;
		
		Coord newCoords = pollutantN.getCoord();
		assertEquals(itemCoords[0].getX(), newCoords.getX(), delta);
		assertEquals(itemCoords[0].getY() - itemVel, newCoords.getY(), delta);
		
		newCoords = pollutantS.getCoord();
		assertEquals(itemCoords[1].getX(), newCoords.getX(), delta);
		assertEquals(itemCoords[1].getY() + itemVel, newCoords.getY(), delta);
		
		newCoords = pollutantE.getCoord();
		assertEquals(itemCoords[2].getX() + itemVel, newCoords.getX(), delta);
		assertEquals(itemCoords[2].getY(), newCoords.getY(), delta);
		
		newCoords = pollutantW.getCoord();
		assertEquals(itemCoords[3].getX() - itemVel, newCoords.getX(), delta);
		assertEquals(itemCoords[3].getY(), newCoords.getY(), delta);
		
		newCoords = pollutantA.getCoord();
		assertEquals(itemCoords[4].getX() + itemDiagVel, newCoords.getX(), delta);
		assertEquals(itemCoords[4].getY() - itemDiagVel, newCoords.getY(), delta);
		
		newCoords = pollutantB.getCoord();
		assertEquals(itemCoords[5].getX() - itemDiagVel, newCoords.getX(), delta);
		assertEquals(itemCoords[5].getY() - itemDiagVel, newCoords.getY(), delta);
		
		newCoords = pollutantD.getCoord();
		assertEquals(itemCoords[6].getX() - itemDiagVel, newCoords.getX(), delta);
		assertEquals(itemCoords[6].getY() + itemDiagVel, newCoords.getY(), delta);
		
		newCoords = pollutantC.getCoord();
		assertEquals(itemCoords[7].getX() + itemDiagVel, newCoords.getX(), delta);
		assertEquals(itemCoords[7].getY() + itemDiagVel, newCoords.getY(), delta);
		
		// Test if grid positions are unchanged
		GridPosition newPos = pollutantN.getGridPosition();
		assertEquals(itemPoses[0].getX(), newPos.getX());
		assertEquals(itemPoses[0].getY(), newPos.getY());
		
		newPos = pollutantS.getGridPosition();
		assertEquals(itemPoses[1].getX(), newPos.getX());
		assertEquals(itemPoses[1].getY(), newPos.getY());

		newPos = pollutantE.getGridPosition();
		assertEquals(itemPoses[2].getX(), newPos.getX());
		assertEquals(itemPoses[2].getY(), newPos.getY());
		
		newPos = pollutantW.getGridPosition();
		assertEquals(itemPoses[3].getX(), newPos.getX());
		assertEquals(itemPoses[3].getY(), newPos.getY());
		
		newPos = pollutantA.getGridPosition();
		assertEquals(itemPoses[4].getX(), newPos.getX());
		assertEquals(itemPoses[4].getY(), newPos.getY());
		
		newPos = pollutantB.getGridPosition();
		assertEquals(itemPoses[5].getX(), newPos.getX());
		assertEquals(itemPoses[5].getY(), newPos.getY());
		
		newPos = pollutantD.getGridPosition();
		assertEquals(itemPoses[6].getX(), newPos.getX());
		assertEquals(itemPoses[6].getY(), newPos.getY());
		
		newPos = pollutantC.getGridPosition();
		assertEquals(itemPoses[7].getX(), newPos.getX());
		assertEquals(itemPoses[7].getY(), newPos.getY());
	}
	
	@Test
	public void testUpdate16Sec(){
		// Save original item coords and grid positions
		Coord [] itemCoords = new Coord[8];
		GridPosition [] itemPoses = new GridPosition[8];
		
		itemCoords[0] = new Coord(pollutantN.getCoord().getX(), pollutantN.getCoord().getY());
		itemCoords[1] = new Coord(pollutantS.getCoord().getX(), pollutantS.getCoord().getY());
		itemCoords[2] = new Coord(pollutantE.getCoord().getX(), pollutantE.getCoord().getY());
		itemCoords[3] = new Coord(pollutantW.getCoord().getX(), pollutantW.getCoord().getY());
		itemCoords[4] = new Coord(pollutantA.getCoord().getX(), pollutantA.getCoord().getY());
		itemCoords[5] = new Coord(pollutantB.getCoord().getX(), pollutantB.getCoord().getY());
		itemCoords[6] = new Coord(pollutantD.getCoord().getX(), pollutantD.getCoord().getY());
		itemCoords[7] = new Coord(pollutantC.getCoord().getX(), pollutantC.getCoord().getY());
		
		itemPoses[0] = new GridPosition(pollutantN.getGridPosition().getX(), pollutantN.getGridPosition().getY());
		itemPoses[1] = new GridPosition(pollutantS.getGridPosition().getX(), pollutantS.getGridPosition().getY());
		itemPoses[2] = new GridPosition(pollutantE.getGridPosition().getX(), pollutantE.getGridPosition().getY());
		itemPoses[3] = new GridPosition(pollutantW.getGridPosition().getX(), pollutantW.getGridPosition().getY());
		itemPoses[4] = new GridPosition(pollutantA.getGridPosition().getX(), pollutantA.getGridPosition().getY());
		itemPoses[5] = new GridPosition(pollutantB.getGridPosition().getX(), pollutantB.getGridPosition().getY());
		itemPoses[6] = new GridPosition(pollutantD.getGridPosition().getX(), pollutantD.getGridPosition().getY());
		itemPoses[7] = new GridPosition(pollutantC.getGridPosition().getX(), pollutantC.getGridPosition().getY());
		
		// Update the items once with 1 second passed 16 times
		// Number of times is based on Triangular Number Sequence, did this to account for acceleration
		Collection<GridItem> c = grid.getItems();
		long elapsedTime = Time.nanosecond;
		
		for(int i = 0; i < 16; i++){
			for(GridItem g : c){
				g.update(elapsedTime);
			}
		}
		
		// Test if coords are correctly changed
		double delta = .01;
		double itemVel = 136;
		double itemDiagVel = 136 * Math.sqrt(2)/2;
		
		Coord newCoords = pollutantN.getCoord();
		assertEquals(itemCoords[0].getX(), newCoords.getX(), delta);
		assertEquals(itemCoords[0].getY() - itemVel, newCoords.getY(), delta);
		
		newCoords = pollutantS.getCoord();
		assertEquals(itemCoords[1].getX(), newCoords.getX(), delta);
		assertEquals(itemCoords[1].getY() + itemVel, newCoords.getY(), delta);
		
		newCoords = pollutantE.getCoord();
		assertEquals(itemCoords[2].getX() + itemVel, newCoords.getX(), delta);
		assertEquals(itemCoords[2].getY(), newCoords.getY(), delta);
		
		newCoords = pollutantW.getCoord();
		assertEquals(itemCoords[3].getX() - itemVel, newCoords.getX(), delta);
		assertEquals(itemCoords[3].getY(), newCoords.getY(), delta);
		
		newCoords = pollutantA.getCoord();
		assertEquals(itemCoords[4].getX() + itemDiagVel, newCoords.getX(), delta);
		assertEquals(itemCoords[4].getY() - itemDiagVel, newCoords.getY(), delta);
		
		newCoords = pollutantB.getCoord();
		assertEquals(itemCoords[5].getX() - itemDiagVel, newCoords.getX(), delta);
		assertEquals(itemCoords[5].getY() - itemDiagVel, newCoords.getY(), delta);
		
		newCoords = pollutantD.getCoord();
		assertEquals(itemCoords[6].getX() - itemDiagVel, newCoords.getX(), delta);
		assertEquals(itemCoords[6].getY() + itemDiagVel, newCoords.getY(), delta);
		
		newCoords = pollutantC.getCoord();
		assertEquals(itemCoords[7].getX() + itemDiagVel, newCoords.getX(), delta);
		assertEquals(itemCoords[7].getY() + itemDiagVel, newCoords.getY(), delta);
		
		// Test if grid positions are unchanged
		delta = 0;
		int gridPosChange = 1;
		
		GridPosition newPos = pollutantN.getGridPosition();
		assertEquals(itemPoses[0].getX(), newPos.getX(), delta);
		assertEquals(itemPoses[0].getY() - gridPosChange, newPos.getY(), delta);
		
		newPos = pollutantS.getGridPosition();
		assertEquals(itemPoses[1].getX(), newPos.getX(), delta);
		assertEquals(itemPoses[1].getY() + gridPosChange, newPos.getY(), delta);

		newPos = pollutantE.getGridPosition();
		assertEquals(itemPoses[2].getX() + gridPosChange, newPos.getX(), delta);
		assertEquals(itemPoses[2].getY(), newPos.getY(), delta);
		
		newPos = pollutantW.getGridPosition();
		assertEquals(itemPoses[3].getX() - gridPosChange, newPos.getX(), delta);
		assertEquals(itemPoses[3].getY(), newPos.getY(), delta);
		
		newPos = pollutantA.getGridPosition();
		assertEquals(itemPoses[4].getX() + gridPosChange, newPos.getX(), delta);
		assertEquals(itemPoses[4].getY() - gridPosChange, newPos.getY(), delta);
		
		newPos = pollutantB.getGridPosition();
		assertEquals(itemPoses[5].getX() - gridPosChange, newPos.getX(), delta);
		assertEquals(itemPoses[5].getY() - gridPosChange, newPos.getY(), delta);
		
		newPos = pollutantD.getGridPosition();
		assertEquals(itemPoses[6].getX() - gridPosChange, newPos.getX(), delta);
		assertEquals(itemPoses[6].getY() + gridPosChange, newPos.getY(), delta);
		
		newPos = pollutantC.getGridPosition();
		assertEquals(itemPoses[7].getX() + gridPosChange, newPos.getX(), delta);
		assertEquals(itemPoses[7].getY() + gridPosChange, newPos.getY(), delta);
	}
	
	@Test
	public void testOffTrail(){
		// Save original grid positions
		GridPosition [] itemPoses = new GridPosition[8];
		
		itemPoses[0] = new GridPosition(pollutantN.getGridPosition().getX(), pollutantN.getGridPosition().getY());
		itemPoses[1] = new GridPosition(pollutantS.getGridPosition().getX(), pollutantS.getGridPosition().getY());
		itemPoses[2] = new GridPosition(pollutantE.getGridPosition().getX(), pollutantE.getGridPosition().getY());
		itemPoses[3] = new GridPosition(pollutantW.getGridPosition().getX(), pollutantW.getGridPosition().getY());
		itemPoses[4] = new GridPosition(pollutantA.getGridPosition().getX(), pollutantA.getGridPosition().getY());
		itemPoses[5] = new GridPosition(pollutantB.getGridPosition().getX(), pollutantB.getGridPosition().getY());
		itemPoses[6] = new GridPosition(pollutantD.getGridPosition().getX(), pollutantD.getGridPosition().getY());
		itemPoses[7] = new GridPosition(pollutantC.getGridPosition().getX(), pollutantC.getGridPosition().getY());
		
		// Update the items once with 1 second passed 28 times, which is enough to move items of trails
		// Number of times is based on Triangular Number Sequence, did this to account for acceleration
		Collection<GridItem> c = grid.getItems();
		long elapsedTime = Time.nanosecond;
		
		for(int i = 0; i < 28; i++){
			for(GridItem g : c){
				g.update(elapsedTime);
			}
		}
		
		// Test if grid positions are unchanged
		double delta = 0;
		int gridPosChange = 2;
		
		GridPosition newPos = pollutantN.getGridPosition();
		assertEquals(itemPoses[0].getX(), newPos.getX(), delta);
		assertEquals(itemPoses[0].getY() - gridPosChange, newPos.getY(), delta);
		
		newPos = pollutantS.getGridPosition();
		assertEquals(itemPoses[1].getX(), newPos.getX(), delta);
		assertEquals(itemPoses[1].getY() + gridPosChange, newPos.getY(), delta);

		newPos = pollutantE.getGridPosition();
		assertEquals(itemPoses[2].getX() + gridPosChange, newPos.getX(), delta);
		assertEquals(itemPoses[2].getY(), newPos.getY(), delta);
		
		newPos = pollutantW.getGridPosition();
		assertEquals(itemPoses[3].getX() - gridPosChange, newPos.getX(), delta);
		assertEquals(itemPoses[3].getY(), newPos.getY(), delta);
		
		newPos = pollutantA.getGridPosition();
		assertEquals(itemPoses[4].getX() + gridPosChange, newPos.getX(), delta);
		assertEquals(itemPoses[4].getY() - gridPosChange, newPos.getY(), delta);
		
		newPos = pollutantB.getGridPosition();
		assertEquals(itemPoses[5].getX() - gridPosChange, newPos.getX(), delta);
		assertEquals(itemPoses[5].getY() - gridPosChange, newPos.getY(), delta);
		
		newPos = pollutantD.getGridPosition();
		assertEquals(itemPoses[6].getX() - gridPosChange, newPos.getX(), delta);
		assertEquals(itemPoses[6].getY() + gridPosChange, newPos.getY(), delta);
		
		newPos = pollutantC.getGridPosition();
		assertEquals(itemPoses[7].getX() + gridPosChange, newPos.getX(), delta);
		assertEquals(itemPoses[7].getY() + gridPosChange, newPos.getY(), delta);
	}
}
