package ics4u.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

        setSize(768, 768);

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

        Container container = getContentPane();

        container.setLayout(layout);
        container.add(view, BorderLayout.CENTER);

        // region Menu Bar
        MenuBar mb = new MenuBar();

        Menu file = new Menu("File");
        MenuItem save = new MenuItem("Save");
//        save.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                saveGame();
//            }
//        });
        file.add(save);
        MenuItem load = new MenuItem("Load");
//        load.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                loadGame();
//            }
//        });
        file.add(load);
        mb.add(file);

        Menu game_ = new Menu("Game");
        MenuItem restart = new MenuItem("Restart");
//        restart.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                game.getBoard().reset();
//                game.repaint();
//            }
//        });
        game_.add(restart);
        mb.add(game_);

        setMenuBar(mb);
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