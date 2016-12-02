package model.grid.gridcell;

import model.Time;

/**
 * GridCell
 * 
 * @author Eric
 *
 */

public class GridCell {
	
	private GridPosition gridPosition;
	private Direction direction;
	private boolean isTrail;
	private boolean canPlaceTower;
	private boolean canPlaceGabion;
	
	public GridCell(GridPosition gp, Direction dir, boolean isTrail, boolean tower, boolean gabion){
		this.gridPosition = gp;
		this.direction = dir;
		this.isTrail = isTrail;
		this.canPlaceTower = tower;
		this.canPlaceGabion = gabion;
	}
	
	private static final double ACCELERATION = 1; // how much to accelerate every second, by pixel
	public static final double ACCELERATION_BY_NANOSECOND = ACCELERATION / Time.nanosecond;
	
	private static Acceleration[] accelerationByDirection;
	
	// GridCell must be statically initialized
	public static void initialize(){
		double diagonalAcceleration = ACCELERATION_BY_NANOSECOND * Math.cos(Math.toRadians(45));
		
		accelerationByDirection = new Acceleration[8];
		accelerationByDirection[Direction.NORTH.ordinal()] = 
				new Acceleration(0, -ACCELERATION_BY_NANOSECOND);
		accelerationByDirection[Direction.EAST.ordinal()] = 
				new Acceleration(ACCELERATION_BY_NANOSECOND, 0);
		accelerationByDirection[Direction.SOUTH.ordinal()] = 
				new Acceleration(0, ACCELERATION_BY_NANOSECOND);
		accelerationByDirection[Direction.WEST.ordinal()] = 
				new Acceleration(-ACCELERATION_BY_NANOSECOND, 0);
		accelerationByDirection[Direction.NORTHEAST.ordinal()] = 
				new Acceleration(diagonalAcceleration, -diagonalAcceleration);
		accelerationByDirection[Direction.NORTHWEST.ordinal()] = 
				new Acceleration(-diagonalAcceleration, -diagonalAcceleration);
		accelerationByDirection[Direction.SOUTHEAST.ordinal()] = 
				new Acceleration(diagonalAcceleration, diagonalAcceleration);
		accelerationByDirection[Direction.SOUTHWEST.ordinal()] = 
				new Acceleration(-diagonalAcceleration, diagonalAcceleration);
	}
	
	public Acceleration getAcceleration(){
		return accelerationByDirection[this.direction.ordinal()];
	}

	public GridPosition getGridPosition() {
		return gridPosition;
	}

	public void setGridPosition(GridPosition gridPosition) {
		this.gridPosition = gridPosition;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean isTrail() {
		return isTrail;
	}

	public void setTrail(boolean isTrail) {
		this.isTrail = isTrail;
	}

	public boolean isCanPlaceTower() {
		return canPlaceTower;
	}

	public void setCanPlaceTower(boolean canPlaceTower) {
		this.canPlaceTower = canPlaceTower;
	}

	public boolean isCanPlaceGabion() {
		return canPlaceGabion;
	}

	public void setCanPlaceGabion(boolean canPlaceGabion) {
		this.canPlaceGabion = canPlaceGabion;
	}

}
