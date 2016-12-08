package model.grid.griditem.trailitem;

import java.util.Collection;
import java.util.Random;

import model.*;
import model.drawing.*;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridColor;
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
	
	private boolean recycle;
	
	public boolean getRecycle(){
		return recycle;
	}

	public Pollutant(Coord c){
		super(c, new Animation("null"), new GridPosition(0,0), new Velocity(0, 0.0001),  GridColor.RED);
		int randomNum = (new Random()).nextInt(4);
		switch(randomNum){
		case 0: this.setAnimation(new Animation("pollutant1")); recycle = true; break;
		case 1: this.setAnimation(new Animation("pollutant2")); recycle = true; break;
		case 2: this.setAnimation(new Animation("pollutant3")); recycle = false; break;
		case 3: this.setAnimation(new Animation("pollutant4")); recycle = false; break;
		}
	}
	
}