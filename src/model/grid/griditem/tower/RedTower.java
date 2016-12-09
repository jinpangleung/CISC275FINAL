package model.grid.griditem.tower;

import java.awt.Color;

import model.difficulty.Difficulty;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.GridColor;
import model.grid.griditem.GridItem;
import model.grid.griditem.trailitem.Pollutant;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.path.Path;
import model.gui.touch.Touch;
import model.player.Player;
import model.grid.gridcell.GridPosition;
import model.drawing.LockedAnimation;


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
	
	private boolean opening;

	public RedTower(Coord coord){
		super(coord, new LockedAnimation("pollutant_tower", 7), new GridPosition(0, 0), GridColor.RED, 
				new Color(255, 0, 0, getOpacity()));
		this.opening = false;
	}
	
	//@Override
	public void release(int mouseX, int mouseY){
		if(Touch.getInstance().isHolding()){
			if(this.isInRange(Touch.getInstance().getStartPosition())){
				GridItem gi = Touch.getInstance().unClamp();
				if(gi instanceof TrailItem){
					Difficulty.collect((TrailItem) gi);
					if(mouseX >= this.getCoord().getX()){
						this.reactTrash(gi);
					} else {
						this.reactRecycle(gi);
					}
				}
				else {
					Path.snap();
				}
			} else {
				Path.snap();
			}
		}
	}

	private void reactRecycle(GridItem gi) {
		if(gi instanceof Pollutant){
			Pollutant p = (Pollutant) gi;
			if(p.getRecycle()){
				Player.getInstance().increaseHappiness(1);
				return;
			} else {
				Player.getInstance().decreaseHappiness(1);
				return;
			}
		} else {
			Player.getInstance().decreaseHappiness(1);
		}
		
	}

	private void reactTrash(GridItem gi) {
		if(gi instanceof Pollutant){
			Pollutant p = (Pollutant) gi;
			if(!p.getRecycle()){
				Player.getInstance().increaseHappiness(1);
				return;
			} else {
				Player.getInstance().decreaseHappiness(1);
				return;
			}
		} else {
			Player.getInstance().decreaseHappiness(1);
		}
	}
	
	@Override
	public void mouseDragged(int x, int y){
		// Expand image
		if(this.isWithin(new Coord(x, y))){
			this.opening = true;
		} else {
			this.opening = false;
		}
	}
	
	@Override
	public boolean update(long timeElapsed){
		if(cooldownRemaining > 0){
			cooldownRemaining -= timeElapsed;
		}
		if(this.opening){
			this.getAnimation().update(timeElapsed);
		} else {
			this.getAnimation().reverseUpdate(timeElapsed);
		}
		return false;
	}
	
	public void close(){
		this.opening = false;
	}
}
