package model.gui.path;

import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.GridItem;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.touch.Touch;
import model.moving.Velocity;

/*
 * A Path is used to tell a Grid Items to move from one point of the screen to another
 */

public class Path {
	
	private GridItem gi;
	private Coord destination;
	private double speed; // separate from Grid Item's speed
	private Velocity velocity;
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
		Coord end = this.getDestination();
		long desiredNanoSeconds = 4000000000L; // How many nano seconds should it take for the path to finish
		// Currently it takes 1 second to complete a path
		double distance = Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
		this.speed = distance / desiredNanoSeconds;
		double angle = Math.atan2(end.getY() - start.getY(), end.getX() - start.getX());
		this.velocity = new Velocity(this.speed * Math.cos(angle), this.speed * Math.sin(angle));
		this.posX = this.velocity.getX() >= 0;
		this.posY = this.velocity.getY() >= 0;
		
	}
	
	public static void snap(){
		GridItem gi = Touch.getInstance().unClamp();
		Coord destination = Touch.getInstance().getStartPosition();
		Path p;
		if(gi instanceof TrailItem){
			p = new Path(gi, destination, new BackToGridBehavior());
		} else {
			p = new Path(gi, destination, new TowerBehavior());
		}
		p.initializeSpeed();
		System.out.println(p);
		Grid.getInstance().addPath(p);
	}
	
	@Override
	public String toString(){
		String str = "";
		str += "Path:\n";
		str += gi.toString();
		str += destination.toString();
		str += Double.toString(speed);
		return str;
	}
	
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
	
	private boolean finished(){
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
	
	private void removeFromGrid(){
		
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

}
