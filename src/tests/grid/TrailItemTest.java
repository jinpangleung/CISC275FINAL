package tests.grid;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Model;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.GridItem;
import model.grid.griditem.tower.BlueTower;
import model.grid.griditem.trailitem.Pollutant;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.path.BackToGridBehavior;
import model.gui.path.Path;
import model.gui.touch.Touch;
import model.inventory.factory.RedTowerFactory;

public class TrailItemTest
{

    @Test
    public void testClick1()
    {
        Model m = new Model();
        m.initialize(100, 100);
        Animation.initialize();
        
        TrailItem item = new Pollutant(new Coord(10,10));
        RedTowerFactory fac = new RedTowerFactory(0,0,10,10);
        fac.mouseClicked(5, 5);
        Grid.getInstance().placeTower(50, 50);
        Grid.getInstance().update(0);
        
        assertEquals(true, item.click());
    }
    
    @Test
    public void testClick2()
    {
        Model m = new Model();
        m.initialize(100, 100);
        Animation.initialize();
        
        TrailItem item = new Pollutant(new Coord(50,50));
        RedTowerFactory fac = new RedTowerFactory(0,0,10,10);
        fac.mouseClicked(5, 5);
        Grid.getInstance().placeTower(10, 10);
        Grid.getInstance().update(0);
        
        assertEquals(false, item.click());
    }

}
