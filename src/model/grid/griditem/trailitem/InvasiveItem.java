package model.grid.griditem.trailitem;

import java.util.Collection;
import model.*;
import model.drawing.Animation;
import model.drawing.Coord;
import model.drawing.Offset;
import model.grid.Grid.*;
import model.grid.gridcell.GridPosition;
import model.grid.GridColor;
import model.moving.Velocity;

/**
 * 
 * InvasiveItem are creatures that's bad for the estuary, you are supposed to pick them up from river
 * 
 * @author Roy Cheng, Eric
 * @version 1
 * @attributes health - health of the trailitems
 * @attributes speed - how fast they travel in the river
 * @attributes isBad - if it is bad to click on it
 * 
 */

public class InvasiveItem extends TrailItem{
	
	public InvasiveItem() {
		super(new Coord(0,0), new Animation("invasive_item", Offset.CENTER, Offset.CENTER), new GridPosition(0,0), new Velocity(0,0.01),  GridColor.GREEN);
	}
	
	
}