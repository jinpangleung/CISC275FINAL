package model.grid.griditem.gabion;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.GridColor;
import model.grid.Grid;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridItem;

/**
 * Gabion, stops storm from damaging the estuary
 * @author 
 * @attributes health - damage it can take
 * @throw
 * @return
 */


public abstract class Gabion extends GridItem {
	
	private int health = 10; //damage it can take

	/**
	 * Constructor
	 * @param coord - coordinate position
	 * @param animation - animation item uses
	 * @param gp - position on grid
	 * @param gc - gridcolor, see GridColor for more explanation
	 * @return none
	 */
	public Gabion(Coord coord, Animation animation, GridPosition gridPosition) {
		super(coord, animation, gridPosition, GridColor.WHITE);
	}
	
	/**
	 * Gets health
	 * @param none
	 * @return health
	 */
	public int getHealth(){
		return health;
	}
	
	/**
	 * Sets health
	 * @param health
	 * @return none
	 */
	public void setHealth(int health){
	    this.health = health;
	}
	
	/**
	 * Applies damage
	 * @param none
	 * @return none
	 */
	public void takeDamage(){
		this.health -= 10;
	}
	
	/**
	 * Updates gabion
	 * @param elapsedTime - time elapsed
	 * @return false
	 */
	public boolean update(long elapsedTime) {
		if(health <= 0){
			Grid.getInstance().removeItem(this);
		}
		return false;
	}

}
