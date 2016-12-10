package tests;

import static org.junit.Assert.*;

import model.Button;

import org.junit.Test;

import controller.Controller;
import model.Model;
import model.StartButton;
import model.TutorialButton;
import model.grid.Grid;
import view.View;

public class ButtonTest {

	@Test
	public void test() {
		Model m = new Model();
		m.initialize(1000, 1000);
		Controller c = new Controller(m, new View(m, ""));
		StartButton sb = new StartButton(0, 0, 1, 1);
		sb.mouseClicked(0, 0);
		assertFalse(c.isRunningTutorial());
		sb.mouseReleased(0, 0);
		
		Button t = new TutorialButton(0, 0, 1, 1);
		t.mouseClicked(0, 0);
		t.mouseReleased(0, 0);
		assertTrue(c.isRunningTutorial());
		
	}

}
