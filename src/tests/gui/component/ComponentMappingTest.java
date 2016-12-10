package tests.gui.component;

import static org.junit.Assert.*;

import org.junit.Test;

import model.gui.component.Component;
import model.gui.component.ComponentMapping;
import model.gui.component.DefaultComponent;

public class ComponentMappingTest {
	
	@Test
	public void testConstructor(){
		Component c1 = new DefaultComponent(0, 0, 100, 100);
		ComponentMapping cm1 = new ComponentMapping(c1, 100, 100);
		for(int i = 0; i < 100; i++){
			for(int j = 0; j < 100; j++){
				assertTrue(c1.equals(cm1.getComponent(i, j)));
			}
		}
	}
	
	@Test
	public void addComponentTest(){
		Component c1 = new DefaultComponent(0, 0, 100, 100);
		ComponentMapping cm1 = new ComponentMapping(c1, 100, 100);
		Component c2 = new DefaultComponent(10, 10, 90, 90);
		cm1.addComponent(c2);
		for(int i = 0; i < 100; i++){
			for(int j = 0; j < 100; j++){
				if(i >= 10 && j >= 10){
					assertTrue(c2.equals(cm1.getComponent(i, j)));
				} else {
					assertTrue(c1.equals(cm1.getComponent(i, j)));
				}
			}
		}
	}
	
	@Test
	public void mouseTest(){
		
		class Counter{
			String s = "";
			void add(String s){ this.s += s; }
			String getMessage(){ return s; }
		}
		final Counter m = new Counter();
		Component c1 = new Component(0, 0, 100, 100){
			Counter c = m;
			//@Override
			public void mouseClicked(int x, int y){
				c.add("c1 click " );
			}

			//@Override
			public void mouseReleased(int mouseX, int mouseY) {
				c.add("c1 release ");
			}
		};
		ComponentMapping cm1 = new ComponentMapping(c1, 100, 100);
		Component c2 = new Component(10, 10, 90, 90){

			Counter c = m;
			//@Override
			public void mouseClicked(int mouseX, int mouseY) {
				c.add("c2 click ");
				
			}

			//@Override
			public void mouseReleased(int mouseX, int mouseY) {
				c.add("c2 release ");
				
			}
		};
		cm1.addComponent(c2);
		cm1.mouseClicked(0, 0);
		cm1.mouseClicked(10, 10);
		cm1.mouseClicked(9, 10);
		cm1.mouseClicked(99, 99);
		cm1.mouseReleased(0, 0);
		cm1.mouseReleased(10, 10);
		cm1.mouseReleased(9, 10);
		cm1.mouseReleased(99, 99);
		assertTrue(m.getMessage().equals("c1 click c2 click c1 click c2 click c1 release c2 release c1 release c2 release "));
	}

}
