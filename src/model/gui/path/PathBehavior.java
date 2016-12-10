package model.gui.path;

/**
 * PathBehavior is a class that encapsulates the action to be performed
 * when a GridItem is done traveling along a Path.
 * 
 * @author Eric
 */

public interface PathBehavior {
	
    /**
     * The action to be performed when a GridItem traveling along a Path
     * reaches its destination
     * @param p a Path
     */
	public void terminate(Path p);

}
