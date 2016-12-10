package model.inventory.factory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import controller.Controller;
import model.drawing.Animation;
import model.TutorialStep;
import model.grid.Grid;
import model.gui.component.Component;
import model.gui.component.ComponentPosition;
import model.gui.path.Path;
import model.gui.touch.Touch;

/**
 * TowerFactory is a factory class that constructs Tower/Gabion
 * objects and puts them into Touch if the user has any remaining.
 * Note that each Tower object requires a certain amount of currency (a cost) to
 * create and towers vary by this amount.
 * 
 * @author Eric
 *
 */
public abstract class TowerFactory extends Component implements Serializable{
	
	protected int currency; // how many towers are left, or how many oysters you have
	protected int costPer; // how much each tower cost, oysters will probably be 4
	protected Animation animation;
	protected int x;
	protected int y;
	
	/**
     * Constructs a TowerFactory object.
     * 
     * @param topLeft See {@see Component} constructor
     * @param width See {@see Component} constructor
     * @param height See {@see Component} constructor
     */
	public TowerFactory(ComponentPosition topLeft, int width, int height){
		super(topLeft, width, height);
		this.currency = 2;
		this.costPer = 1;
	}
	
	/**
     * Constructs a TowerFactory object. Makes use of the other
     * constructor.
     * 
     * @param x x value of a {@see ComponentPosition}
     * @param y y value of a (@see ComponentPosition}
     * @param width {@see Component} constructor
     * @param height {@see Component} constructor
     */
	public TowerFactory(int x, int y, int width, int height){
		this(new ComponentPosition(x, y), width, height);
	}
	
	//@Override
	public abstract void mouseClicked(int mouseX, int mouseY);
	
	//@Override
	public void mouseReleased(int mouseX, int mouseY){
		if(Touch.getInstance().isHolding()){
			// If tutorial is running and player is holding tower then call correct action
			if(Controller.isRunningTutorial() && Grid.getInstance().getStep() == TutorialStep.PLACE_TOWER){
				Grid.getInstance().doneClickTowerUndo();
			}
			if(Controller.isRunningTutorial() && Grid.getInstance().getStep() == TutorialStep.PLACE_GABBION){
				Grid.getInstance().doneClickGabbionUndo();
			}
			
			Path.snap();
		}
	}
	
	/**
	 * Decreases player's remaining Tower/Gabion count by 1
	 */
	public void replaceTower(){
		this.currency += costPer;
	}
	
	/**
	 * @return an int representing the amount of remaining currency
	 */
	public int getCurrency(){
		return this.currency;
	}
	
	/**
     * @param c an int representing the amount of currency that should be remaining
     */
	public void setCurrency(int c){
		this.currency = c;
	}
	
	/**
	 * reduceCurrency() reduces the Tower/Gabion currency by amount
	 * @param amount an int representing the amount to subtract by
	 * @exception NegativeCurrencyException if currency is reduced to below 0
	 */
	public void reduceCurrency(int amount){
		this.currency -= amount;
		if(this.currency < 0){
			throw new NegativeCurrencyException();
		}
	}
	
	/**
	 * increaseCurrency() increases the Tower/Gabion currency by amount
	 * @param amount an int representing the amount of add by
	 */
	public void increaseCurrency(int amount){
		this.currency += amount;
	}
	
	/**
	 * @return an int representing the cost of a Tower/Gabion
	 */
	public int getCostPer(){
		return this.costPer;
	}
	
	/**
	 * @param c an representing what the cost of a Tower/Gabion should be
	 */
	public void setCostPer(int c){
		this.costPer = c;
	}
	
	//@Override
	public void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times Roman", Font.PLAIN, 20));
		int x = this.getBottomRight().getX() - ((this.getBottomRight().getX() - this.getTopLeft().getX()) / 2);
		int y = this.getBottomRight().getY() - ((this.getBottomRight().getY() - this.getTopLeft().getY()) / 2);
		animation.draw(g, x, y);
		g.drawString(Integer.toString(getCurrency()), this.getBottomRight().getX(), this.getBottomRight().getY());
	}

}
