package model.grid.griditem.gabion;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.gridcell.GridPosition;

/**
 * A Gabion that is made of Oysters
 * @author 
 * @attributes
 * @throw
 * @return
 */
public class OysterGabion extends Gabion{
	/**
	 * Constructor
	 * @param coord - coordinate position
	 * @return none
	 */
	public OysterGabion(Coord coord){
		super(coord, new Animation("OysterGabion"), new GridPosition(0, 0));
	}
}
