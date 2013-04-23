package com.zlawrence.codebreaker;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Class for running the Codebreaker game.
 * @author Zachary Lawrence
 * @since 4/22/2013
 */
public class Game {
	private Code codeToBreak;
	private ArrayDeque<Byte> codeDeque = new ArrayDeque<Byte>();
	private ArrayDeque<Byte> solvedStack = new ArrayDeque<Byte>();
	
	/**
	 * Constructor for the game to play.
	 * @param size The size of the code to create.
	 */
	public Game(short size) {
		this.codeToBreak = new Code(size);
		this.codeDeque = codeToBreak.getCodeDeque();
	}
	
	/**
	 * Method for playing the game.
	 * @param timeDuration The amount of time for the game to last, in seconds.
	 */
	public void playGame(int timeDuration) {
		Scanner input = new Scanner(System.in);
		GameTimer timer = new GameTimer(this, timeDuration);
		timer.set(); //set our game timer
		codeToBreak.printCode();
		
		/**
		 * ALGORITHM: Compare user input to top of the codeDeque. If the user enters
		 * the correct number, take number off the top of codeDeque and push onto
		 * the solvedStack. If the user enters the wrong number, pop everything off
		 * the solvedStack back onto the codeDeque (this makes the code "start over").
		 */
		while (true) {
			if (codeDeque.isEmpty()) { //if the codeDeque is empty, user wins
				this.gameWon();
			} else if (codeDeque.getFirst().compareTo(input.nextByte()) == 0) {
				//if user guesses next number correctly, continues
				solvedStack.addFirst(codeDeque.removeFirst());
			} else {
				//otherwise, start over
				System.out.println("WRONG, TRY AGAIN...");
				for (Byte b : solvedStack) {
					codeDeque.addFirst(b); //"pop" everything from solved stack back to the codeDeque
				}
				solvedStack.clear(); //since we didn't actually "pop" above, clear the stack
			}
		}
	}
	
	/**
	 * Simple method for win condition.
	 */
	public void gameWon() {
		System.out.println("YOU CRACKED THE CODE!");
		System.exit(0);
	}
	
	/**
	 * Method for losing condition (time runs out)
	 */
	public void timeExpired() {
		System.out.println("GAME OVER");
		System.out.print("The code was: ");
		codeToBreak.printCode();
		System.out.println("You had " + codeDeque.size() + " numbers left to break");
		System.exit(0);
	}

}
