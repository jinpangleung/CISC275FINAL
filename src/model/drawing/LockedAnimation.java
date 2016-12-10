package model.drawing;

/**
 * LockedAnimation is a class that encapsulates an animation
 * sequence much like Animation. However sequences encapsulated
 * in LockedAnimation do not loop.
 * 
 * @author Eric
 */
public class LockedAnimation extends Animation {

    /**
     * Constructs a LockedAnimation object
     * 
     * @param base See {@see Animation} constructor
     * @param num See {@see Animation} constructor
     * @author Eric
     */
	public LockedAnimation(String base, int num) {
		super(base, num);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Changes the index of a certain animation sequence
	 * based on elapsedTime from the game loop. It changes the index in a
	 * forward manner.
	 * 
	 * @param elapsedTime a long representing the number of nanoseconds passed
	 * since the last update.
	 * @author Eric
	 */
	//@Override
	public void update(long elapsedTime){
		if(this.index == this.length - 1){
			return;
		} else {
			this.elapsedTime += elapsedTime;
			if(this.elapsedTime > animationTime){
				this.elapsedTime = animationTime;
			}
			this.elapsedTime = this.elapsedTime % this.animationTime;
			long newIndex = this.elapsedTime / this.frameTime;
			this.index = (int) newIndex;
		}	
	}
	
	
	/**
	 * Changes the index of a certain animation sequence
	 * based on elapsedTime from the game loop. It changes the index in a
	 * backward manner, to play an animation backward.
	 * 
	 * @param elapsedTime a long representing the number of nanoseconds passed
	 * since the last update.
	 * @author Eric
	 */
	//@Override
	public void reverseUpdate(long elapsedTime){
		if(this.index == 0){
			return;
		} else {
			this.elapsedTime -= elapsedTime;
			if(this.elapsedTime < 0){
				this.elapsedTime = 0;
			}
			this.elapsedTime = this.elapsedTime % this.animationTime;
			long newIndex = this.elapsedTime / this.frameTime;
			this.index = (int) newIndex;
		}
	}

}
