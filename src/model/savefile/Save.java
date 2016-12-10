package model.savefile;

import controller.Controller;
import model.Model;

public class Save {
	
	private static final String BASE = "save";
	
	public static void save(int i){
		WriteToFile.write(BASE + Integer.toString(i));
	}
	
	public static void read(int i){
		ReadFromFile.read(BASE + Integer.toString(i));
	}

}
