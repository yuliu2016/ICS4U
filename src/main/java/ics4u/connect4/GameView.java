package ics4u.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * @noinspection FieldCanBeLocal
 */
public class GameView extends JComponent {

    private static Color darkCyan = new Color(0, 125, 125);
    private static Color lightGray = new Color(224, 224, 224);
    private static Color darkYellow = new Color(224, 224, 0);
    private static Color darkRed = new Color(224, 0, 0);

    private int lastWidth = 0;
    private int lastHeight = 0;

    private double scale = 0.0;
    private double x = 0.0;
    private double y = 0.0;
    private double width = 0.0;
    private double height = 0.0;

    private int hoverColumn = -1;

    private Font mainFont;
    private Connect4 connect4;
    private JFrame jFrame;

    private MouseListener mouseListener = new MouseListener() {
        public void mouseClicked(MouseEvent e) {
            if (hoverColumn == -1 || connect4.getState() != Connect4.kPlaying) {
                return;
            }
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

    private MouseMotionListener mouseMotionListener = new MouseMotionListener() {
        public void mouseDragged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            int newCol = -1;
            if (scale != 0.0 && connect4.getState() == Connect4.kPlaying) {
                int mouseX = e.getX();
                int cols = connect4.getColumns();
                double min = x + 2 * scale;
                double max = min + cols * 12 * scale;
                if (mouseX >= min && mouseX <= max) {
                    newCol = (int) ((mouseX - min) / (12 * scale));
                }
            }
            if (newCol != hoverColumn) {
                hoverColumn = newCol;
                repaint();
            }
        }
    };

    public GameView(JFrame jFrame, Connect4 connect4) {
        this.jFrame = jFrame;
        this.connect4 = connect4;
        checkFonts();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseMotionListener);
        invalidateState();
    }

    private void checkFonts() {
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = g.getAvailableFontFamilyNames();
        for (int i = 0; i < fonts.length; i++) {
            if (fonts[i].equals("Roboto")) {
                mainFont = new Font("Roboto", Font.PLAIN, 11);
                return;
            }
        }
        mainFont = new Font("Arial", Font.PLAIN, 11);
    }

    public void invalidateState() {
        lastWidth = 0;
        lastHeight = 0;
        hoverColumn = -1;
        jFrame.setTitle("Connect " + connect4.getN() + " | " +
                connect4.getRows() + "×" + connect4.getColumns() + " | " +
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
