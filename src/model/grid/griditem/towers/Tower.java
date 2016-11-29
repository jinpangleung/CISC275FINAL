package model.grid.griditem.towers;

import model.drawing.Animation;
import model.drawing.Coord;
import model.drawing.Offset;
import model.grid.gridcell.GridPosition;
import model.grid.GridColor;
import model.grid.griditem.GridItem;
import model.grid.griditem.trailitem.Oyster;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.path.BackToGridBehavior;
import model.gui.path.Path;
import model.gui.path.TowerBehavior;
import model.gui.touch.Touch;
/**
 * A Tower is an abstract object that extends GridItem. 
 * 
 * @author leung, Eric
 * @version 1
 * @attributes GridColor - Color on the grid
 * @attributes cooldownRemaining - Cooldown for tower
 * @attributes range - range of tower
 * @throw
 * @return
 */

public abstract class Tower extends GridItem {

	////Attributes ////
	protected long cooldownRemaining;
	protected int range;
	
	public Tower(Coord coord) {
		super(coord, new Animation(), new GridPosition(0, 0), GridColor.RED);
		
	}
	
	public long getCooldownRemaining(){
		return this.cooldownRemaining;
	}
	
	public int getRange(){
		return this.range;
	}
	
	
	public void setCooldownRemaining(long cd){
		this.cooldownRemaining = cd;
	}
	
	public boolean isInRange(Coord cd){
		if(range <= Math.sqrt(Math.pow(cd.getX()+coord.getX(),2)+Math.pow(cd.getY()+coord.getY(),2))){
			return true;
		}
		return false;	
	}
	
	public String toString(){
		String str = "";
		switch(getGridColor()){
		case RED: str += "Red Tower "; break;
		case BLUE: str += "Blue Tower "; break;
		case GREEN: str += "Green Tower "; break;
		default: str += "Undefined Color Tower "; break;
		}
		str += "Grid Posn: " + gridPosition.toString() + " Pixel Posn " + coord.toString() + " ";
		str += "Cooldown Remaining = " + Long.toString(cooldownRemaining);
		return str;
	}
	
	
	
	//// Methods ////
	
	public void release(){
		System.out.println("JK\n\n\n\n\n\n");
		if(Touch.getInstance().isHolding()){
			GridItem gi = Touch.getInstance().unClamp();
			if(gi instanceof TrailItem){
				if(gi.getGridColor()==this.getGridColor() || gi.getGridColor() == GridColor.WHITE){
					this.react(gi, this.getGridColor());
				}
				else{
					Path.snap();
				}
			}
			else if(gi instanceof Tower){
				Path.snap();
			}
		}
	}
	
	public void react(GridItem gi, GridColor towerColor){
		if(gi.getGridColor() == towerColor){
			if(gi instanceof Oyster){
				//ADD OYSTER
			}
			else{
				//INCREASE HAPPINESS
			}
		}
		else{
			//DECREASE HAPPINESS
		}
	}
	
	@Override
	public void update(long elapsedTime){
		if(cooldownRemaining > 0){
			cooldownRemaining -= elapsedTime;
		}
	}
}
