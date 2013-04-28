package com.zlawrence.codebreaker;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for putting a time constraint on the game.
 * @author Zachary Lawrence
 * @since 4/22/2013
 */
public class GameTimer extends TimerTask {
	
	Game currentGame; //the current game in play
	long timerDuration; //the duration of the timer in milliseconds
	
	/**
	 * Constructor for GameTimer.
	 * @param currentGame The game in play.
	 * @param timerDuration The duration of the timer to create in seconds.
	 */
	GameTimer(Game currentGame, long timerDuration) {
		this.currentGame = currentGame;
		this.timerDuration = 1000*timerDuration; //convert to seconds
	}
	
	/**
	 * Method that "starts" the timer.
	 */
	public void set() {
		Timer timer = new Timer();
		timer.schedule(this, timerDuration);
	}
	
	/**
	 * When the timer is up, this method is executed.
	 */
	public void run() {
	    currentGame.timeExpired();
	}

}
