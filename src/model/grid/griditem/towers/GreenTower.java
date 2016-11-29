package model.grid.griditem.towers;

import model.drawing.Animation;
import model.drawing.Coord;
import model.drawing.Offset;
import model.grid.GridColor;
import model.grid.gridcell.GridPosition;
/**
 * A GreenTower is a tower that can pick up Invasive Items. 
 * 
 * @author leung, Eric
 * @version 1
 * @attributes GridColor - Color on the grid
 * @attributes cooldownRemaining - Cooldown for tower
 * @attributes range - range of tower
 * @throw
 * @return
 */

public class GreenTower extends Tower{
	
	public GreenTower(Coord coord){
		super(coord);
		setGridColor(GridColor.GREEN);
		cooldownRemaining = 10;
		range = 1000;
		animation = new Animation("invasive_item_tower", Offset.CENTER, Offset.CENTER);
	}
	
}
