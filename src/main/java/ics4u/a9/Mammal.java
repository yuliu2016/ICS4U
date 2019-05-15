package ics4u.a9;

public class Mammal extends Pet {

    String hairLength;

    public Mammal(String colour, String hairLength) {
        super(colour, 4);
        this.hairLength = hairLength;
    }

    /**
     * The sound a pet makes while speaking.
     *
     * @return The sound a pet makes while speaking.
     */
    public String speak() {
        return "Hello World";
    }
}
