package model.grid.griditem.tower;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.GridColor;
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
		super(coord, new Animation("invasive_item_tower"), new GridPosition(0, 0), GridColor.GREEN);
	}
	
}