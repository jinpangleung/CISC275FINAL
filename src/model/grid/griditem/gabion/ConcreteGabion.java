package model.grid.griditem.gabion;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.gridcell.GridPosition;


/**
 * ConcretGabion
 * a gabion that has limited amount of it, where it could help to stop damage from storm
 * 
 * @author Roy
 *
 */


public class ConcreteGabion extends Gabion {

	public ConcreteGabion(Coord coord){
		super(coord, new Animation("ConcreteGabion"), new GridPosition(0, 0));
	}
	
}
