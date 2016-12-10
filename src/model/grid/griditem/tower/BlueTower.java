package model.grid.griditem.tower;

import java.awt.Color;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.GridColor;
import model.grid.gridcell.GridPosition;
/**
 * A BlueTower is a tower that can pick up oysters. 
 * 
 * @author 
 * @attributes 
 * @throw
 * @return
 */

public class BlueTower extends Tower{
	
	/**
	 * Initializes the tower to have a range which is set by screen size
	 * @param coord - Coordinate position
	 * @return none
	 */
	public BlueTower(Coord coord){
		super(coord, new Animation("oyster_tower"), new GridPosition(0, 0), GridColor.BLUE,
				new Color(0, 0, 255, getOpacity()));
	}
}
