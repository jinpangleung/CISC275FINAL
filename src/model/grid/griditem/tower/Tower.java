package model.grid.griditem.tower;

import java.awt.Color;
import java.awt.Graphics;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridColor;
import model.grid.griditem.GridItem;
import model.grid.griditem.trailitem.Oyster;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.path.Path;
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
	
	protected static int range;
	
	private static final double RANGE_PERCENT = 0.1; // percent of screen width the range should be
	
	protected Color color;
	private static final int OPACITY = 100;
	
	public Tower(Coord coord, Animation animation, GridPosition gp, GridColor gc, Color c) {
		super(coord, animation, gp, gc);
		cooldownRemaining = 10;
		this.color = c;
	}
	
	public static void initialize(int screenWidth, int screenHeight){
		double rangeDouble = screenWidth * RANGE_PERCENT;
		Tower.range = (int) rangeDouble;
	}
	
	public long getCooldownRemaining(){
		return this.cooldownRemaining;
	}
	
	public static int getRange(){
		return Tower.range;
	}
	
	
	public void setCooldownRemaining(long cd){
		this.cooldownRemaining = cd;
	}
	
	public boolean isInRange(Coord cd){
		return Tower.getRange() >= Math.sqrt(Math.pow(cd.getX() - this.getCoord().getX(), 2) +
											Math.pow(cd.getY() - this.getCoord().getY(), 2));
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
	
	public void release(int mouseX, int mouseY){
		if(Touch.getInstance().isHolding()){
			GridItem gi = Touch.getInstance().unClamp();
			if(gi instanceof TrailItem){
				this.react(gi);
			}
			else {
				Path.snap();
			}
		}
	}
	
	public void react(GridItem gi){
		if(gi.getGridColor() == this.getGridColor()){
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
	public boolean update(long elapsedTime){
		if(cooldownRemaining > 0){
			cooldownRemaining -= elapsedTime;
		}
		return false;
	}
	
	protected static int getOpacity(){
		return Tower.OPACITY;
	}
	
	@Override
	public void draw(Graphics g){
		if(Touch.getInstance().isHolding()){
				if(Touch.getInstance().getHolding().equals(this)){
					g.setColor(this.color);
					g.fillOval(this.getCoord().getX().intValue() - range, this.getCoord().getY().intValue() - range, 
							range*2, range*2);
				}
		}
		if(!snapping){
			
			switch(getGridColor()){
			case RED: g.setColor(Color.RED); break;
			case BLUE: g.setColor(Color.BLUE); break;
			case GREEN: g.setColor(Color.GREEN); break;
			default: g.setColor(Color.WHITE); System.out.println("THERE'S NO COLOR"); break;
			}
			
			
			g.drawOval(this.getCoord().getX().intValue() - range, this.getCoord().getY().intValue() - range, 
					range*2, range*2);
		}
		super.draw(g);
	}
}
