package model.inventory;

import java.awt.Graphics;
import java.io.Serializable;

import model.grid.griditem.GridItem;
import model.grid.griditem.gabion.*;
import model.grid.griditem.tower.*;
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

public class Inventory implements Serializable {
	
	private RedTowerFactory rtf;
	public void setRtf(RedTowerFactory rtf) {
		this.rtf = rtf;
	}

	public void setBtf(BlueTowerFactory btf) {
		this.btf = btf;
	}

	public void setGtf(GreenTowerFactory gtf) {
		this.gtf = gtf;
	}

	public void setCgf(ConcreteGabionFactory cgf) {
		this.cgf = cgf;
	}

	public void setOgf(OysterGabionFactory ogf) {
		this.ogf = ogf;
	}

	private BlueTowerFactory btf;
	private GreenTowerFactory gtf;
	private ConcreteGabionFactory cgf;
	private OysterGabionFactory ogf;
	private static Inventory instance;
	public static final double FACTORY_X_START = .85;
	public static final double FACTORY_SIZE = .05;
	
	public Inventory(){
		instance = this;
	}
	
	public static Inventory getInstance(){
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
	
	public void draw(Graphics g){
		rtf.draw(g);
		btf.draw(g);
		gtf.draw(g);
		cgf.draw(g);
		ogf.draw(g);
	}
	
	public void replaceTower(GridItem gi){
		if(gi instanceof RedTower){
			rtf.replaceTower();
		} else if(gi instanceof BlueTower){
			btf.replaceTower();
		} else if(gi instanceof GreenTower){
			gtf.replaceTower();
		} else if(gi instanceof OysterGabion){
			ogf.replaceTower();
		} else if(gi instanceof ConcreteGabion){
			cgf.replaceTower();
		} else {
			throw new TriedToAddATrailItemToTowerFactoryException();
		}
	}

	public RedTowerFactory getRtf() {
		return rtf;
	}

	public BlueTowerFactory getBtf() {
		return btf;
	}

	public GreenTowerFactory getGtf() {
		return gtf;
	}

	public ConcreteGabionFactory getCgf() {
		return cgf;
	}

	public OysterGabionFactory getOgf() {
		return ogf;
	}
	
	

}
