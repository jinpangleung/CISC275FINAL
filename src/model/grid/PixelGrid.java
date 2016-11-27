package model.grid;

import model.drawing.Coord;
import model.grid.gridcell.GridCell;
import model.grid.gridcell.GridPosition;


/**
 * PixelGrid
 * it helps for moveableObject, so the animation can move smoothly, instead of jumping from
 * gridcell to gridcell
 * 
 * @author Roy, Eric
 *
 */

public class PixelGrid {
	
	public int squareWidth;
	public int squareHeight;

	
	public PixelGrid(){
		Grid g = Grid.getInstance();
		int width = g.getBottomRight().getX() - g.getTopLeft().getX();
		int height = g.getBottomRight().getY() - g.getTopLeft().getY();
		int numSquareWidth = g.getSquareWidth();
		int numSquareHeight = g.getSquareHeight();
		squareWidth = width / numSquareWidth;
		squareHeight = height / numSquareHeight;
	}
	
	public GridPosition getGridCell(int x, int y){
		//get offset X and Y position
				int offsetX = Grid.getInstance().getTopLeft().getX();
				int offsetY = Grid.getInstance().getTopLeft().getY();
				//get coord X and Y position
				int coordX = x;
				int coordY = y;
				//get exact X and Y position
				int xPos = (coordX - offsetX) / squareWidth;
				int yPos = (coordY - offsetY) / squareHeight;
				
				System.out.println(Integer.toString(coordX) + " - " + Integer.toString(offsetX)
						+ " / " + Integer.toString(squareWidth) + " = " + Integer.toString(xPos));
				//crash program if goes out of grid
				if(xPos >= Grid.getInstance().getSquareWidth() || xPos < 0 
						|| yPos >= Grid.getInstance().getSquareHeight() || yPos < 0){
					System.out.println(Integer.toString(xPos) + ", " + Integer.toString(yPos));
					throw new OutOfGridException();
				}
				return new GridPosition(xPos, yPos);
	}
	
	public GridPosition getGridCell(Coord coord){
		return getGridCell((int) coord.getX(), (int) coord.getY());
	}

	
	public Coord getValidCoord(GridPosition gp){
		int offsetX = Grid.getInstance().getTopLeft().getX();
		int offsetY = Grid.getInstance().getTopLeft().getY();
		int posX = gp.getX() * squareWidth;
		int posY = gp.getY() * squareHeight;
		return new Coord(posX + offsetX, posY + offsetY);
	}
}
