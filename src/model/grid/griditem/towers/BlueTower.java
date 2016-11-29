package model.grid.griditem.towers;

import model.drawing.Animation;
import model.drawing.Coord;
import model.drawing.Offset;
import model.grid.GridColor;
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
		super(coord);
		setGridColor(GridColor.BLUE);
		cooldownRemaining = 10;
		range = 1000;
		animation = new Animation("oyster_tower", Offset.CENTER, Offset.CENTER);
	}
}
