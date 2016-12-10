package model.grid.griditem.tower;

import java.awt.Color;

import model.difficulty.Difficulty;
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
 * @author 
 * @attributes opening - true if tower is expanding, false otherwise
 * @throw
 * @return
 */
public class RedTower extends Tower {
	
	private boolean opening; //if tower is bins or not

	/**
	 * Initializes the tower to have a range which is set by screen size
	 * @param coord - Coordinate position
	 * @return none
	 */
	public RedTower(Coord coord){
		super(coord, new LockedAnimation("pollutant_tower", 7), new GridPosition(0, 0), GridColor.RED, 
				new Color(255, 0, 0, getOpacity()));
		this.opening = false;
	}
	
	/**
	 * Reacts to mouse release
	 * @param mouseX - mouse x position
	 * @param mouseY - mouse y position
	 * @return none
	 */
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

	/**
	 * Reacts to item dropped in recycling bin
	 * @param gi - grid item
	 * @return none
	 */
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

	/**
	 * Reacts to item dropped in trash can
	 * @param gi - grid item
	 * @return none
	 */
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
	
	/**
	 * Reacts to mouse being dragged
	 * @param x - x value
	 * @param y - y value
	 * @return none
	 */
	@Override
	public void mouseDragged(int x, int y){
		// Expand image
		if(this.isWithin(new Coord(x, y))){
			this.opening = true;
		} else {
			this.opening = false;
		}
	}
	
	/**
	 * Update method, just returns false
	 * @param elapsedTime - time elapsed
	 * @return false
	 */
	@Override
	public boolean update(long timeElapsed){
		if(this.opening){
			this.getAnimation().update(timeElapsed);
		} else {
			this.getAnimation().reverseUpdate(timeElapsed);
		}
		return false;
	}
	
	/**
	 * sets opening to false
	 * @param none
	 * @return none
	 */
	public void close(){
		this.opening = false;
	}
	
	/**
	 * Gets opening
	 * @param none
	 * @return opening
	 */
	public boolean getOpening(){
		return opening;
	}
}
