package ics4u.a9;

public class Pet {

	String colour;
	int numLegs;
	
	public Pet(String colour, int numLegs) {
		this.colour = colour;
		this.numLegs = numLegs;
	}
	
	/**
	 * The sound a pet makes while eating.
	 * @return The sound a pet makes while eating.
	 */
	public String eat() {
		return "OM NOM NOM";
	}
	
	/**
	 * The sound a pet makes wile sleeping.
	 * @return The sound a pet makes wile sleeping.
	 */
	public String sleep() {
		return "zzz";
	}
}
