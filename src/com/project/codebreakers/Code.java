package com.zlawrence.codebreaker;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class for representing a randomly
 * generated code. Uses shorts/bytes
 * for many things since we're dealing
 * with small numbers.
 * 
 * @author Zachary Lawrence
 * @since 4/22/2013
 */
public class Code {
	
	private short length; //The length of the code.
	private byte startingNum; //The starting number of the game (NOT PART OF THE CODE)
	private byte[] code; //The code array
	
	/**
	 * HashMap for storing number adjacency. Each key is a number between
	 * 1 and 6 representing the "faces" of our imaginary game object. Each
	 * face is adjacent to 4 other "faces" (other numbers), which are
	 * represented by the Byte arrays. The position in the array corresponds
	 * with the direction of the face with respect to the key by LRTB. For example,
	 * imagine that 3 is "face up" -- then 2 is on the left, 1 is on the right, 5
	 * is on top, and 6 is on bottom.
	 */
	private static final Map<Byte, Byte[]> ADJACENCY  = new HashMap<Byte, Byte[]>();
	static {
		ADJACENCY.put((byte) 1, new Byte[] {3,4,5,6});
		ADJACENCY.put((byte) 2, new Byte[] {4,3,5,6});
		ADJACENCY.put((byte) 3, new Byte[] {2,1,5,6});
		ADJACENCY.put((byte) 4, new Byte[] {1,2,5,6});
		ADJACENCY.put((byte) 5, new Byte[] {3,4,2,1});
		ADJACENCY.put((byte) 6, new Byte[] {4,3,2,1});
	}
	
	/**
	 * Default constructor for Code object.
	 * Creates a 5-number code.
	 */
	public Code() {
		this((short) 5);
	}
	
	/**
	 * Constructor for Code object.
	 * @param length The length of the code to create.
	 */
	public Code(short length) {
		setLength(length);
	}
	
	/**
	 * Setter for length. Any time the length
	 * changes, the code and starting number changes
	 * with it.
	 * @param length The length of the code to create.
	 */
	public void setLength(short length) {
		this.length = length;
		this.code = generateCode();
	}
	
	/**
	 * Getter for code length. 
	 * @return Returns the length of the code.
	 */
	public short getLength() {
		return this.length;
	}
	
	/**
	 * Getter for starting number. The starting number
	 * would be the face which the user sees as face up
	 * at the beginning of the game. The starting number
	 * is NOT part of the code.
	 * @return Returns the starting number.
	 */
	public byte getStartingNum() {
		return this.startingNum;
	}
	
	/**
	 * Getter for code.
	 * @return Returns the code array.
	 */
	public byte[] getCode() {
		return this.code;
	}
	
	/**
	 * Code generator. Creates a random sequence
	 * of numbers based on the face adjacency hash-map.
	 * @return Returns the code generated in a byte array.
	 */
	private byte[] generateCode() {
		Random randGen = new Random(); //Seed PRNG
		byte[] code = new byte[length]; 
		byte index = (byte) (Math.abs((randGen.nextInt() % 6)) + 1); //Positive number between 1 & 6
		this.startingNum = index;
		
		for(short i = 0; i < length; i++) {
			short randNum = (short) (Math.abs(randGen.nextInt()) % 4); //Positive number between 0 & 3
			code[i] = ADJACENCY.get(index)[randNum]; //Get a possible number from current face
			index = code[i]; //Switch to new face
		}
		return code;
	}
	
	/**
	 * Prints out the code array. For debugging purposes.
	 */
	public void printCode() {
		for(byte b : this.code) {
			System.out.print(b);
		}
		System.out.print("\n");
	}
	
	/**
	 * Creates an ArrayDeque based on the code array.
	 * Should be useful for the actual gameplay.
	 * @return Returns an ArrayDeque containing the code.
	 */
	public ArrayDeque<Byte> getCodeDeque() {
		ArrayDeque<Byte> codeDeque = new ArrayDeque<Byte>();
		for (Byte b : this.code) {
			codeDeque.addLast(b);
		}
		return codeDeque;
	}
	
}