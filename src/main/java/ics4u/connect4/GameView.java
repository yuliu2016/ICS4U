package ics4u.connect4;

import java.awt.*;

class GameView extends Canvas {


    /**
     * Overrides Canvas' update method.
     * Creates a buffer image from the current component,
     * then calls the component's paint method with the graphic
     * context created from the image. Finally it calls drawImage
     * on the component's graphic context to "blit" the image onto
     * the screen
     *
     * @param g the onscreen component's graphic context
     */

    public void update(Graphics g) {
        Image bufferedImage = createImage(getWidth(), getHeight());
        Graphics bufferedGraphics = bufferedImage.getGraphics();
        bufferedGraphics.setColor(getBackground());
        bufferedGraphics.fillRect(0, 0, getWidth(), getHeight());
        paint(bufferedGraphics);
        g.drawImage(bufferedImage, 0, 0, this);
    }
}
