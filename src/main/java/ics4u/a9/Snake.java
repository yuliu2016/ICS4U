package ics4u.a9;

public class Snake extends Pet {

	public Snake(String colour) {
		super(colour, 0);
	}
	
	/**
	 * Returns the sound a snake makes
	 */
	public String hiss() {
		return "hissssssss";
	}
}
