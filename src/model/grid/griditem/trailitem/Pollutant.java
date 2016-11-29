package model.grid.griditem.trailitem;

import java.util.Collection;

import model.*;
import model.drawing.*;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.gridcell.GridPosition;
import model.grid.GridColor;
import model.moving.Velocity;

/**
 * 
 * Pollutant are stuff that's bad for the estuary, you are supposed to pick them up.
 * 
 * @author Roy Cheng, Eric
 * @version 1
 * @attributes health - health of the trailitems
 * @attributes speed - how fast they travel in the river
 * @attributes isBad - if it is bad to click on it
 * 
 */

public class Pollutant extends TrailItem{

	public Pollutant(){
		super(new Coord(0,0), null, new GridPosition(0,0), new Velocity(0, 0.01),  GridColor.RED);
		int randomNum = (int)(Math.random() * 3);
		switch(randomNum){
		case 0: this.setAnimation(new Animation("pollutant1", Offset.CENTER, Offset.CENTER)); break;
		case 1: this.setAnimation(new Animation("pollutant2", Offset.CENTER, Offset.CENTER)); break;
		case 2: this.setAnimation(new Animation("pollutant3", Offset.CENTER, Offset.CENTER)); break;
		case 3: this.setAnimation(new Animation("pollutant4", Offset.CENTER, Offset.CENTER)); break;
		}
	}
	
}