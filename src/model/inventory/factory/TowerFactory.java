package model.inventory.factory;

import java.awt.Color;
import java.awt.Graphics;

import controller.Controller;
import model.drawing.Animation;
import model.TutorialStep;
import model.grid.Grid;
import model.gui.component.Component;
import model.gui.component.ComponentPosition;
import model.gui.path.Path;
import model.gui.touch.Touch;

/**
 * TowerFactory
 * an abstract class of factory that creates towers
 * 
 * @author Eric
 *
 */

public abstract class TowerFactory extends Component {
	
	protected int currency; // how many towers are left, or how many oysters you have
	protected int costPer; // how much each tower cost, oysters will probably be 4
	protected Animation animation;
	protected int x;
	protected int y;
	
	public TowerFactory(ComponentPosition topLeft, int width, int height){
		super(topLeft, width, height);
		this.currency = 2;
		this.costPer = 1;
	}
	
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
	
	public void replaceTower(){
		this.currency += costPer;
	}
	
	public int getCurrency(){
		return this.currency;
	}
	
	public void setCurrency(int c){
		this.currency = c;
	}
	
	public void reduceCurrency(int ammount){
		this.currency -= ammount;
		if(this.currency < 0){
			throw new NegativeCurrencyException();
		}
	}
	
	public void increaseCurrency(int ammount){
		this.currency += ammount;
	}
	
	public int getCostPer(){
		return this.costPer;
	}
	
	public void setCostPer(int c){
		this.costPer = c;
	}
	
	@Override
	public void draw(Graphics g){
		int x = this.getBottomRight().getX() - ((this.getBottomRight().getX() - this.getTopLeft().getX()) / 2);
		int y = this.getBottomRight().getY() - ((this.getBottomRight().getY() - this.getTopLeft().getY()) / 2);
		animation.draw(g, x, y);
	}

}
