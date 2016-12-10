package tests.view;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.MouseController;
import model.Model;
import model.drawing.Animation;
import view.ViewPanel;

public class ViewPanelTest {

	@Test
	public void testInitialize() {
		Model m = new Model();
		m.initialize(100, 100);
		Animation.initialize();
		
		ViewPanel panel = new ViewPanel();
		MouseController mcon = new MouseController(m);
		panel.initialize(mcon);
		
		assertEquals(1, panel.getMouseListeners().length);
		assertEquals(1, panel.getMouseMotionListeners().length);
	}

}
