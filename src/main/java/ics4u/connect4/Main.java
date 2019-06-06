package ics4u.connect4;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        final Frame app = new Frame("Connect Four");
        final GameView view = new GameView();

        app.setSize(800, 600);
        app.setLayout(null);
        app.setResizable(true);

        app.add(view);

        app.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                app.dispose();
            }
        });

        app.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension newSize = app.getSize();
                view.setSize(newSize.width, newSize.height);
            }
        });

        app.setMinimumSize(new Dimension(800, 600));
        app.setVisible(true);
    }
}
