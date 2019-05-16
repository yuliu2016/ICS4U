package ics4u.a9;

/**
 * Assignment 9
 *
 * @author Yu
 */
public class Hamster extends Mammal {

    private boolean isTired;

    public Hamster(String colour, String hairLength) {
        super(colour, hairLength);
    }

    /**
     * If the hamster is not too tired, it runs in the wheel. The wheel squeaks a bit.
     */
    public void runInWheel() {
        if (isTired) {
            System.out.println("Hamster is too tired to run");
        } else {
            System.out.println("*Squeak* *Squeak* *Squeak*");
        }
        isTired = !isTired;
    }
}
