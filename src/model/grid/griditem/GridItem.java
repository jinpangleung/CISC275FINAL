package model.grid.griditem;

import model.drawing.Animation;
import model.drawing.Coord;
import model.drawing.DrawableObject;
import model.grid.gridcell.GridPosition;

/**
 * GridItem
 * Abstract class that represents an object that can exists on a Grid
 * A GridItem is drawable
 * @author Eric
 *
 */

public abstract class GridItem extends DrawableObject {
	
	public GridItem(Coord coord, Animation animation, GridPosition gridPosition, GridColor gc) {
		super(coord, animation);
		this.gridPosition = gridPosition;
		this.gridColor = gc;
	}

	protected GridPosition gridPosition;
	protected GridColor gridColor;
	
	public boolean snapping = false;
	
	public GridPosition getGridPosition(){
		return gridPosition;
	}
	
	public void setGridPosition(GridPosition gp){
		this.gridPosition = gp;
	}
	
	public GridColor getGridColor(){
		return gridColor;
	}
	
	public void setGridColor(GridColor gc){
		this.gridColor = gc;
	}

	public abstract boolean update(long elapsedTime);

}