package ics4u.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {
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

        BorderLayout layout = new BorderLayout();

        setLayout(layout);
        add(view, BorderLayout.CENTER);
//
//        Panel panel = new Panel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//
//        panel.add(new Button("hi"));
//        panel.add(new Button("ho"));
//
//        add(panel, BorderLayout.EAST);


//
//        Button button = new Button("Save");
//        add(view, "East");
//
//        add(view, "Center");




        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void update(Graphics g) {
        Image bufferedImage = createImage(getWidth(), getHeight());
        Graphics bg = bufferedImage.getGraphics();
        paint(bg);
        g.drawImage(bufferedImage, 0, 0, this);
    }
}