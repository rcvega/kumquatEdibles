package com.zlawrence.codebreaker;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Game {
	private Code codeToBreak;
	private ArrayDeque<Byte> codeDeque = new ArrayDeque<Byte>();
	private ArrayDeque<Byte> solvedStack = new ArrayDeque<Byte>();
	
	public Game(short size) {
		this.codeToBreak = new Code(size);
		this.codeDeque = codeToBreak.getCodeDeque();
	}
	
	public void playGame(int timeDuration) {
		Scanner input = new Scanner(System.in);
		GameTimer timer = new GameTimer(this, timeDuration);
		timer.set();
		codeToBreak.printCode();
		while (true) {
			
			if (codeDeque.isEmpty()) {
				this.gameWon();
			} else if (codeDeque.getFirst().compareTo(input.nextByte()) == 0) {
				solvedStack.addFirst(codeDeque.removeFirst());
			} else {
				System.out.println("WRONG, TRY AGAIN...");
				for (Byte b : solvedStack) {
					codeDeque.addFirst(b);
				}
				solvedStack.clear();
			}
		}
	}
	
	public void gameWon() {
		System.out.println("YOU CRACKED THE CODE!");
		System.exit(0);
	}
	
	public void timeExpired() {
		System.out.println("GAME OVER");
		System.out.print("The code was: ");
		codeToBreak.printCode();
		System.out.println("You had " + codeDeque.size() + " numbers left to break");
		System.exit(0);
	}

}
