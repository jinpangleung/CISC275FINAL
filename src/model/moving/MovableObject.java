package model.moving;

import model.drawing.Animation;
import model.drawing.Coord;
import model.drawing.DrawableObject;
import model.grid.Grid;
import model.grid.griditem.GridColor;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridItem;

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
	//final double MAXVELOCITY = Grid.getInstance().getWidthByHeight()/50;
	
	
	public void move(long elapsedTime){
		/*
		// Get Acceleration
		Acceleration a = Grid.getInstance().getAcceleration(this.getGridPosition(), elapsedTime);
		//double vx = this.getVelocity().getX() + (a.getX() *0.0005);
		//double vy = this.getVelocity().getY() + (a.getY() * 0.0005);	
		double vy = this.getVelocity().getY() * 0.000005 * elapsedTime;
		System.out.println(elapsedTime);
		this.getCoord().setY(this.getCoord().getY() + vy);
		/*
		Acceleration a = Grid.getInstance().getAcceleration(this.getGridPosition(), elapsedTime);//grabs acceleration of current
		double ax = a.getX();//for specifics
		double ay = a.getY();
		Velocity v = this.getVelocity();//grabs velocity of current
		double vx = v.getX();//for specifics
		double vy = v.getY();
		
		//specifics for readability, xx and yy represent acceleration + velocity which is velocity
		double xx =  ax + vx;
		double yy = ay + vy;
		
		//changes velocity
		v.setX(xx);
		v.setY(yy);
		
		//coordinate for positon
		double cx = this.getCoord().getX(); 
		double cy = this.getCoord().getY();
		
		
		//calculations for coordinate
		cx = cx + (xx * elapsedTime);
		cy = cy + (yy * elapsedTime);
		*/
		
		
		//checks if goes into next grid, if yes update grid position.
		//this.setGridPosition(Grid.getInstance().getGridCell(getCoord()).getGridPosition());
			
		//get grid position for checking block
		//get coor position for checking pixel
		//GridPosition gridPos = Grid.getGridPosition(coord);
		//this.setGridPosition(Grid.getInstance().getGridPosition(this.getCoord()));
		
		
		
	}
	//eventually move code from move() to this for readability
	public void updateVelocity(){
		//nothing yet
	}
	public String toString(){
		String str = "";
		str += this.getCoord().toString() + "\n";
		str += this.getVelocity().toString() + "\n";
		return str;
	}
	//eventually move code from move() to this for readability
	public void applyVelocity(){
		//nothing yet
	}
	
	public Velocity getVelocity(){
		return velocity;
	}
	
	public void setVelocity(Velocity velocity){
		this.velocity = velocity;
	}
	
	@Override
	public boolean update(long timeElapsed){
		//System.out.println(this);
		move(timeElapsed);
		return false;
		// TODO return when off grid
	}
	
	

}