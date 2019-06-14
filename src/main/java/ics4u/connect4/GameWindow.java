package ics4u.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

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

        final Container container = getContentPane();

        container.setLayout(layout);
        container.add(view, BorderLayout.CENTER);

        // region Menu Bar
        JMenuBar menuBar = new JMenuBar();
        int shortcut = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenu game_ = new JMenu("Menu");

        JMenuItem setPlayer1_ = new JMenuItem("Set Red Player");
        setPlayer1_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog("Set Red Player", connect4.getFirstPlayer());
                if (s.length() > 0 && !s.equals(connect4.getFirstPlayer())) {
                    connect4.setFirstPlayer(s);
                    view.invalidateState();
                    view.repaint();
                }
            }
        });
        game_.add(setPlayer1_);

        JMenuItem setPlayer2_ = new JMenuItem("Set Yellow Player");
        setPlayer2_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog("Set Yellow Player", connect4.getSecondPlayer());
                if (s.length() > 0 && !s.equals(connect4.getSecondPlayer())) {
                    connect4.setSecondPlayer(s);
                    view.invalidateState();
                    view.repaint();
                }
            }
        });
        game_.add(setPlayer2_);

        JMenuItem save_ = new JMenuItem("Save");
        save_.setAccelerator(KeyStroke.getKeyStroke('S', shortcut));
        save_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(GameWindow.this, "Save", FileDialog.SAVE);
                fileDialog.setFile("*.txt");
                fileDialog.setVisible(true);
                String filename = fileDialog.getFile();
                String dir = fileDialog.getDirectory();
                if (filename != null && dir != null) {
                    try {
                        connect4.save(new File(dir, filename));
                    } catch (IOException ignored) {
                    }
                    view.invalidateState();
                    view.repaint();
                }
            }
        });
        game_.add(save_);

        JMenuItem load_ = new JMenuItem("Load");
        load_.setAccelerator(KeyStroke.getKeyStroke('O', shortcut));
        load_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(GameWindow.this, "Save", FileDialog.LOAD);
                fileDialog.setFile("*.txt");
                fileDialog.setVisible(true);
                String filename = fileDialog.getFile();
                String dir = fileDialog.getDirectory();
                if (filename != null && dir != null) {
                    connect4.load(new File(dir, filename));
                    view.invalidateState();
                    view.repaint();
                }
            }
        });
        game_.add(load_);

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
                view.invalidateState();
                view.repaint();
            }
        });
        game_.add(addRow_);

        JMenuItem addCol_ = new JMenuItem("Add Column");
        addCol_.setAccelerator(KeyStroke.getKeyStroke('Y', shortcut));
        addCol_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect4.init(connect4.getRows(), connect4.getColumns() + 1, connect4.getN());
                view.invalidateState();
                view.repaint();
            }
        });
        game_.add(addCol_);

        JMenuItem addN_ = new JMenuItem("Add N");
        addN_.setAccelerator(KeyStroke.getKeyStroke('N', shortcut));
        addN_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect4.init(connect4.getRows(), connect4.getColumns(), connect4.getN() + 1);
                view.invalidateState();
                view.repaint();
            }
        });
        game_.add(addN_);


        JMenuItem resetAll_ = new JMenuItem("Reset All");
        resetAll_.setAccelerator(KeyStroke.getKeyStroke('0', shortcut));
        resetAll_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect4.init(6, 7, 4);
                view.invalidateState();
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