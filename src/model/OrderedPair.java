package model;
import java.io.Serializable;

/**
 * OrderedPair is a class representing the mathematical concept of an ordered pair.
 * @author Eric
 * @param <T> The type of the ordered pair
 */
public abstract class OrderedPair<T> implements Serializable {
	
	// Attributes
	private T x;
	private T y;
	
	// Constructor
	public OrderedPair(T x, T y){
		this.x = x;
		this.y = y;
	}
	
	// Getters / Setters
	public T getX(){
		return x;
	}
	public T getY(){
		return y;
	}
	public void setX(T x){
		this.x = x;
	}
	public void setY(T y){
		this.y = y;
	}
	
	//@Override
	public boolean equals(Object o){
		if(this.getClass() != o.getClass()){
			return false;
		}
		if(o instanceof OrderedPair<?>){
			OrderedPair<?> op = (OrderedPair<?>) o;
			return this.getX().equals(op.getX()) && this.getY().equals(op.getY());
		} else {
			return false;
		}
	}
	
}
