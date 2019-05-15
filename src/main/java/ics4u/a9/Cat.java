package ics4u.a9;

public class Cat extends Mammal {

	boolean isPlayful;
	
	public Cat(String colour, boolean isPlayful, String hairLength) {
		super(colour, hairLength);
		this.isPlayful = isPlayful;
	}

	/**
	 * Returns the sound a cat makes when it purrs
	 */
	public String purr() {
		return "purrrrrrrr";
	}
	
	/**
	 * Returns the sound a cat makes when it speaks
	 */
	public String meow()  {
		return "meow!";
	}

	/**
	 * If this Cat is playful, it will pounce you, otherwise it will stare at you lazily.
	 */
	public void pounce(){
		if (isPlayful) {
			System.out.println("You have been pounced.");
		} else {
			System.out.println("The Cat stares at you lazily.");
		}
	}
}
