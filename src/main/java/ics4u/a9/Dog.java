package ics4u.a9;

/**
 * Assignment 9
 *
 * @author Yu
 */
public class Dog extends Mammal {
	boolean chasesTail;
	private boolean playsFetch;
	private boolean excited;
	
	public Dog(String colour, String hairLength, boolean chasesTail, boolean playsFetch) {
		super(colour, hairLength);
		this.chasesTail = chasesTail;
		this.playsFetch = playsFetch;
		this.excited = false;
	}
	
	/**
	 * Returns what the dog says
	 * @return the string of what a dog says
	 */
	public String bark() {
		return "Woof";
	}
	
	/**
	 * If the dog plays fetch, the dog becomes excited, otherwise it looks bored. If the dog is
	 * excited and chasesTail is true, then the dog will become distracted and chase its tail.
	 */
	public void playFetch() {
		if (chasesTail && excited) {
			System.out.println("Dog has become distracted and is now chasing its tail!");
			excited = false;
		} else if (playsFetch) {
			System.out.println("Dog has become excited");
			excited = true;
		} else{
			System.out.println("Dog looks bored");
		}
	}
}
