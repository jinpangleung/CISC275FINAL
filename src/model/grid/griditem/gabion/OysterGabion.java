package model.grid.griditem.gabion;

import model.drawing.Animation;
import model.drawing.Coord;
import model.drawing.Offset;
import model.grid.gridcell.GridPosition;

public class OysterGabion extends Gabion{
	public OysterGabion(Coord coord, GridPosition gridPosition){
		super(coord, new Animation("OysterGabion", Offset.CENTER, Offset.CENTER), gridPosition);
	}

    @Override
    public void takeDamage()
    {
        this.setHealth(this.getHealth() - 10);
        
    }
}
