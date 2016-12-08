package model.moving;

import model.Model;
import model.Time;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.PixelGrid;
import model.grid.gridcell.Acceleration;
import model.grid.gridcell.GridCell;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridColor;
import model.grid.griditem.GridItem;
import model.gui.path.DestroyBehavior;
import model.gui.path.Path;
import model.player.Player;

public class MovableObject extends GridItem {

	public MovableObject(Coord coord, Animation animation, GridPosition gridPosition, GridColor gc, Velocity velocity) {
		super(coord, animation, gridPosition, gc);
		this.velocity = velocity;
	}
	
	private static final double INITIAL_VELOCITY = PixelGrid.getInstance().getSquareHeight(); // per second
	private static double maxVelocity = INITIAL_VELOCITY / Time.nanosecond;
	
	private static final double FRICTION = 0.9999;
	
	private Velocity velocity;

	public void move(long elapsedTime) {
		GridCell currentGridCell = PixelGrid.getInstance().getGridCell(PixelGrid.getInstance().getGridPosition(this.getCoord()));
		Acceleration accel = currentGridCell.getAcceleration();
		
		double xAccel = accel.getX()*elapsedTime;
		double yAccel = accel.getY()*elapsedTime;
		
		double xVel = this.getVelocity().getX() + xAccel;
		double yVel = this.getVelocity().getY() + yAccel;
		
		if(xVel >= maxVelocity)
			xVel = maxVelocity;
		if(yVel >= maxVelocity)
			yVel = maxVelocity;
		if(xVel <= -maxVelocity)
			xVel = -maxVelocity;
		if(yVel <= -maxVelocity)
			yVel = -maxVelocity;
		
		this.getVelocity().setX(xVel);
		this.getVelocity().setY(yVel);
		
		this.applyFriction(xAccel == 0, yAccel == 0);
		
		double xCoord = this.getCoord().getX() + (xVel * elapsedTime);
		double yCoord = this.getCoord().getY() + (yVel * elapsedTime);
		
		if(PixelGrid.getInstance().isOutsideGrid(new Coord(xCoord, yCoord))){
			Grid.getInstance().removeItem(this);
			Grid.getInstance().addPath(new Path(this, new Coord(this.getCoord().getX(),
						Model.getInstance().getScreenHeight() + 50), new DestroyBehavior()));
			Player.getInstance().decreaseHappiness(5);
			return;
		}
		
		GridCell nextGridCell = PixelGrid.getInstance().getGridCell(PixelGrid.getInstance().getGridPosition(new Coord(xCoord, yCoord)));
		if(!nextGridCell.isTrail()){
			return;
		} else {
			this.getCoord().setX(xCoord);
			this.getCoord().setY(yCoord);
			this.setGridPosition(nextGridCell.getGridPosition());
			return;
		}
	}
	
	private void applyFriction(boolean x, boolean y){
		if(x){
			this.getVelocity().setX(this.getVelocity().getX() * FRICTION);
		}
		if(y){
			this.getVelocity().setY(this.getVelocity().getY() * FRICTION);
		}
	}
	
	public Velocity getVelocity(){
		return this.velocity;
	}
	
	@Override
	public boolean update(long elapsedTime){
		move(elapsedTime);
		return false;
	}

}
