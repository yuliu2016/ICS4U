package ics4u.connect4;

import java.awt.*;

public class GameView extends Component {

    private Connect4 state;

    public GameView(Connect4 state) {
        this.state = state;
    }

    public void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, w, h);

        int cols = state.getColumns();
        int rows = state.getRows();

        int minWidth = cols * 12 + 2;
        int maxWidth = 10 * minWidth;

        int minHeight = rows * 12 + 2 ;
        int maxHeight = 10 * minHeight;

        if (w < minWidth || h < minHeight) {
            return;
        }

        double size = Math.min(h / (rows * 12 + 2.0), w / (cols * 12 + 2.0));
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

//        System.out.println(dx + " " + dy);

        double diam = size * 10;
        int[][] board = state.getBoard();

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                int x = (int) (dx + size * 12 * i);
                int y = (int) (dy + size * 12 * j);
//                System.out.println(i + ", " + j + " ," + x + ", " + y);
                int piece = board[j][i];
                g.setColor(piece == 0? Color.WHITE : (piece == 1? Color.RED: Color.YELLOW));
                g.fillOval(x, y, (int) diam, (int) diam);
            }
        }
    }
}
