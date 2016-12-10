package model.moving;

import model.Model;
import model.Time;
import model.difficulty.Difficulty;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.PixelGrid;
import model.grid.gridcell.Acceleration;
import model.grid.gridcell.GridCell;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridColor;
import model.grid.griditem.GridItem;
import model.grid.griditem.trailitem.Larvae;
import model.grid.griditem.trailitem.Oyster;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.path.DestroyBehavior;
import model.gui.path.Path;
import model.player.Player;

/**
 * MovableItem is a class that all objects which move through the trail must
 * inherit from. This class provides all the logic necessary to make the objects
 * move through the trail according to the time for each loop of the game.
 * 
 * @author Gifan, Eric
 */
public class MovableObject extends GridItem {

    private static final double INITIAL_VELOCITY = PixelGrid.getInstance().getSquareHeight(); // per second
    private static double MAX_VELOCITY = INITIAL_VELOCITY / Time.nanosecond;
    private static final double FRICTION = 0.9999;
    
    private Velocity velocity;
    private Acceleration lastAcceleration;

    /**
     * Constructs a MovableObject object.
     * 
     * @param coord See {@see GridItem} constructor
     * @param animation See {@see GridItem} constructor
     * @param gridPosition See {@see GridItem} constructor
     * @param gc See {@see GridItem} constructor
     * @param velocity a Velocity object representing starting velocity
     */
	public MovableObject(Coord coord, Animation animation, GridPosition gridPosition, GridColor gc, Velocity velocity) {
		super(coord, animation, gridPosition, gc);
		this.velocity = velocity;
	}
	
	/**
	 * Sets the maximum velocity any MovableObject can have
	 * 
	 * @param speed a double representing the new maximum velocity
	 */
	public static void setMaxSpeed(double speed){
		MAX_VELOCITY = speed / Time.nanosecond;
	}
	
	/**
	 * Moves a MovableObject through the trail depending on elapsedTime.
	 * 
	 * @param elapsedTime See {@link controller.Controller#run(long)} 
	 */
	public void move(long elapsedTime) {
		GridCell currentGridCell = PixelGrid.getInstance().getGridCell(PixelGrid.getInstance().getGridPosition(this.getCoord()));
		Acceleration accel = currentGridCell.getAcceleration();
		double xAccel = accel.getX()*elapsedTime;
		double yAccel = accel.getY()*elapsedTime;
		double xVel = this.getVelocity().getX() + xAccel;
		double yVel = this.getVelocity().getY() + yAccel;
		
		if(xVel >= MAX_VELOCITY)
			xVel = MAX_VELOCITY;
		if(yVel >= MAX_VELOCITY)
			yVel = MAX_VELOCITY;
		if(xVel <= -MAX_VELOCITY)
			xVel = -MAX_VELOCITY;
		if(yVel <= -MAX_VELOCITY)
			yVel = -MAX_VELOCITY;
		
		this.getVelocity().setX(xVel);
		this.getVelocity().setY(yVel);
		
		this.applyFriction(xAccel == 0, yAccel == 0);
		
		double xCoord = this.getCoord().getX() + (xVel * elapsedTime);
		double yCoord = this.getCoord().getY() + (yVel * elapsedTime);
		
		if(PixelGrid.getInstance().isOutsideGrid(new Coord(xCoord, yCoord))){
			Grid.getInstance().removeItem(this);
			Grid.getInstance().addPath(new Path(this, new Coord(this.getCoord().getX(),
						Model.getInstance().getScreenHeight() + 50), new DestroyBehavior()));
			if ((TrailItem)this instanceof Larvae){
				Player.getInstance().increaseHappiness(2);
				Difficulty.collect((TrailItem)this);
			}else if(this instanceof Oyster){
					
			} else {
				Player.getInstance().decreaseHappiness(2);
			}
			return;
		}
		
		GridCell nextGridCell = PixelGrid.getInstance().getGridCell(PixelGrid.getInstance().getGridPosition(new Coord(xCoord, yCoord)));
		if(!nextGridCell.isTrail()){
			moveLastAcceleration(elapsedTime);
		} else {
			this.getCoord().setX(xCoord);
			this.getCoord().setY(yCoord);
			this.setGridPosition(nextGridCell.getGridPosition());
			this.lastAcceleration = accel;
			return;
		}
	}
	
	/**
	 * Does the same thing as move but with a different acceleration value.
	 */
	private void moveLastAcceleration(long elapsedTime){
		Acceleration accel = lastAcceleration;
		
		double xAccel = accel.getX()*elapsedTime;
		double yAccel = accel.getY()*elapsedTime;
		
		double xVel = this.getVelocity().getX() + xAccel;
		double yVel = this.getVelocity().getY() + yAccel;
		
		if(xVel >= MAX_VELOCITY)
			xVel = MAX_VELOCITY;
		if(yVel >= MAX_VELOCITY)
			yVel = MAX_VELOCITY;
		if(xVel <= -MAX_VELOCITY)
			xVel = -MAX_VELOCITY;
		if(yVel <= -MAX_VELOCITY)
			yVel = -MAX_VELOCITY;
		
		this.getVelocity().setX(xVel);
		this.getVelocity().setY(yVel);
		
		this.applyFriction(xAccel == 0, yAccel == 0);
		
		double xCoord = this.getCoord().getX() + (xVel * elapsedTime);
		double yCoord = this.getCoord().getY() + (yVel * elapsedTime);
		
		if(PixelGrid.getInstance().isOutsideGrid(new Coord(xCoord, yCoord))){
			Grid.getInstance().removeItem(this);
			Grid.getInstance().addPath(new Path(this, new Coord(this.getCoord().getX(),
						Model.getInstance().getScreenHeight() + 50), new DestroyBehavior()));
			if ((TrailItem)this instanceof Larvae){
				Player.getInstance().increaseHappiness(2);
				Difficulty.collect((TrailItem)this);
			}else if(this instanceof Oyster){
					
			} else {
				Player.getInstance().decreaseHappiness(2);
			}
			return;
		}
		
		GridCell nextGridCell = PixelGrid.getInstance().getGridCell(PixelGrid.getInstance().getGridPosition(new Coord(xCoord, yCoord)));
		this.getCoord().setX(xCoord);
		this.getCoord().setY(yCoord);
		this.setGridPosition(nextGridCell.getGridPosition());
		this.lastAcceleration = accel;
		return;
	}
	
	/**
	 * Multiplies a MovableObject object's velocity by FRICTION
	 * 
	 * @param x a boolean which is true whenever x is 0
	 * @param y a boolean which is true whenever y is 0
	 */
	private void applyFriction(boolean x, boolean y){
		if(x){
			this.getVelocity().setX(this.getVelocity().getX() * FRICTION);
		}
		if(y){
			this.getVelocity().setY(this.getVelocity().getY() * FRICTION);
		}
	}
	
	/**
	 * @return the velocity of a MovableObject
	 */
	public Velocity getVelocity(){
		return this.velocity;
	}
	
	/**
	 * @param velocity the new velocity of a MovableObject
	 */
	public void setVelocity(Velocity velocity){
        this.velocity = velocity;
    }
	
	//@Override
	public boolean update(long elapsedTime){
		move(elapsedTime);
		return false;
	}

}
