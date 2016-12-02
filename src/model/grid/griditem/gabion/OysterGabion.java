package model.grid.griditem.gabion;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.gridcell.GridPosition;

public class OysterGabion extends Gabion{
	public OysterGabion(Coord coord){
		super(coord, new Animation("OysterGabion"), new GridPosition(0, 0));
	}
}
