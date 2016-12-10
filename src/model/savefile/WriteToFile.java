package model.savefile;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import controller.Controller;
import model.Model;
import model.grid.Grid;
import model.grid.griditem.gabion.Gabion;
import model.grid.griditem.tower.Tower;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.path.Path;
import model.inventory.Inventory;
import model.player.Player;

public class WriteToFile {
    public static void write(String fileName){
    	try{
	    	FileOutputStream fos = new FileOutputStream(fileName + ".ser");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(new ArrayList<TrailItem>(Grid.getInstance().getTrailItems()));
	        oos.writeObject(new ArrayList<Tower>(Grid.getInstance().getTowers()));
	        oos.writeObject(new ArrayList<Gabion>(Grid.getInstance().getGabions()));
	        oos.writeObject(new ArrayList<Path>(Grid.getInstance().getPaths()));
	        oos.writeObject(Player.getInstance());
	        oos.writeObject(Inventory.getInstance().getRtf());
	        oos.writeObject(Inventory.getInstance().getBtf());
	        oos.writeObject(Inventory.getInstance().getGtf());
	        oos.writeObject(Inventory.getInstance().getOgf());
	        oos.writeObject(Inventory.getInstance().getCgf());
	        oos.writeObject(new Long(Controller.getTime()));
	        oos.close();
    	} catch(Exception e){
    		System.out.println("Writing to file caused an exception");
    		e.printStackTrace();
    	}
    }
}
