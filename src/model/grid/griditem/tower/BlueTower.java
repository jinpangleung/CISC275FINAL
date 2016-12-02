package model.grid.griditem.tower;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.GridColor;
import model.grid.gridcell.GridPosition;

/**
 * A BlueTower is a tower that can pick up oysters. 
 * 
 * @author leung, Eric
 * @version 1
 * @attributes GridColor - Color on the grid
 * @attributes cooldownRemaining - Cooldown for tower
 * @attributes range - range of tower
 * @throw
 * @return
 */

public class BlueTower extends Tower{
	
	public BlueTower(Coord coord){
		super(coord, new Animation("oyster_tower"), new GridPosition(0, 0), GridColor.BLUE);
	}
}
