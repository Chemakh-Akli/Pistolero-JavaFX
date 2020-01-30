package ClassObjet;

import javafx.animation.AnimationTimer;

public class AnimationTim extends AnimationTimer{
	private long wait = 0;
	private long pause = 0;
	private boolean boolpause = false;
	long lastTime;

	@Override
	public void handle(long now) {
		// TODO Auto-generated method stub
	}

	public void endPause( ){
		wait+=System.nanoTime()-pause;
	}
	public long getPause(){
		return wait;
	}
	public void resetPause(){
		wait=0;
	}
	public void debutPause(){
		pause=System.nanoTime();
	}
	public boolean enPause() {
		return boolpause;
	}
	public void setPause(boolean pause) {
		this.boolpause = pause;
	}
	public void startTime(){
		lastTime = System.nanoTime();
	}

}
