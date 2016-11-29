package model.grid.griditem.towers;

import model.drawing.Animation;
import model.drawing.Coord;
import model.drawing.Offset;
import model.grid.griditem.*;
import model.grid.GridColor;
import model.grid.gridcell.GridPosition;


/**
 * A RedTower is a tower that can pick up pollution. 
 * 
 * @author leung, Eric
 * @version 1
 * @attributes GridColor - Color on the grid
 * @attributes cooldownRemaining - Cooldown for tower
 * @attributes range - range of tower
 * @throw
 * @return
 */

public class RedTower extends Tower {

	public RedTower(Coord coord){
		super(coord);
		setGridColor(GridColor.RED);
		cooldownRemaining = 10;
		range = 1000;
		animation = new Animation("pollutant_tower", Offset.CENTER, Offset.CENTER);
	}
}
