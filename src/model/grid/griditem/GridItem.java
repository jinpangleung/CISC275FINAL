package model.grid.griditem;

import model.drawing.Animation;
import model.drawing.Coord;
import model.drawing.DrawableObject;
import model.grid.gridcell.GridPosition;

/**
 * GridItem is a class that represents an object that can exists on a Grid
 * @author Eric
 *
 */
public abstract class GridItem extends DrawableObject {
	
    /**
     * Constructs a GridItem
     * 
     * @param coord See {@see DrawableObject} constructor
     * @param animation See {@see DrawableObject} constructor
     * @param gridPosition the GridPosition of the new GridItem
     * @param gc the GridColor of the new GridItem
     */
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