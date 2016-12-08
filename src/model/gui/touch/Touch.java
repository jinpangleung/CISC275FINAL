package model.gui.touch;

import java.awt.Color;
import java.awt.Graphics;

import model.drawing.DrawableObject;
import model.grid.Board;
import model.grid.Grid;
import model.grid.PixelGrid;
import model.grid.gridcell.GridCell;
import model.grid.griditem.GridItem;
import model.grid.griditem.gabion.Gabion;
import model.grid.griditem.tower.Tower;
import model.drawing.Coord;

/**
 * Touch
 * Touch keeps a pointer to a GridItem
 * Touch is exclusively used for dragging GridItems around the screen
 * Touch needs to be explicitly told when to pick something up and when to drop something using clamp and unClamp
 * The Touch pointer should never be null, because Touch should be able to draw the object it's holding
 * 
 * @author Eric
 *
 */

public class Touch {
	
	private GridItem holding;
	private GridItem nullObject;
	private boolean isHolding;
	private Coord startPosition;
	private static Touch instance;
	
	public Touch(){
		nullObject = new DefaultTouchObject();
		holding = nullObject;
		isHolding = false;
		startPosition = new Coord(0, 0);
		instance = this;
	}
	
	public static Touch getInstance(){
		return instance;
	}
	
	public void mouseDragged(int mouseX, int mouseY){
		if(this.isHolding){
			this.holding.setCoord(mouseX, mouseY);
		}
		for(Tower t : Grid.getInstance().getTowers()){
			t.mouseDragged(mouseX, mouseY);
		}
	}
	
	public void clamp(GridItem objectToBeClamped){
		if(this.isHolding){
			System.out.println("Touch is already holding something");
			System.out.println(holding);
			throw new UnableToClampException();
		} else {
			this.holding = objectToBeClamped;
			this.startPosition.setX(objectToBeClamped.getCoord().getX());
			this.startPosition.setY(objectToBeClamped.getCoord().getY());
			this.isHolding = true;
		}
		
	}
	
	public GridItem unClamp(){
		if(this.isHolding){
			GridItem gi = holding;
			holding = nullObject;
			this.isHolding = false;
			return gi;
		} else {
			throw new ImpossibleUnclampException();
		}
	}
	
	public void draw(Graphics g){
		if(isHolding){
			if(holding instanceof Gabion){
				highLight(g);
			}
			holding.draw(g);
		}
	}
	
	public void highLight(Graphics g){
		int width = Board.getInstance().getWidth();
		int height = Board.getInstance().getHeight();
		double squareWidth = PixelGrid.getInstance().getSquareWidth();
		double squareHeight = PixelGrid.getInstance().getSquareHeight();
		int smallWidth = (int)( squareWidth * 0.7);
		int smallHeight = (int) (squareHeight * 0.7);
		g.setColor(Color.YELLOW);
		for(int i = 0; i < width; i++){
			GridCell gc = Board.getInstance().getGridCell(i, height - 1);
			if(gc.isCanPlaceGabion() && noGabion(gc.getGridPosition().getX())){
				Coord center = PixelGrid.getInstance().getCenter(gc.getGridPosition());
				int x = center.getX().intValue();
				int y = center.getY().intValue();
				g.fillRect(x - (smallWidth / 2), y - (smallHeight / 2), smallWidth, smallHeight);
			}
		}
	}
	
	public boolean noGabion(int x){
		for(Gabion g : Grid.getInstance().getGabions()){
			if(g.getGridPosition().getX() == x){
				return false;
			}
		}
		return true;
	}
	
	
	public GridItem getHolding() {
		return holding;
	}
	
	public Coord getStartPosition(){
		return startPosition;
	}

	public void setHolding(GridItem holding) {
		this.holding = holding;
	}

	public DrawableObject getNullObject() {
		return nullObject;
	}

	public void setNullObject(GridItem nullObject) {
		this.nullObject = nullObject;
	}

	public boolean isHolding() {
		return isHolding;
	}

	public void setHolding(boolean isHolding) {
		this.isHolding = isHolding;
	}

	public String toString(){
		String str = "";
		str += "Touch is holding...\n";
		str += holding.toString();
		return str;
	}

}
