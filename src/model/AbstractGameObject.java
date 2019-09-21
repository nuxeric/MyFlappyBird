package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractGameObject {
    protected Image gameObjectImage;
    protected int xLoc, yLoc;

    /**
     * Returns the x location of the GameObject
     * @return int
     */
    public int getxLoc() {
        return xLoc - gameObjectImage.getWidth(null) /2 ;
    }

    /**
     * Returns the y location of the GameObject
     * @return int
     */
    public int getyLoc() {
        return yLoc - gameObjectImage.getHeight(null) /2 ;
    }

    /**
     * Returns the Image of the GameObject
     * @return Image
     */
    public Image getGameObjectImage() {
        return gameObjectImage;
    }

    /**
     * Sets the x location of the given GameObject
     * @param x the x coordinate of the xloc
     */
    public void setXloc(int x) {
        xLoc = x;
    }

    /**
     * Sets the y location of the given GameObject
     * @param y the y coordinate of the xloc
     */
    public void setYloc(int y) {
        yLoc = y;
    }

    /**
     * Returns the Rectangle (or bounds) of the GameObject
     * @return Rectangle the rectangle of the GameObject
     */
    public Rectangle getBounds() {
        return new Rectangle(xLoc - gameObjectImage.getWidth(null) /2, yLoc - gameObjectImage.getHeight(null) /2, gameObjectImage.getWidth(null), gameObjectImage.getHeight(null));
    }

    /**
     * Returns the Rectangle (or bounds) of the GameObject
     * @return Rectangle the rectangle of the GameObject
     */
    public BufferedImage toBufferedImage()
    {
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(gameObjectImage.getWidth(null), gameObjectImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(gameObjectImage, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

}
