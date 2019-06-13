package ics4u.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

public class GameView extends JComponent {

    private Connect4 state;

    private static Color darkCyan = Color.CYAN.darker();

    public GameView(Connect4 state) {
        this.state = state;

        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {

            }

            public void mouseMoved(MouseEvent e) {
            }
        });
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        g.setColor(darkCyan);
        g.fillRect(0, 0, w, h);

        int cols = state.getColumns();
        int rows = state.getRows();

        int minWidth = cols * 12 + 1;
        int maxWidth = 10 * minWidth;

        int minHeight = rows * 12 + 1;
        int maxHeight = 10 * minHeight;

        if (w < minWidth || h < minHeight) {
            return;
        }

        double size = Math.min(h / (rows * 12 + 1.0), w / (cols * 12 + 1.0));
        double dx = (w - size * cols * 12) / 2;
        double dy = (h - size * rows * 12) / 2;

        if (w > maxWidth) {
            dx = (w - maxWidth) / 2;
            size = Math.min(size, 10);
        }
        if (h > maxHeight) {
            dy = (h - maxHeight) / 2;
            size = Math.min(size, 10);
        }

        dx += size;
        dy += size;

        double diam = size * 10;
        int[][] board = state.getBoard();

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Ellipse2D ellipse = new Ellipse2D.Double();
                ellipse.setFrame(dx + size * 12 * i, dy + size * 12 * j, diam, diam);
                int piece = board[j][i];
                g.setColor(piece == 0 ? Color.WHITE : (piece == 1 ? Color.RED : Color.YELLOW));
                g2.fill(ellipse);
            }
        }
    }
}
