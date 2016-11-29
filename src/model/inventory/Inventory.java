package model.inventory;

import java.awt.Graphics;

import model.grid.GridColor;
import model.grid.griditem.gabion.GabionType;
import model.gui.component.ComponentPosition;
import model.inventory.factory.*;

/**
 * Inventory
 * where the towers and gabions are going to be shown intially before players drag it onto
 * the grid
 * 
 * @author eric
 *
 */

public class Inventory {
	
	private RedTowerFactory rtf;
	private BlueTowerFactory btf;
	private GreenTowerFactory gtf;
	private ConcreteGabionFactory cgf;
	private OysterGabionFactory ogf;
	private static Inventory instance;
	public static final double FACTORY_X_START = .85;
	public static final double FACTORY_SIZE = .05;
	
	private Inventory(){
	}
	
	public static Inventory getInstance(){
		if(instance == null){
			instance = new Inventory();
		}
		
		return instance;
	}
	
	public void initialize(int screenWidth, int screenHeight){
		rtf = new RedTowerFactory(
				new ComponentPosition((int) (screenWidth * FACTORY_X_START), (int) (screenHeight * .25)),
				(int) (screenWidth * FACTORY_SIZE), (int) (screenHeight * FACTORY_SIZE));
		btf = new BlueTowerFactory(
				new ComponentPosition((int) (screenWidth * FACTORY_X_START), (int) (screenHeight * .35)),
				(int) (screenWidth * FACTORY_SIZE), (int) (screenHeight * FACTORY_SIZE));
		gtf = new GreenTowerFactory(
				new ComponentPosition((int) (screenWidth * FACTORY_X_START), (int) (screenHeight * .45)),
				(int) (screenWidth * FACTORY_SIZE), (int) (screenHeight * FACTORY_SIZE));
		cgf = new ConcreteGabionFactory(
				new ComponentPosition((int) (screenWidth * FACTORY_X_START), (int) (screenHeight * .55)),
				(int) (screenWidth * FACTORY_SIZE), (int) (screenHeight * FACTORY_SIZE));
		ogf = new OysterGabionFactory(
				new ComponentPosition((int) (screenWidth * FACTORY_X_START), (int) (screenHeight * .65)),
				(int) (screenWidth * FACTORY_SIZE), (int) (screenHeight * FACTORY_SIZE));
	}
	
	public TowerFactory getTowerFactory(GridColor gc){
		switch(gc){
		case RED: return rtf;
		case BLUE: return btf;
		case GREEN: return gtf;
		default: throw new RuntimeException();
		}
	}
	
	public TowerFactory getGabionFactory(GabionType gt){
		switch(gt){
		case CONCRETE: return cgf;
		case OYSTER: return ogf;
		default: throw new RuntimeException();
		}
	}
	
	public void draw(Graphics g){
		rtf.draw(g);
		btf.draw(g);
		gtf.draw(g);
		cgf.draw(g);
		ogf.draw(g);
	}

}
