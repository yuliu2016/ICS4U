package ics4u.a9;

public class Bird extends Pet {

	private boolean isTired;
	
	public Bird(String colour) {
		super(colour, 2);
	}

	/**
	 * If the bird is not tired, it flaps its wings and flies.
	 */
	public void fly() {
		if (isTired){
			System.out.println("Cannot fly, Bird is resting.");
		} else {
			System.out.println("*flap* *flap* *flap*");
		}
		isTired = !isTired;
	}
}
