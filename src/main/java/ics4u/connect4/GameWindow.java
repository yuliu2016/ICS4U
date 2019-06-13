package ics4u.connect4;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends Frame {
    public static void main(String[] args) {
        new GameWindow();
    }

    private Connect4 state;
    private GameView view;

    GameWindow() {
        super("Connect Four");
        state = new Connect4("Player 1", "Player 2");
        view = new GameView(state);

        setSize(512, 512);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {

            }
        });

        setLayout(new BorderLayout());

        add(view, "Center");

        setLocationRelativeTo(null);
        setVisible(true);
    }
}