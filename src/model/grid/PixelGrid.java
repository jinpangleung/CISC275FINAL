package model.grid;

import model.drawing.Coord;
import model.grid.gridcell.GridCell;
import model.grid.gridcell.GridPosition;
import model.gui.component.ComponentPosition;

/**
 * PixelGrid
 * A class that provides a O(1) conversion from an x,y coordinate to a GridCell
 * @author eric
 *
 */

public class PixelGrid {
	
	private Board board;
	private double squareWidth; // absolute
	private double squareHeight; // absolute
	private ComponentPosition offset;
	private int width;
	private int height;
	private static PixelGrid instance;
	
	public PixelGrid(Board b){
		this.board = b;
		this.offset = Grid.getInstance().getTopLeft();
		this.width = Grid.getInstance().getWidth();
		this.height = Grid.getInstance().getHeight();
		this.squareWidth = (double) width / (double) this.board.getWidth();
		this.squareHeight = (double) height / (double) this.board.getHeight();
		PixelGrid.instance = this;
	}
	
	public static PixelGrid getInstance(){
		return PixelGrid.instance;
	}
	
	public GridPosition getGridPosition(int x, int y){
		int gpX = (int) (((double) x - this.offset.getX()) / this.squareWidth);
		int gpY = (int) (((double) y - this.offset.getY()) / this.squareHeight);
		if(gpX >= board.getWidth() || gpY >= board.getHeight()){
			throw new PixelGridOutOfBoundsExcpetion();
		}
		return new GridPosition(gpX, gpY);
	}
	
	public GridCell getGridCell(int x, int y){
		return this.board.getGridCell(this.getGridPosition(x, y));
	}
	
	/**
	 * Returns the GridCell corresponding to the given GridPosition. Method
	 * is included to give clients access to specific GridCells.
	 * @param pos a GridPosition corresponding to the GridCell the client wishes to get.
	 * @author Gifan Thadathil
	 */
	public GridCell getGridCell(GridPosition pos){
		return this.board.getGridCell(pos);
	}
	
	public boolean isOutsideGrid(Coord c){
		return c.getY().doubleValue() >= (double) Grid.getInstance().getBottomRight().getY();
	}
	
	public double getSquareWidth(){
		return this.squareWidth;
	}
	
	public double getSquareHeight(){
		return this.squareHeight;
	}
	
	public Coord getCoord(GridPosition gp){
		double x = this.offset.getX() + (this.squareWidth * gp.getX()) + (this.squareWidth / 2.0);
		double y = this.offset.getY() + (this.squareHeight * gp.getY()) + 1; // stop off by 1 out of bounds errors
		return new Coord(x, y);
	}
	
	public Coord getCenter(GridPosition gp){
		double x = this.offset.getX() + (this.squareWidth * gp.getX()) + (this.squareWidth / 2);
		double y = this.offset.getY() + (this.squareHeight * gp.getY()) + (this.squareHeight / 2);
		return new Coord(x, y);
	}
	
	public GridPosition getGridPosition(Coord c){
		return getGridPosition(c.getX().intValue(), c.getY().intValue());
	}

}
