package model.grid.griditem.gabion;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.GridColor;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridItem;

/**
 * Gabion
 * Stops storm from damaging the estuary
 * 
 * @author Roy
 *
 */

public abstract class Gabion extends GridItem {
	
	private int health = 100;

	public Gabion(Coord coord, Animation animation, GridPosition gridPosition) {
		super(coord, animation, gridPosition, GridColor.WHITE);
		// TODO Auto-generated constructor stub
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int health){
	    this.health = health;
	}
	
	public void takeDamage(){
		this.health -= 10;
	}
	
	//@Override
	public boolean update(long elapsedTime) {
		// TODO Auto-generated method stub
		return false;
	}

}
