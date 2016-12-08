package model.drawing;

public class LockedAnimation extends Animation {

	public LockedAnimation(String base, int num) {
		super(base, num);
		// TODO Auto-generated constructor stub
	}
	
	@Override
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
	
	
	
	@Override
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
