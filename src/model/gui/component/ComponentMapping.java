package model.gui.component;

/**
 * ComponentMapping
 * A 2D array that represents the desired "screen"
 * Each "pixel" on the "screen" has a pointer to which Component exists at that "pixel"
 * @attributes Component[][] gridOfComponents
 * @author Eric
 *
 */

public class ComponentMapping {
	
	private Component gridOfComponents[][]; // x,y
	
	public ComponentMapping(Component defaultComponent, int width, int height){
		this.gridOfComponents = new Component[width][height];
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				gridOfComponents[i][j] = defaultComponent;
			}
		}
	}
	
	public boolean addComponent(Component c){
		boolean overlap = false;
		for(int i = c.getTopLeft().getX(); i <= c.getBottomRight().getX(); i++){
			for(int j = c.getTopLeft().getY(); j <= c.getBottomRight().getY(); j++){
				if(gridOfComponents[i][j].getClass() != Component.class){
					overlap = true;
				}
				gridOfComponents[i][j] = c;
			}
		}
		return overlap;
	}
	
	public void mouseClicked(int mouseX, int mouseY){
		System.out.println("Mouse Clicked");
		this.getComponent(mouseX, mouseY).mouseClicked(mouseX, mouseY);
	}
	
	public void mouseReleased(int mouseX, int mouseY){
		//System.out.println("Component Mapping " + Integer.toString(mouseX) + ", " + Integer.toString(mouseY));
		System.out.println("Mouse Released");
		this.getComponent(mouseX, mouseY).mouseReleased(mouseX, mouseY);
	}
	
	public Component getComponent(int x, int y){
		return gridOfComponents[x][y];
	}

}
