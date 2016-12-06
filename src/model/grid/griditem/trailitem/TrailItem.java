package model.grid.griditem.trailitem;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridColor;
import model.grid.griditem.tower.Tower;
import model.gui.touch.Touch;
import model.moving.MovableObject;
import model.moving.Velocity;

/**
 * TrailItem
 * A TrailItem is a GridItem that moves along with the direction of the GridCells
 * They can be picked up via the Grid's mouse handlers
 * 
 * @author Roy, Eric
 *
 */

public abstract class TrailItem extends MovableObject {
	
	public TrailItem(Coord coord, Animation animation, GridPosition gridPosition, Velocity velocity, GridColor gc) {
		super(coord, animation, gridPosition, gc, velocity);
	}
	
	public boolean click(){
		for (Tower a : Grid.getInstance().getTowers()){
			if (a.isInRange(this.getCoord())){
				Touch.getInstance().clamp(this);
				return true;
			}
		}
		return false;
	}

}