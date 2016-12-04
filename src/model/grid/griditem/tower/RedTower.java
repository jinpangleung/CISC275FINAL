package model.grid.griditem.tower;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.GridColor;
import model.grid.griditem.GridItem;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.path.Path;
import model.gui.touch.Touch;
import model.grid.gridcell.GridPosition;


/**
 * A RedTower is a tower that can pick up pollution. 
 * 
 * @author leung, Eric
 * @version 1
 * @attributes GridColor - Color on the grid
 * @attributes cooldownRemaining - Cooldown for tower
 * @attributes range - range of tower
 * @throw
 * @return
 */

public class RedTower extends Tower {

	public RedTower(Coord coord){
		super(coord, new Animation("pollutant_tower"), new GridPosition(0, 0), GridColor.RED);
	}
	
	//@Override
	public void release(int mouseX, int mouseY){
		if(Touch.getInstance().isHolding()){
			GridItem gi = Touch.getInstance().unClamp();
			if(gi instanceof TrailItem){
				if(mouseX >= this.getCoord().getX()){
					this.reactTrash();
				} else {
					this.reactRecycle();
				}
			}
			else {
				Path.snap();
			}
		}
	}

	private void reactRecycle() {
		// TODO Auto-generated method stub
		
	}

	private void reactTrash() {
		// TODO Auto-generated method stub
		
	}
}
