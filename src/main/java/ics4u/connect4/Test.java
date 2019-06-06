package ics4u.connect4;

public class Test {
    public static void main(String[] args) {
        Connect4 connect4 = new Connect4();
        connect4.move(1);
        connect4.move(1);
        connect4.move(2);
        connect4.move(2);
        connect4.move(3);
        connect4.move(3);
        connect4.move(4);
        System.out.println(connect4);
    }
}
