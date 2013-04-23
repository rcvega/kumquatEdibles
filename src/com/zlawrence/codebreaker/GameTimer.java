package com.zlawrence.codebreaker;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends TimerTask {
	
	Game currentGame;
	long timerDuration;
	
	GameTimer(Game currentGame, long timerDuration) {
		this.currentGame = currentGame;
		this.timerDuration = 1000*timerDuration;
	}
	
	public void set() {
		Timer timer = new Timer();
		timer.schedule(this, timerDuration);
	}
	
	public void run() {
	    currentGame.timeExpired();
	}

}
