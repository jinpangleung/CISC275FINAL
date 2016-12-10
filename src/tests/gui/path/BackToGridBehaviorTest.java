package tests.gui.path;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Model;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.GridItem;
import model.grid.griditem.trailitem.Pollutant;
import model.gui.path.BackToGridBehavior;
import model.gui.path.Path;

public class BackToGridBehaviorTest
{

    @Test
    public void testTerminate()
    {
        Model m = new Model();
        m.initialize(100, 100);
        Animation.initialize();
        BackToGridBehavior b = new BackToGridBehavior();
        GridItem item = new Pollutant(new Coord(0,0));
        Path p = new Path(item, new Coord(50,50), b);
        
        b.terminate(p);
        Grid.getInstance().update(0);
        
        assertEquals(true, Grid.getInstance().getTrailItems().contains(item));
    }
}
