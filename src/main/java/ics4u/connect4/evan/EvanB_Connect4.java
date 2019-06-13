package ics4u.connect4.evan;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Evan B
 * date: 2019-06-08
 * This is a program to play Connect 4
 */
public class EvanB_Connect4 extends Frame implements KeyListener {
    Connect4Game game = new Connect4Game(400, 400);

    EvanB_Connect4() {
        super("Connect 4 ");

        setSize(600, 700);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        addKeyListener(this);

        setLayout(new BorderLayout());

        add(game, "Center");

        // region Menu Bar
        MenuBar mb = new MenuBar();

        Menu file = new Menu("File");
        MenuItem save = new MenuItem("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });
        file.add(save);
        MenuItem load = new MenuItem("Load");
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });
        file.add(load);
        mb.add(file);

        Menu game_ = new Menu("Game");
        MenuItem restart = new MenuItem("Restart");
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.getBoard().reset();
                game.repaint();
            }
        });
        game_.add(restart);
        mb.add(game_);

        setMenuBar(mb);
        // endregion

        game.addPropertyChangeListener("Win", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("WIN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        });
    }

    public static void main(String[] args) {
        System.out.println("Start");
        EvanB_Connect4 frame = new EvanB_Connect4();
        frame.setVisible(true);
    }


    // region Keyboard Listeners

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        char ch = e.getKeyChar();
        System.out.println(ch);
        if (ch == 'r') {
            game.getBoard().reset();
            repaint();
        } else if (ch == 'l') {
            loadGame();
            repaint();
        } else if (ch == 's') {
            saveGame();
            repaint();
        }
    }
    // endregion

    /**
     * save the current game to a file
     *
     * @see EvanB_Connect4#loadGame()
     */
    private void saveGame() {
        FileDialog fileDialog = new FileDialog(this, "Save game", FileDialog.SAVE);
        fileDialog.setFile("*.con4");
        fileDialog.setVisible(true);
        String filename = fileDialog.getFile();
        if (filename != null) {
            try {
                String dir = fileDialog.getDirectory();

                FileWriter fw = new FileWriter(dir + filename);
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = game.getBoard().getGrid().length - 1; i >= 0; i--) {
                    for (int j = 0; j < game.getBoard().getGrid()[0].length; j++)
                        stringBuffer.append(game.getBoard().getGrid()[i][j]);
                    stringBuffer.append("\n");
                }

                fw.write(stringBuffer.toString());
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * load the previously saved game
     * output error message if there is no previously saved game
     *
     * @return whether the file was loaded successfully
     * @see EvanB_Connect4#saveGame()
     */
    boolean loadGame() {
        FileDialog fileDialog = new FileDialog(this, "Load game", FileDialog.LOAD);
        fileDialog.setFile("*.con4");
        fileDialog.setVisible(true);

        String filename = fileDialog.getFile();
        if (filename != null) {
            try {
                String dir = fileDialog.getDirectory();
                FileReader fr = new FileReader(dir + filename);
                BufferedReader br = new BufferedReader(fr);

                String line;
                for (int i = 0; (line = br.readLine()) != null; i++) {
                    for (int j = 0; j < line.length(); j++) {
                        game.getBoard().getGrid()[game.getBoard().getHeight() - i - 1][j] = Character.getNumericValue(line.charAt(j));
                    }
                }
                br.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void paint(Graphics g) {
        int m = Math.min(Math.min(getWidth(), getHeight()), 500);
        game.setDimensions(m, m);
        super.paint(g);
    }
}