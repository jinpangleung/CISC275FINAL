package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Model;
import model.TitleScreen;

public class TitleScreenTest {

	@Test
	public void test() {
		Model m = new Model();
		m.initialize(1000, 1000);
		TitleScreen ts = new TitleScreen();
		ts.click(0, 0);
	}

}
