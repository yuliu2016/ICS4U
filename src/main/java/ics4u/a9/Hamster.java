package ics4u.a9;

public class Hamster extends Pet {

    String hairLength;
    private boolean isTired;

    public Hamster(String colour, String hairLength) {
        super(colour, 4);
        this.hairLength = hairLength;
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
