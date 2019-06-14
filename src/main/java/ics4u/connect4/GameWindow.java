package ics4u.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @noinspection FieldCanBeLocal
 */
public class GameWindow extends JFrame {
    private Connect4 connect4;
    private GameView view;
    GameWindow() {
        super("Connect 4");
        connect4 = new Connect4("Player 1", "Player 2");
        view = new GameView(this, connect4);

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
        JMenuBar menuBar = new JMenuBar();
        int shortcut = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenu file_ = new JMenu("File");
        JMenuItem save_ = new JMenuItem("Save");
//        save.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                saveGame();
//            }
//        });
        file_.add(save_);
        JMenuItem load_ = new JMenuItem("Load");
//        load.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                loadGame();
//            }
//        });
        file_.add(load_);
        menuBar.add(file_);

        JMenu game_ = new JMenu("Game");

        JMenuItem restart_ = new JMenuItem("Restart");
        restart_.setAccelerator(KeyStroke.getKeyStroke('R', shortcut));
        restart_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect4.restartBoard();
                view.repaint();
            }
        });
        game_.add(restart_);

        JMenuItem addRow_ = new JMenuItem("Add Row");
        addRow_.setAccelerator(KeyStroke.getKeyStroke('T', shortcut));
        addRow_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect4.init(connect4.getRows() + 1, connect4.getColumns(), connect4.getN());
                view.invalidateScaling();
                view.repaint();
            }
        });
        game_.add(addRow_);

        JMenuItem addCol_ = new JMenuItem("Add Column");
        addCol_.setAccelerator(KeyStroke.getKeyStroke('Y', shortcut));
        addCol_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect4.init(connect4.getRows(), connect4.getColumns() + 1, connect4.getN());
                view.invalidateScaling();
                view.repaint();
            }
        });
        game_.add(addCol_);

        JMenuItem resetAll_ = new JMenuItem("Reset All");
        resetAll_.setAccelerator(KeyStroke.getKeyStroke('U', shortcut));
        resetAll_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect4.init(6, 7, 4);
                view.invalidateScaling();
                view.repaint();
            }
        });
        game_.add(resetAll_);

        menuBar.add(game_);
        setJMenuBar(menuBar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameWindow();
    }

    public void update(Graphics g) {
        Image bufferedImage = createImage(getWidth(), getHeight());
        Graphics bg = bufferedImage.getGraphics();
        paint(bg);
        g.drawImage(bufferedImage, 0, 0, this);
    }
}