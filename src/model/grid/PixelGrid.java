package model.grid;

import model.drawing.Coord;
import model.grid.gridcell.GridCell;
import model.grid.gridcell.GridPosition;
import model.gui.component.ComponentPosition;

/**
 * PixelGrid is a class that implements the logic and behavior of a grid of pixels
 * (size of the window) underlying the Grid. In the PixelGrid different squares
 * of pixels correspond to each of the position of the GridCell.
 * 
 * @author Eric
 */
public class PixelGrid {
	
	private Board board;
	private double squareWidth; // absolute
	private double squareHeight; // absolute
	private ComponentPosition offset;
	private int width;
	private int height;
	private static PixelGrid instance;
	
	/**
	 * Constructs a PixelGrid
	 * @param b a Board to create the pixel grid around. We need the Board for
	 * access to GridCell objects.
	 */
	public PixelGrid(Board b){
		this.board = b;
		this.offset = Grid.getInstance().getTopLeft();
		this.width = Grid.getInstance().getWidth();
		this.height = Grid.getInstance().getHeight();
		this.squareWidth = (double) width / (double) this.board.getWidth();
		this.squareHeight = (double) height / (double) this.board.getHeight();
		PixelGrid.instance = this;
	}
	
	/**
	 * Gets the instance of a PixelGrid object.
	 * @return a PixelGrid object
	 */
	public static PixelGrid getInstance(){
		return PixelGrid.instance;
	}
	
	/**
	 * @param x an int for the x component for a pixel coordinate
	 * @param y an int for the y component for a pixel coordinate
	 * @return a GridPosition corresponding to the pixel coordinate given.
	 */
	public GridPosition getGridPosition(int x, int y){
		int gpX = (int) (((double) x - this.offset.getX()) / this.squareWidth);
		int gpY = (int) (((double) y - this.offset.getY()) / this.squareHeight);
		if(gpX >= board.getWidth() || gpY >= board.getHeight()){
			throw new PixelGridOutOfBoundsExcpetion();
		}
		return new GridPosition(gpX, gpY);
	}
	
	/**
	 * Gets the G
	 * 
     * @param x an int for the x component for a pixel coordinate
     * @param y an int for the y component for a pixel coordinate
	 * @return a GridCell as the GridPosition corresponding to the pixel coordinate
     * given.
	 */
	public GridCell getGridCell(int x, int y){
		return this.board.getGridCell(this.getGridPosition(x, y));
	}
	
	/**
	 * Returns the GridCell corresponding to the given GridPosition. Method
	 * is included to give developers access to specific GridCells.
	 * @param pos a GridPosition corresponding to the GridCell the client wishes to get.
	 */
	public GridCell getGridCell(GridPosition pos){
		return this.board.getGridCell(pos);
	}
	
	/**
	 * @param c a Coord to evaluate
	 * @return a boolean that is true if c is outside the square of pixels
	 * where the Grid lies
	 */
	public boolean isOutsideGrid(Coord c){
	    boolean outside = false;
	    if(c.getY().doubleValue() >= (double) Grid.getInstance().getBottomRight().getY()){
	        outside = true;
	    }
	    else if(c.getY().doubleValue() <= (double) Grid.getInstance().getTopLeft().getY()){
	        outside = true;
	    }
	    else if(c.getX().doubleValue() <= (double) Grid.getInstance().getTopLeft().getX()){
	        outside = true;
	    }
	    else if(c.getX().doubleValue() >= (double) Grid.getInstance().getBottomRight().getX()){
	        outside = true;
	    }
		return outside;
	}
	
	/**
	 * @return the width in pixels of a square of pixels corresponding to a single
	 * GridCell
	 */
	public double getSquareWidth(){
		return this.squareWidth;
	}
	
	/**
     * @return the height in pixels of a square of pixels corresponding to a single
     * GridCell
     */
	public double getSquareHeight(){
		return this.squareHeight;
	}
	
	/**
	 * @param gp a GridPosition to evaluate
	 * @return the top-center Coord corresponding to gp
	 */
	public Coord getCoord(GridPosition gp){
		double x = this.offset.getX() + (this.squareWidth * gp.getX()) + (this.squareWidth / 2.0);
		double y = this.offset.getY() + (this.squareHeight * gp.getY()) + 1; // stop off by 1 out of bounds errors
		return new Coord(x, y);
	}
	
	/**
     * @param gp a GridPosition to evaluate
     * @return the center Coord corresponding to gp
     */
	public Coord getCenter(GridPosition gp){
		double x = this.offset.getX() + (this.squareWidth * gp.getX()) + (this.squareWidth / 2);
		double y = this.offset.getY() + (this.squareHeight * gp.getY()) + (this.squareHeight / 2);
		return new Coord(x, y);
	}
	
	/**
	 * @param c a Coord to evaluate
	 * @return a GridPosition corresponding to v
	 */
	public GridPosition getGridPosition(Coord c){
		return getGridPosition(c.getX().intValue(), c.getY().intValue());
	}

}
