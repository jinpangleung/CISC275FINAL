package model.moving;

import model.drawing.Animation;
import model.drawing.Coord;
import model.drawing.DrawableObject;
import model.grid.Grid;
import model.grid.PixelGrid;
import model.grid.griditem.GridColor;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridItem;
import model.grid.gridcell.Acceleration;
import model.grid.gridcell.GridCell;

/**
 * MovableObject
 * An Object that can be moved
 * Needs to have a velocity to dictate movement
 * A MovableObject is also a DrawableObject
 * @see DrawableObject
 * @author Eric
 *
 */

public abstract class MovableObject extends GridItem implements Movable {

	public MovableObject(Coord coord, Animation animation, GridPosition gridPosition, GridColor gc, Velocity velocity) {
		super(coord, animation, gridPosition, gc);
		this.velocity = velocity;
	}

	private Velocity velocity;
	private double maxVelocity;
	
	/**
	 * Moves all MovableObjects in the correct direction (by adjusting its velocity and position) 
	 * depending on where the object lies on the Board and the time passed since the last runthrough of the game loop.
	 * 
	 * @param elapsedTime a long representing the number of nanoseconds since the last
	 * runthrough of the game loop.
	 * @author Gifan Thadathil
	 */
	public void move(long elapsedTime){
		// 5. Check if grid position is non-trail, if it is then move object to old grid position and 
		// change coord position accordingly. May not be needed if velocities change sufficiently fast.
		
		
		// Sets up some variables we want access to and gets the Acceleration corresponding to this objects GridPosition.
		GridPosition objectPosition = this.getGridPosition();
		GridCell objectCell = PixelGrid.getInstance().getGridCell(objectPosition);
		Acceleration objectAccel = objectCell.getAcceleration();
		
		// Update this object's velocity according to objectAccel
		double newXVel = this.velocity.getX() + (objectAccel.getX()*elapsedTime);
		double newYVel = this.velocity.getY() + (objectAccel.getY()*elapsedTime);
		if(newXVel > maxVelocity){
			newXVel = maxVelocity;
		}
		if(newYVel > maxVelocity){
			newYVel = maxVelocity;
		}
		
		this.velocity.setX(newXVel);
		this.velocity.setY(newYVel);
		
		// Update pixel coordinates of this object
		Coord objectCoord = this.getCoord();
		this.setCoord(objectCoord.getX() + this.velocity.getX(), objectCoord.getY() + this.velocity.getY());
		
		// Update this object's grid position
		this.setGridPosition(PixelGrid.getInstance().getGridPosition((int) Math.round(this.getCoord().getX()), 
				(int) Math.round(this.coord.getY())));
				
	}

	public String toString(){
		String str = "";
		str += this.getCoord().toString() + "\n";
		str += this.getVelocity().toString() + "\n";
		return str;
	}
	
	public Velocity getVelocity(){
		return velocity;
	}
	
	public void setVelocity(Velocity velocity){
		this.velocity = velocity;
	}
	
	public double getMaxVelocity() {
		return maxVelocity;
	}

	public void setMaxVelocity(double maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	@Override
	public void update(long timeElapsed){
		move(timeElapsed);
	}
	
	

}