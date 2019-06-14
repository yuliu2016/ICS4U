package ics4u.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

/**
 * The GameView provides a graphical user interface for the Conncect4 class
 *
 * @author Yu Liu
 */
public class GameView extends JComponent {

    // Setup colours

    private static Color darkCyan = new Color(0, 125, 125);
    private static Color lightGray = new Color(224, 224, 224);
    private static Color darkYellow = new Color(224, 224, 0);
    private static Color darkRed = new Color(224, 0, 0);

    // Keep track of the last window size to determine if it has changed

    private int lastWidth = 0;
    private int lastHeight = 0;

    // Values computed on resize that determines the position and size of everything

    private double scale = 0.0;
    private double x = 0.0;
    private double y = 0.0;
    private double width = 0.0;
    private double height = 0.0;

    // The column that the mouse is hovering on, or -1 if not selected
    private int hoverColumn = -1;

    // The font used to display text
    private Font mainFont;

    // The Connect4 instance for game data
    private Connect4 connect4;

    // The window to display this view on
    private JFrame frame;

    public GameView() {
        // Initialize font
        mainFont = new Font("Arial", Font.PLAIN, 11);
        // Initialize game
        connect4 = new Connect4("RED", "YELLOW");

        // Initialize mouse handlers
        setEventHandlers();

        // Create window
        frame = new JFrame();
        frame.setSize(768, 768);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // Create window layout
        BorderLayout layout = new BorderLayout();
        Container container = frame.getContentPane();
        container.setLayout(layout);
        container.add(this, BorderLayout.CENTER);

        // Create menu bar
        frame.setJMenuBar(getMenuBar());

        // Center window on screen
        frame.setLocationRelativeTo(null);

        // Set the title of the window
        invalidateState();

        // Show the window
        frame.setVisible(true);

    }

    // Main Method; Just initialize the GameView
    public static void main(String[] args) {
        new GameView();
    }

    // Add mouse handlers to the Component
    private void setEventHandlers() {
        MouseMotionListener mouseMotionListener = new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
            }

            public void mouseMoved(MouseEvent e) {
                // Find the column where the mouse is; -1 by default
                int newCol = -1;
                // Check if the current state allows for hovering
                if (scale != 0.0 && connect4.getState() == Connect4.kPlaying) {
                    int mouseX = e.getX();
                    int cols = connect4.getColumns();
                    // Calculate the min and max of the mouse range using columns
                    double min = x + 2 * scale;
                    double max = min + cols * 12 * scale;
                    // Check if mouse in range
                    if (mouseX >= min && mouseX <= max) {
                        // Interpolate to get the column number
                        newCol = (int) ((mouseX - min) / (12 * scale));
                    }
                }
                // Repaint only if the new column is not the current hoverColumn
                if (newCol != hoverColumn) {
                    hoverColumn = newCol;
                    repaint();
                }
            }
        };
        addMouseMotionListener(mouseMotionListener);

        MouseListener mouseListener = new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                // Check if the current state allows for clicking
                if (hoverColumn == -1 || connect4.getState() != Connect4.kPlaying) {
                    return;
                }
                // Check that the move is valid before making the move
                if (connect4.isValidMove(hoverColumn)) {
                    connect4.move(hoverColumn);
                    repaint();
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
        };
        addMouseListener(mouseListener);
    }

    // Creates the menu bar, with menu items and shortcuts
    private JMenuBar getMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        // Get the shortcut key from the toolkit
        int shortcut = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenu menu = new JMenu("Menu");

        JMenuItem setPlayer1_ = new JMenuItem("Set Red Player");
        setPlayer1_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ask for the name of the first player
                String s = JOptionPane.showInputDialog("Set Red Player", connect4.getFirstPlayer());
                // Set the first player if it's valid
                if (s.length() > 0 && !s.equals(connect4.getFirstPlayer())) {
                    connect4.setFirstPlayer(s);
                    invalidateState();
                    repaint();
                }
            }
        });
        menu.add(setPlayer1_);

        JMenuItem setPlayer2_ = new JMenuItem("Set Yellow Player");
        setPlayer2_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ask for the name of the second player
                String s = JOptionPane.showInputDialog("Set Yellow Player", connect4.getSecondPlayer());
                // Set the second player if it's valid
                if (s.length() > 0 && !s.equals(connect4.getSecondPlayer())) {
                    connect4.setSecondPlayer(s);
                    invalidateState();
                    repaint();
                }
            }
        });
        menu.add(setPlayer2_);

        JMenuItem save_ = new JMenuItem("Save");
        save_.setAccelerator(KeyStroke.getKeyStroke('S', shortcut));
        save_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a file chooser for saving
                FileDialog fileDialog = new FileDialog(frame, "Save", FileDialog.SAVE);
                fileDialog.setFile("*.txt");
                fileDialog.setVisible(true);
                String filename = fileDialog.getFile();
                String dir = fileDialog.getDirectory();
                // Check that file is seleted
                if (filename != null && dir != null) {
                    // Try saving to the file
                    try {
                        connect4.save(new File(dir, filename));
                    } catch (IOException ignored) {
                    }
                }
            }
        });
        menu.add(save_);

        JMenuItem load_ = new JMenuItem("Load");
        load_.setAccelerator(KeyStroke.getKeyStroke('O', shortcut));
        load_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a file chooser for loading
                FileDialog fileDialog = new FileDialog(frame, "Save", FileDialog.LOAD);
                fileDialog.setFile("*.txt");
                fileDialog.setVisible(true);
                String filename = fileDialog.getFile();
                String dir = fileDialog.getDirectory();
                // Check that file is seleted
                if (filename != null && dir != null) {
                    // Try loading it into the file
                    connect4.load(new File(dir, filename));
                    invalidateState();
                    repaint();
                }
            }
        });
        menu.add(load_);

        JMenuItem restart_ = new JMenuItem("Restart");
        restart_.setAccelerator(KeyStroke.getKeyStroke('R', shortcut));
        restart_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Restarts the board
                connect4.restartBoard();
                repaint();
            }
        });
        menu.add(restart_);

        JMenuItem addRow_ = new JMenuItem("Add Row");
        addRow_.setAccelerator(KeyStroke.getKeyStroke('T', shortcut));
        addRow_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add 1 to rows and restart
                connect4.init(connect4.getRows() + 1, connect4.getColumns(), connect4.getN());
                invalidateState();
                repaint();
            }
        });
        menu.add(addRow_);

        JMenuItem addCol_ = new JMenuItem("Add Column");
        addCol_.setAccelerator(KeyStroke.getKeyStroke('Y', shortcut));
        addCol_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add 1 to columns and restart
                connect4.init(connect4.getRows(), connect4.getColumns() + 1, connect4.getN());
                invalidateState();
                repaint();
            }
        });
        menu.add(addCol_);

        JMenuItem addN_ = new JMenuItem("Add N");
        addN_.setAccelerator(KeyStroke.getKeyStroke('N', shortcut));
        addN_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add 1 to N and restart
                connect4.init(connect4.getRows(), connect4.getColumns(), connect4.getN() + 1);
                invalidateState();
                repaint();
            }
        });
        menu.add(addN_);


        JMenuItem resetAll_ = new JMenuItem("Reset All");
        resetAll_.setAccelerator(KeyStroke.getKeyStroke('0', shortcut));
        resetAll_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset to original and restart
                connect4.init(6, 7, 4);
                invalidateState();
                repaint();
            }
        });
        menu.add(resetAll_);

        menuBar.add(menu);
        return menuBar;
    }

    public void invalidateState() {
        lastWidth = 0;
        lastHeight = 0;
        hoverColumn = -1;
        frame.setTitle("Connect " + connect4.getN() + " | " +
                connect4.getRows() + " by " + connect4.getColumns() + " | " +
                connect4.getFirstPlayer() + " vs. " + connect4.getSecondPlayer());
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Set rendering parameters
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int viewWidth = getWidth();
        int viewHeight = getHeight();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, viewWidth, viewHeight);

        int cols = connect4.getColumns();
        int rows = connect4.getRows();

        // Check if the window has resized; if so, recompute coordinates
        if (viewWidth != lastWidth || viewHeight != lastHeight) {
            double minWidth = cols * 12 + 4.0;
            double maxWidth = 8 * minWidth;

            double minHeight = (rows + 1) * 12 + 4.0;
            double maxHeight = 8 * minHeight;

            // return if window is too small
            if (viewWidth < minWidth || viewHeight < minHeight) {
                scale = 0.0;
                return;
            }

            scale = Math.min(viewHeight / minHeight, viewWidth / minWidth);
            if (viewWidth > maxWidth) scale = Math.min(scale, 8);
            if (viewHeight > maxHeight) scale = Math.min(scale, 8);

            width = scale * minWidth;
            height = scale * minHeight;

            x = (viewWidth - width) / 2.0;
            y = (viewHeight - height) / 2.0;

            mainFont = mainFont.deriveFont((float) (int) (scale * 5));

            lastWidth = viewWidth;
            lastHeight = viewHeight;
        }


        RoundRectangle2D bg1 = new RoundRectangle2D.Double();
        bg1.setRoundRect(x, y, width, height,
                scale * 4, scale * 4);

        g.setColor(lightGray);
        g2.fill(bg1);

        g.setColor(Color.BLACK);
        g.setFont(mainFont);

        String message;
        int state = connect4.getState();
        if (state == Connect4.kPlaying) message = connect4.getPlayerName() + "'s turn";
        else if (state == Connect4.kWin) message = connect4.getPlayerName() + " won the game!!!";
        else message = "The game is a tie";

        Rectangle2D bounds = mainFont.getStringBounds(message, g2.getFontRenderContext());
        g.drawString(message, (int) (x + width / 2 - bounds.getWidth() / 2.0),
                (int) (y + scale * 6 + bounds.getHeight() / 2.0));

        RoundRectangle2D bg2 = new RoundRectangle2D.Double();
        bg2.setRoundRect(x + scale, y + scale * 13, scale * (cols * 12 + 2), scale * (rows * 12 + 2),
                scale * 4, scale * 4);

        g.setColor(darkCyan);
        g2.fill(bg2);

        int[][] board = connect4.getBoard();

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Ellipse2D ellipse = new Ellipse2D.Double();
                ellipse.setFrame(x + scale * (12 * i + 3), y + scale * (12 * j + 12 + 3),
                        scale * 10, scale * 10);
                int piece = board[rows - 1 - j][i];
                g.setColor(piece == Connect4.kEmpty ? Color.WHITE : (piece == Connect4.kFirst ? darkRed : darkYellow));
                g2.fill(ellipse);
            }
        }

        if (hoverColumn != -1) {
            int row = rows - 1;
            if (board[row][hoverColumn] == 0) {
                while (row > 0 && board[row - 1][hoverColumn] == 0) row--;
                int player = connect4.getPlayer();
                g.setColor(player == Connect4.kFirst ? darkRed :
                        (player == Connect4.kSecond ? darkYellow : Color.BLUE));
                Ellipse2D hovierIndicator = new Ellipse2D.Double();
                hovierIndicator.setFrame(x + scale * (12 * hoverColumn + 3 + 3.5),
                        y + scale * (12 * (rows - 1 - row) + 12 + 3 + 3.5), scale * 3, scale * 3);
                g2.fill(hovierIndicator);
            }
        }

    }
}
