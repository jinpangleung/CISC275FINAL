package model.gui.path;

import model.Time;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.GridItem;
import model.grid.griditem.tower.Tower;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.touch.Touch;

/*
 * A Path is used to tell a Grid Items to move from one point of the screen to another
 */

public class Path {
	
	private static final long TIME_TO_COMPLETE_PATH = Time.nanosecond;
	
	private GridItem gi;
	private Coord destination;
	private double speed; // separate from Grid Item's speed
	private double angle;
	private PathVelocity velocity;
	private PathBehavior termination;
	private boolean posX;
	private boolean posY;
	
	public Path(GridItem gi, Coord destination, PathBehavior termination){
		this.gi = gi;
		this.destination = destination;
		this.termination = termination;
		this.initializeSpeed();
	}
	
	private void initializeSpeed(){
		Coord start = gi.getCoord();
		Coord end = destination;
		double distance = Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
		this.speed = distance / TIME_TO_COMPLETE_PATH;
		this.angle = Math.atan2(end.getY() - start.getY(), end.getX() - start.getX());
		this.velocity = new PathVelocity(this.speed * Math.cos(angle), this.speed * Math.sin(angle));
		this.posX = this.velocity.getX() >= 0;
		this.posY = this.velocity.getY() >= 0;
		
	}
	
	
	public static Path snap(){
		GridItem gi = Touch.getInstance().unClamp();
		Coord destination = Touch.getInstance().getStartPosition();
		Path p;
		if(gi instanceof TrailItem){
			p = new Path(gi, destination, new BackToGridBehavior());
		} else {
			gi.snapping = true;
			p = new Path(gi, destination, new TowerBehavior());
		}
		Grid.getInstance().addPath(p);
		return p;
	}
	
	
	//@Override
	public String toString(){
		String str = "";
		str += "Path:\n";
		str += gi.toString();
		str += destination.toString();
		str += Double.toString(speed);
		return str;
	}
	
	/**
	 * When the Grid is updating the path, the Grid must remove the Path when path.update returns true
	 * This is the only safe way to do it
	 * A path will not know whether or not it needs to be removed until it is update
	 * And the Grid is what updates it
	 * 
	 * @param elapsedTime
	 * @return true when path needs to be removed from Grid
	 */
	public boolean update(long elapsedTime){
		double origX = gi.getCoord().getX();
		double origY = gi.getCoord().getY();
		double vx = this.velocity.getX();
		double vy = this.velocity.getY();
		vx *= elapsedTime;
		vy *= elapsedTime;
		gi.setCoord(new Coord(origX + vx, origY + vy));
		
		if(this.finished()){
			termination.terminate(this);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean finished(){
		boolean isFinished = true;
		if(this.posX){
			isFinished = isFinished && gi.getCoord().getX() >= this.destination.getX();
		} else {
			isFinished = isFinished && gi.getCoord().getX() <= this.destination.getX();
		}
		if(this.posY){
			isFinished = isFinished && gi.getCoord().getY() >= this.destination.getY();
		} else {
			isFinished = isFinished && gi.getCoord().getY() <= this.destination.getY();
		}
		return isFinished;
	}
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public GridItem getGridItem() {
		return gi;
	}
	public void setGridItem(GridItem gi) {
		this.gi = gi;
	}
	public Coord getDestination() {
		return destination;
	}
	public void setDestination(Coord destination) {
		this.destination = destination;
	}
	public PathVelocity getVelocity(){
		return this.velocity;
	}
	public double getAngle(){
		return this.angle;
	}

}
