package ics4u.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.*;

/**
 * The Connect 4 (N) Game, with a graphical user interface
 *
 * @author Yu Liu
 */
public class ConnectFour extends JComponent {

    // ==========CONSTANT FIELDS==========

    // Game Constants
    public static final int kDefaultRows = 6;
    public static final int kDefaultColumns = 7;
    public static final int kDefaultN = 4;
    public static final String kDefaultFirst = "RED";
    public static final String kDefaultSecond = "YELLOW";
    public static final Font kDefaultFont = new Font("Arial", Font.PLAIN, 11);

    // Colour constants
    private static final Color kDarkCyan = new Color(0, 125, 125);
    private static final Color kLightGray = new Color(224, 224, 224);
    private static final Color kDarkYellow = new Color(224, 224, 0);
    private static final Color kDarkRed = new Color(224, 0, 0);

    // Player constants
    private static final int kEmpty = 0;
    private static final int kFirst = 1;
    private static final int kSecond = 2;

    // Game state constants
    private static final int kPlaying = 0;
    private static final int kWin = 1;
    private static final int kDraw = 2;

    // UI Constants
    private static final int kDefaultWidth = 768;
    private static final int kDefaultHeight = 768;

    // ==========INSTANCE FIELDS==========

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

    // The coordinates of the last connection (used to display line)
    private int connectedMinRow = 0;
    private int connectedMinCol = 0;
    private int connectedMaxRow = 0;
    private int connectedMaxCol = 0;

    // The font used to display text
    private Font mainFont;

    // The window to display this view on
    private JFrame frame;

    // ==========STATE FIELDS==========

    // The 2D game board array
    private int[][] board;

    // The row size of the board
    private int rows;

    // The row size of the board
    private int columns;

    // The number of pieces that needs to be connected to win
    private int n;

    // The current player number
    private int player;

    // The game state number
    private int state;

    // Data for the first player
    private String firstPlayer;
    private int firstScore;

    // Data for the second player
    private String secondPlayer;
    private int secondScore;

    // Numbers of draws
    private int draws;

    // ==========CONSTRUCTOR==========

    public ConnectFour() {

        // Initialize game
        mainFont = kDefaultFont;
        firstPlayer = kDefaultFirst;
        secondPlayer = kDefaultSecond;
        resetAll(kDefaultRows, kDefaultColumns, kDefaultN);

        // Initialize mouse handlers
        setEventHandlers();

        // Create window
        frame = new JFrame();
        frame.setSize(kDefaultWidth, kDefaultHeight);
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

    // ==========STATIC METHODS==========

    // Main Method; Just initialize the GameView
    public static void main(String[] args) {
        new ConnectFour();
    }

    // Read an integer from a BufferedReader
    private static int readInt(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }

    // ==========GAME METHODS==========

    // Get the name of the current player
    public String getPlayerName() {
        if (player == kFirst) {
            return firstPlayer;
        } else if (player == kSecond) {
            return secondPlayer;
        }
        throw new IllegalStateException();
    }

    // Change to the winning state
    private void setWin() {
        if (player == kFirst) {
            firstScore++;
        } else if (player == kSecond) {
            secondScore++;
        }
        state = kWin;
    }

    // Change to the playing state
    private void setPlaying() {
        state = kPlaying;
    }

    // Change to the draw state
    private void setDraw() {
        draws++;
        state = kDraw;
    }

    // Reset the score and the board with size params
    public void resetAll(int rows, int columns, int n) {
        this.rows = rows;
        this.columns = columns;
        this.n = n;
        firstScore = 0;
        secondScore = 0;
        draws = 0;
        board = new int[rows][];
        restartBoard();
    }

    // Restart the game without resetting everything
    public void restartBoard() {
        for (int i = 0; i < rows; i++) {
            board[i] = new int[columns];
            for (int j = 0; j < columns; j++) {
                board[i][j] = 0;
            }
        }
        player = kFirst;
        setPlaying();
    }

    // Check if a board move is valid (i.e. the top row of the column is empty)
    // Column index is constrained elsewhere
    boolean isValidMove(int column) {
        return board[rows - 1][column] == kEmpty;
    }

    // Check if the board is filled; if it is, signal a draw
    // It is filled when there are no valid moves available for every column
    private void checkBoardFilled() {
        for (int i = 0; i < columns; i++) {
            if (isValidMove(i)) {
                return;
            }
        }
        setDraw();
    }

    // Check for the number of pieces connected horizontally to the specified position
    // Returns true if the count is greater than n
    private boolean checkHorizontal(int row, int column, int[][] board, int player) {
        int count = 1;

        // Check in the negative direction
        int cMin = column;
        while (cMin > 0 && cMin < columns && board[row][cMin - 1] == player) {
            cMin--;
            count++;
        }

        // Check in the positive direction
        int cMax = column;
        while (cMax >= 0 && cMax < columns - 1 && board[row][cMax + 1] == player) {
            cMax++;
            count++;
        }

        if (count >= n) {
            // Cache the start and endpoint for indicator
            connectedMinRow = row;
            connectedMaxRow = row;
            connectedMinCol = cMin;
            connectedMaxCol = cMax;
            return true;
        }

        return false;
    }

    // Check for the number of pieces connected vertically to the specified position
    // Returns true if the count is greater than n
    private boolean checkVertical(int row, int column, int[][] board, int player) {
        int count = 1;

        // Check in the negative direction
        int rMin = row;
        while (rMin > 0 && rMin < rows && board[rMin - 1][column] == player) {
            rMin--;
            count++;
        }

        // Check in the positive direction
        int rMax = row;
        while (rMax >= 0 && rMax < rows - 1 && board[rMax + 1][column] == player) {
            rMax++;
            count++;
        }

        if (count >= n) {
            // Cache the start and endpoint for indicator
            connectedMinRow = rMin;
            connectedMaxRow = rMax;
            connectedMinCol = column;
            connectedMaxCol = column;
            return true;
        }

        return false;
    }

    // Check for the number of pieces connected diagonally to the specified position
    // Returns true if the count is greater than n
    private boolean checkDiagonal(int row, int column, int[][] board, int player) {
        int count = 1;

        // Check in the negative direction
        int rMin = row;
        int cMin = column;
        while (rMin > 0 && rMin < rows && cMin > 0 && cMin < columns && board[rMin - 1][cMin - 1] == player) {
            rMin--;
            cMin--;
            count++;
        }

        // Check in the positive direction
        int rMax = row;
        int cMax = column;
        while (rMax >= 0 && rMax < rows - 1 && cMax >= 0 && cMax < columns - 1 && board[rMax + 1][cMax + 1] == player) {
            rMax++;
            cMax++;
            count++;
        }

        if (count >= n) {
            // Cache the start and endpoint for indicator
            connectedMinRow = rMin;
            connectedMaxRow = rMax;
            connectedMinCol = cMin;
            connectedMaxCol = cMax;
            return true;
        }

        return false;
    }

    // Check for the number of pieces connected inverse diagonally to the specified position
    // Returns true if the count is greater than n
    private boolean checkInverseDiagonal(int row, int column, int[][] board, int player) {
        int count = 1;

        // Check in the negative direction
        int rMax = row;
        int cMin = column;
        while (rMax >= 0 && rMax < rows - 1 && cMin > 0 && cMin < columns && board[rMax + 1][cMin - 1] == player) {
            rMax++;
            cMin--;
            count++;
        }

        // Check in the positive direction
        int rMin = row;
        int cMax = column;
        while (rMin > 0 && rMin < rows && cMax >= 0 && cMax < columns - 1 && board[rMin - 1][cMax + 1] == player) {
            rMin--;
            cMax++;
            count++;
        }

        if (count >= n) {
            // Cache the start and endpoint for indicator
            connectedMinRow = rMax;
            connectedMaxRow = rMin;
            connectedMinCol = cMin;
            connectedMaxCol = cMax;
            return true;
        }

        return false;
    }

    // Make a move at the specified column
    // This method assumes that the column is already a valid move
    // The player piece is moved to the bottom of the column, and is
    // then checked in all four directions for connections. If any
    // is true, change the state to win; Otherwise check for draw
    // If all checks fail, advance to the next player
    void move(int column) {
        int row = rows - 1;
        while (row > 0 && board[row - 1][column] == kEmpty) {
            row--;
        }
        board[row][column] = player;

        // This is a short-circuit evaluation to keep efficiency
        if (checkHorizontal(row, column, board, player) ||
                checkVertical(row, column, board, player) ||
                checkDiagonal(row, column, board, player) ||
                checkInverseDiagonal(row, column, board, player)) {
            setWin();
        } else {
            checkBoardFilled();
        }

        // Advance to the next player
        if (state == kPlaying) {
            switch (player) {
                case kFirst:
                    player = kSecond;
                    break;
                case kSecond:
                    player = kFirst;
            }
        }
    }

    // Convert data to a printable string
    public String toString() {
        StringBuffer buffer = new StringBuffer("Current Board: \n");

        // Append the headers
        for (int i = 1; i <= columns; i++) {
            buffer.append(i);
            buffer.append(i > 9 ? " " : "  ");
        }
        buffer.append('\n');

        // Append board data
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                int it = board[i][j];
                // Append piece character
                if (it == kEmpty) buffer.append('.');
                else if (it == kFirst) buffer.append('r');
                else buffer.append('b');

                buffer.append("  ");
            }
            buffer.append("\n");
        }

        // Append game state
        String player = getPlayerName();
        buffer.append("Game State: ");

        if (state == kPlaying) buffer.append(player).append("'s turn");
        else if (state == kWin) buffer.append(player).append(" won the game");
        else buffer.append("The game is a draw");

        // Append score
        buffer.append('\n')
                .append("Score: ")
                .append(firstPlayer)
                .append('-')
                .append(firstScore)
                .append('|')
                .append(secondPlayer)
                .append('-')
                .append(secondScore)
                .append("|Draw-")
                .append(draws)
                .append('\n');

        return buffer.toString();
    }

    // Serialize this object into a string to be processed later
    private String serialize() {
        StringBuffer buffer = new StringBuffer();

        // Add metadata
        buffer
                .append(firstPlayer).append('\n')
                .append(firstScore).append('\n')
                .append(secondPlayer).append('\n')
                .append(secondScore).append('\n')
                .append(draws).append('\n')
                .append(rows).append('\n')
                .append(columns).append('\n')
                .append(n).append('\n')
                .append(player).append('\n')
                .append(state).append('\n');

        // Add board data
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                buffer.append(board[i][j]);
                buffer.append(',');
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    // Save the object into a file by writing the serialized text
    private void save(File file) throws IOException {
        String s = serialize();
        // Create a writer
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        writer.print(s);
        writer.close();
    }

    // Load a file by trying to deserialize it
    // Reset to defaults if there are any errors
    private void load(File file) {
        try {
            deserializeFile(file);
        } catch (IOException e) {
            this.firstPlayer = "RED";
            this.secondPlayer = "BLUE";
            resetAll(kDefaultRows, kDefaultColumns, kDefaultN);
        }
    }

    // Deserialize the file and read into this object
    private void deserializeFile(File file) throws IOException {
        // Create a reader
        BufferedReader br = new BufferedReader(new FileReader(file));
        // Read all the metadata
        firstPlayer = br.readLine();
        firstScore = readInt(br);
        secondPlayer = br.readLine();
        secondScore = readInt(br);
        draws = readInt(br);
        rows = readInt(br);
        columns = readInt(br);
        board = new int[rows][];
        n = readInt(br);
        player = readInt(br);
        state = readInt(br);

        // Read the board using the dimensions given above
        int row = 0;
        String line;
        while ((line = br.readLine()) != null && row < rows) {
            // Split the input
            String[] data = line.split(",");

            // Check data size
            if (data.length != columns) throw new IllegalArgumentException("Data has wrong size");

            // Create new row array
            board[row] = new int[columns];
            for (int column = 0; column < columns; column++) {
                // Parse each column of the row
                board[row][column] = Integer.parseInt(data[column]);
            }
            row++;
        }

        // Check data size again
        if (row != rows) throw new IllegalArgumentException("Data has wrong size");
        br.close();
    }

    // ==========USER INTERFACE METHODS==========

    // Add mouse handlers to the Component
    private void setEventHandlers() {
        MouseMotionListener mouseMotionListener = new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
            }

            public void mouseMoved(MouseEvent e) {
                // Find the column where the mouse is; -1 by default
                int newCol = -1;

                // Check if the current state allows for hovering
                if (scale != 0.0 && state == kPlaying) {
                    int mouseX = e.getX();

                    // Calculate the min and max of the mouse range using columns
                    double min = x + 2 * scale;
                    double max = min + columns * 12 * scale;

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
                if (hoverColumn == -1 || state != kPlaying) {
                    return;
                }

                // Check that the move is valid before making the move
                if (isValidMove(hoverColumn)) {
                    move(hoverColumn);
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
                String s = JOptionPane.showInputDialog("Set Red Player", firstPlayer);
                // Set the first player if it's valid
                if (s.length() > 0 && !s.equals(firstPlayer)) {
                    firstPlayer = s;
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
                String s = JOptionPane.showInputDialog("Set Yellow Player", secondPlayer);
                // Set the second player if it's valid
                if (s.length() > 0 && !s.equals(secondPlayer)) {
                    secondPlayer = s;
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
                        save(new File(dir, filename));
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
                    load(new File(dir, filename));
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
                restartBoard();
                repaint();
            }
        });
        menu.add(restart_);

        JMenuItem addRow_ = new JMenuItem("Add Row");
        addRow_.setAccelerator(KeyStroke.getKeyStroke('T', shortcut));

        addRow_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add 1 to rows and restart
                resetAll(rows + 1, columns, n);
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
                resetAll(rows, columns + 1, n);
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
                resetAll(rows, columns, n + 1);
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
                resetAll(kDefaultRows, kDefaultColumns, kDefaultN);
                invalidateState();
                repaint();
            }
        });
        menu.add(resetAll_);

        JMenuItem print_ = new JMenuItem("Print");
        print_.setAccelerator(KeyStroke.getKeyStroke('P', shortcut));

        print_.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Print this object
                System.out.println(ConnectFour.this);
            }
        });
        menu.add(print_);

        menuBar.add(menu);
        return menuBar;
    }

    // Invalidates computed state of the view
    public void invalidateState() {
        // Reset the width and height so it will be picked up on paint
        lastWidth = 0;
        lastHeight = 0;
        hoverColumn = -1;

        // Reset the title of the window
        frame.setTitle("Connect " + n + " | " +
                rows + " by " + columns + " | " +
                firstPlayer + " vs. " + secondPlayer);
    }

    // Paints the game view onto the screen
    public void paint(Graphics g) {

        // Get a casted Graphics2D
        Graphics2D g2 = (Graphics2D) g;

        // Set rendering parameters
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Get view dimensions
        int viewWidth = getWidth();
        int viewHeight = getHeight();

        // Clear the background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, viewWidth, viewHeight);

        // Check if the window has resized; if so, recompute coordinates
        if (viewWidth != lastWidth || viewHeight != lastHeight) {

            // Find the min and max of viewport dimensions
            double minWidth = columns * 12 + 4.0;
            double maxWidth = 8 * minWidth;

            double minHeight = (rows + 1) * 12 + 4.0;
            double maxHeight = 8 * minHeight;

            // return if window is too small to draw properly
            if (viewWidth < minWidth || viewHeight < minHeight) {
                scale = 0.0;
                return;
            }

            // Compute the based on the view dimensions
            scale = Math.min(viewHeight / minHeight, viewWidth / minWidth);

            // Limit scale if view dimensions exceed the max dimensions
            if (viewWidth > maxWidth) {
                scale = Math.min(scale, 8);
            }
            if (viewHeight > maxHeight) {
                scale = Math.min(scale, 8);
            }

            // Compute viewport dimensions using scale
            width = scale * minWidth;
            height = scale * minHeight;

            // Find the offset translation so that the viewport is centered
            x = (viewWidth - width) / 2.0;
            y = (viewHeight - height) / 2.0;

            // Derive the font size according to the scale
            mainFont = mainFont.deriveFont((float) (int) (scale * 5));

            lastWidth = viewWidth;
            lastHeight = viewHeight;
        }

        // Draw the gray background
        RoundRectangle2D bg1 = new RoundRectangle2D.Double();
        bg1.setRoundRect(x, y, width, height, scale * 4, scale * 4);

        g.setColor(kLightGray);
        g2.fill(bg1);

        // Create the message
        String message;
        if (state == kPlaying) message = getPlayerName() + "'s turn";
        else if (state == kWin) message = getPlayerName() + " won the game!!!";
        else message = "The game is a tie";

        // Use measured bounds to center the message
        Rectangle2D bounds = mainFont.getStringBounds(message, g2.getFontRenderContext());

        g.setColor(Color.BLACK);
        g.setFont(mainFont);

        g.drawString(message, (int) (x + width / 2 - bounds.getWidth() / 2.0),
                (int) (y + scale * 6 + bounds.getHeight() / 2.0));

        // Draw the dark cyan background for the board, offsetting all sides by 1 * scale
        RoundRectangle2D bg2 = new RoundRectangle2D.Double();

        bg2.setRoundRect(x + scale, y + scale * (12 + 1),
                scale * (columns * 12 + 2), scale * (rows * 12 + 2),
                scale * 4, scale * 4);

        g.setColor(kDarkCyan);
        g2.fill(bg2);

        // Draw all the checker pieces
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                Ellipse2D ellipse = new Ellipse2D.Double();

                // Compute the absolute position of the piece
                ellipse.setFrame(x + scale * (12 * i + 3), y + scale * (12 * j + 12 + 3),
                        scale * 10, scale * 10);

                // Get the piece using inverted index to switch direction
                int piece = board[rows - 1 - j][i];

                // Resolve the color of the piece
                g.setColor(piece == kEmpty ? Color.WHITE : (piece == kFirst ? kDarkRed : kDarkYellow));
                g2.fill(ellipse);
            }
        }

        // Draw an indicater if a column is being hovered
        if (hoverColumn != -1) {
            int row = rows - 1;
            if (board[row][hoverColumn] == 0) {
                while (row > 0 && board[row - 1][hoverColumn] == 0) {
                    row--;
                }

                // Resolve the color of the piece
                g.setColor(player == kFirst ? kDarkRed :
                        (player == kSecond ? kDarkYellow : Color.BLUE));

                Ellipse2D hovierIndicator = new Ellipse2D.Double();

                // Compute the absolute position of the indicater
                hovierIndicator.setFrame(x + scale * (12 * hoverColumn + 3 + 3.5),
                        y + scale * (12 * (rows - 1 - row) + 12 + 3 + 3.5), scale * 3, scale * 3);

                g2.fill(hovierIndicator);
            }
        }

        // Draw a line showing the connected pieces if winning
        if (state == kWin) {
            g2.setStroke(new BasicStroke((float) scale / 2));
            g.setColor(Color.PINK);
            g2.draw(new Line2D.Double(x + scale * (12 * connectedMinCol + 3 + 5),
                    y + scale * (12 * (rows - 1 - connectedMinRow) + 12 + 3 + 5),
                    x + scale * (12 * connectedMaxCol + 3 + 5),
                    y + scale * (12 * (rows - 1 - connectedMaxRow) + 12 + 3 + 5)));
        }
    }
}
