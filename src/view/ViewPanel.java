package view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import controller.MouseController;
import model.Model;
import model.savefile.Save;

/**
 * ViewPanel
 * Intended to be the only Panel in the View
 * Intended to cover the entire View
 * @author Eric
 *
 */

public class ViewPanel extends JPanel{
	
	private static final long serialVersionUID = -6692584106492189311L;
	
	private Model model;
	
	public ViewPanel(Model model){
		this.model = model;
		bindKeyWith("0.shift.press", KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.SHIFT_DOWN_MASK, true), new SaveAction(0));
		bindKeyWith("0.press", KeyStroke.getKeyStroke(KeyEvent.VK_0, 0, true), new LoadAction(0));
		bindKeyWith("1.shift.press", KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.SHIFT_DOWN_MASK, true), new SaveAction(1));
		bindKeyWith("1.press", KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, true), new LoadAction(1));
		bindKeyWith("2.shift.press", KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.SHIFT_DOWN_MASK, true), new SaveAction(2));
		bindKeyWith("2.press", KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, true), new LoadAction(2));
		bindKeyWith("3.shift.press", KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.SHIFT_DOWN_MASK, true), new SaveAction(3));
		bindKeyWith("3.press", KeyStroke.getKeyStroke(KeyEvent.VK_3, 0, true), new LoadAction(3));
		bindKeyWith("4.shift.press", KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.SHIFT_DOWN_MASK, true), new SaveAction(4));
		bindKeyWith("4.press", KeyStroke.getKeyStroke(KeyEvent.VK_4, 0, true), new LoadAction(4));
		bindKeyWith("5.shift.press", KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.SHIFT_DOWN_MASK, true), new SaveAction(5));
		bindKeyWith("5.press", KeyStroke.getKeyStroke(KeyEvent.VK_5, 0, true), new LoadAction(5));
		bindKeyWith("6.shift.press", KeyStroke.getKeyStroke(KeyEvent.VK_6, InputEvent.SHIFT_DOWN_MASK, true), new SaveAction(6));
		bindKeyWith("6.press", KeyStroke.getKeyStroke(KeyEvent.VK_6, 0, true), new LoadAction(6));
		bindKeyWith("7.shift.press", KeyStroke.getKeyStroke(KeyEvent.VK_7, InputEvent.SHIFT_DOWN_MASK, true), new SaveAction(7));
		bindKeyWith("7.press", KeyStroke.getKeyStroke(KeyEvent.VK_7, 0, true), new LoadAction(7));
		bindKeyWith("8.shift.press", KeyStroke.getKeyStroke(KeyEvent.VK_8, InputEvent.SHIFT_DOWN_MASK, true), new SaveAction(8));
		bindKeyWith("8.press", KeyStroke.getKeyStroke(KeyEvent.VK_8, 0, true), new LoadAction(8));
		bindKeyWith("9.shift.press", KeyStroke.getKeyStroke(KeyEvent.VK_9, InputEvent.SHIFT_DOWN_MASK, true), new SaveAction(9));
		bindKeyWith("9.press", KeyStroke.getKeyStroke(KeyEvent.VK_9, 0, true), new LoadAction(9));
	}
	
	public void initialize(MouseController mc){
		this.addMouseListener(mc);
		this.addMouseMotionListener(mc);
	}
	
	//@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Model.getInstance().draw(g);
	}
	
	protected void bindKeyWith(String name, KeyStroke evt, Action action) {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(evt, name);
        am.put(name, action);
    }
	
	public class SaveAction extends AbstractAction {

		int i;
        public SaveAction(int i) {
        	this.i = i;
        }
        
        //@Override
        public void actionPerformed(ActionEvent e){
        	Save.save(this.i);
        	System.out.println("Num Save " + Integer.toString(this.i));
        }

    }
	
	public class LoadAction extends AbstractAction {

		int i;
        public LoadAction(int i) {
        	this.i = i;
        }
        
        //@Override
        public void actionPerformed(ActionEvent e){
        	Save.read(this.i);
        	System.out.println("Num Load " + Integer.toString(this.i));
        }

    }

}
