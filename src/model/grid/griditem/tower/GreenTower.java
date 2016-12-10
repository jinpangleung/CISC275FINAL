package model.grid.griditem.tower;

import java.awt.Color;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.GridColor;
import model.grid.gridcell.GridPosition;
/**
 * A GreenTower is a tower that can pick up Invasive Items. 
 * 
 * @author 
 * @attributes 
 * @throw 
 * @return 
 */

public class GreenTower extends Tower{
	
	/**
	 * Initializes the tower to have a range which is set by screen size
	 * @param coord - Coordinate position
	 * @return none
	 */
	public GreenTower(Coord coord){
		super(coord, new Animation("invasive_item_tower"), new GridPosition(0, 0), GridColor.GREEN,
				new Color(0, 255, 0, getOpacity()));
	}
	
}
