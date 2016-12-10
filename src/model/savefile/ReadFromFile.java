package model.savefile;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;

import controller.Controller;
import model.Model;
import model.grid.Grid;
import model.grid.griditem.gabion.Gabion;
import model.grid.griditem.tower.Tower;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.path.Path;
import model.inventory.Inventory;
import model.inventory.factory.*;
import model.player.Player;

public class ReadFromFile {
	
	
	public static void read(String fileName){
		try{
			FileInputStream fis = new FileInputStream(fileName + ".ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        /*
	        Grid.getInstance().unReady();
	        Grid gStart = Grid.getInstance();
	        Model m = new Model();
	        m.initialize(Model.getInstance().getScreenWidth(), Model.getInstance().getScreenHeight());
	        */
	        Grid.getInstance().unReady();
	        List<TrailItem> trailItems = (ArrayList<TrailItem>) ois.readObject();
	        List<Tower> towers = (ArrayList<Tower>) ois.readObject();
	        List<Gabion> gabions = (ArrayList<Gabion>) ois.readObject();
	        List<Path> paths = (ArrayList<Path>) ois.readObject();
	        Player p = (Player) ois.readObject();
	        RedTowerFactory rtf = (RedTowerFactory) ois.readObject();
	        BlueTowerFactory btf = (BlueTowerFactory) ois.readObject();
	        GreenTowerFactory gtf = (GreenTowerFactory) ois.readObject();
	        OysterGabionFactory ogf = (OysterGabionFactory) ois.readObject();
	        ConcreteGabionFactory cgf = (ConcreteGabionFactory) ois.readObject();
	        Long time = (Long) ois.readObject();
	        Grid.getInstance().setTrailItems(trailItems);
	        Grid.getInstance().setTowers(towers);
	        Grid.getInstance().setGabions(gabions);
	        Grid.getInstance().setPaths(paths);
	        Inventory.getInstance().setRtf(rtf);
	        Inventory.getInstance().setBtf(btf);
	        Inventory.getInstance().setGtf(gtf);
	        Inventory.getInstance().setOgf(ogf);
	        Inventory.getInstance().setCgf(cgf);
	        Controller.setTime(time);
	        Player.setInstance(p);
	        Grid.getInstance().ready();
	        /*
	        Grid.getInstance().ready();
	        m.setTitleScreen(false);
	        if(gStart.equals(Grid.getInstance())){
	        	System.out.println("WTF");
	        }
	        */
	        ois.close();
	        /*
	        Model.setInstance(m);
	        Controller.getInstance().setModel(m);
	        return m;
	        */
		} catch (Exception e){
        	System.out.println("Excpetion while reading file");
        	e.printStackTrace();
        	// return null;
        }
	}

}
