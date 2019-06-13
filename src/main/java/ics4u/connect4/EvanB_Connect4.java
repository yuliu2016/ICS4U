package ics4u.connect4;

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
    public static void main(String[] args) {
        System.out.println("Start");
        EvanB_Connect4 frame = new EvanB_Connect4();
        frame.setVisible(true);
    }

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

class Connect4Game extends Component implements MouseListener {
    private int w, h;
    private Board board = new Board(4, 7, 7);
    private int turn = 1;

    private int diameter = 0;

    Connect4Game(int w, int h) {
        this.w = w;
        this.h = h;
        setSize(w, h);
        addMouseListener(this);
    }

    Board getBoard() {
        return board;
    }

    void setDimensions(int w, int h) {
        this.w = w;
        this.h = h;
    }

    private int getMidBorder() {
        double midBorderScale = 0.1;
        return (int) (midBorderScale * Math.min(h / board.getHeight(), w / board.getWidth()));
    }

    public void paint(Graphics g) {

        // paint the board
        Rectangle2D background = new Rectangle2D.Double();
        background.setFrame(0, 0, w, h);


        diameter = (w / board.getWidth() < h / board.getHeight() ?
                (w - getMidBorder() * (board.getWidth() + 1)) / board.getWidth() :
                (h - getMidBorder() * (board.getHeight() + 1)) / board.getHeight());


        Graphics2D g2 = (Graphics2D) g;
        g2.setPaintMode();

        g2.setColor(Color.BLUE);
        g2.fill(background);

        // paint the tiles, and remove the board where there are no pieces
        for (int i = 0; i < board.getWidth(); i++)
            for (int j = 0; j < board.getHeight(); j++) {
                Ellipse2D circle = new Ellipse2D.Double();
                int q = board.getWidth() - i - 1;
                circle.setFrame(j * diameter + (j + 1) * getMidBorder(), q * diameter + (q + 1) * getMidBorder(), diameter, diameter);

                int piece = board.getGrid()[i][j];
                if (piece == 1) g2.setColor(Color.RED);
                else if (piece == 2) g2.setColor(Color.YELLOW);
                else g2.setColor(Color.GRAY);
                g2.fill(circle);
            }
        if (board.check() != 0 ) firePropertyChange("Win",false, true);
    }

    // region Mouse Listeners
    public void mouseClicked(MouseEvent e) {
        int check = board.check();
        if (check == 0) {
            int x = e.getX();

            if (x > 0 && x < diameter * board.getWidth() + getMidBorder() * (board.getWidth() + 1)) {
                Point2D placement = board.place(turn, x / (getMidBorder() + diameter));
                if (placement != null) {
                    if (turn == 1) turn = 2;
                    else if (turn == 2) turn = 1;
                    repaint();
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    // endregion

}

class Board {
    private int n;
    private int[][] grid;

    /**
     * @param n the number of pieces in a line that you need to win
     * @param x the length of the board across axis 1. the width of the board
     * @param y the length of the board across axis 0. the height of the board
     */
    Board(int n, int x, int y) {
        grid = new int[y][x];
        for (int i = 0; i < y; i++)
            for (int j = 0; j < x; j++)
                grid[i][j] = 0;
        this.n = n;
    }

    /**
     * @return the grid
     * -1: tie
     * 0: no piece
     * 1: player 1
     * 2: player 2
     */
    int[][] getGrid() {
        return grid;
    }

    /**
     * @return the width of the board. the length of the grid in axis 0
     */
    int getWidth() {
        return grid.length;
    }

    /**
     * @return the height of the board. the length of the grid in axis 1
     */
    int getHeight() {
        return grid[0].length;
    }

    /**
     * @param player the player who is placing the piece
     * @param x      the x coordinate where the piece is trying to be placed at
     * @return whether the placement was successful
     */
    Point2D place(int player, int x) {
        for (int y = 0; y < grid[0].length && x >= 0 && x <= grid[0].length; y++)
            if (grid[y][x] == 0) {
                grid[y][x] = player;
                return new Point2D.Double(x, y);
            }
        return null;
    }

    /**
     * @return the result of the check:
     * -1: tie
     * 0: no winner
     * 1: player 1 wins
     * 2: player 2 wins
     */
    int check() {
        StringBuffer gridStrBuff = new StringBuffer();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                gridStrBuff.append(grid[i][j]);
        String gridStr = gridStrBuff.toString();

        String[] player1Checks = new String[]{
                t("1", this.grid[0].length, this.n),
                t("1", 1, this.n),
                t("1", this.grid[0].length - 1, this.n),
                t("1", this.grid[0].length + 1, this.n)
        };

        for (int i = 0; i < player1Checks.length; i++)
            if (gridStr.matches(player1Checks[i]))
                return 1;
            else if (gridStr.matches(player1Checks[i].replace('1', '2')))
                return 2;
        if (isFull()) return -1;
        else return 0;
    }

    private boolean isFull() {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 0)
                    return false;
        return true;
    }

    /**
     * @param str     the string that is searched for
     * @param spacing the distance between each #str
     * @param count   the number of repeats
     * @return an uncompiled Pattern that searches for #str that is repeated #count times, and is spaced apart by #spacing
     */
    private String t(String str, int spacing, int count) {
        return ".*"+ str + "(.{" + (spacing - 1) + "}" + str + "){" + (count - 1) + "}.*";
    }

    /**
     * @return the board as it should be displayed to the user
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = 0; j < grid[0].length; j++)
                stringBuffer.append(grid[i][j]);
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    void reset() {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                grid[i][j] = 0;
    }
}