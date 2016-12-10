package model.grid.griditem.gabion;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.gridcell.GridPosition;


/**
 * A Gabion that is Concrete
 * @author 
 * @attributes
 * @throw
 * @return
 */


public class ConcreteGabion extends Gabion {

	/**
	 * Constructor
	 * @param coord - coordinate position
	 * @return none
	 */
	public ConcreteGabion(Coord coord){
		super(coord, new Animation("ConcreteGabion"), new GridPosition(0, 0));
	}
	
}
