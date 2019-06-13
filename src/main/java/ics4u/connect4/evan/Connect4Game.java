package ics4u.connect4.evan;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

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
        if (board.check() != 0) firePropertyChange("Win", false, true);
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
