package model.gui.touch;

/**
 * ImpossibleUnclampException
 * Touch tried to unclamp, but it was not holding anything
 * Fatal, shouldn't happen
 * Will not be able to find a GridItem to return
 * 
 * @author Eric
 *
 */

public class ImpossibleUnclampException extends RuntimeException {

	private static final long serialVersionUID = 2642752618741231943L;

}
