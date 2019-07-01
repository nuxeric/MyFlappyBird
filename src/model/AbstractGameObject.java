package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractGameObject {
    protected Image gameObjectImage;
    protected int xLoc, yLoc;

    // EFFECTS: returns the xCoord of AbstractGameObject (centered in image)
    public int getxLoc() {
        return xLoc - gameObjectImage.getWidth(null) /2 ;
    }

    // EFFECTS: returns the yCoord of AbstrasctGameObject (centered in image)
    public int getyLoc() {
        return yLoc - gameObjectImage.getHeight(null) /2 ;
    }

    // EFFECTS: returns the Image of AbstractGameObject
    public Image getGameObjectImage() {
        return gameObjectImage;
    }

    public void setXloc(int x) {
        xLoc = x;
    }

    public void setYloc(int y) {
        yLoc = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(xLoc - gameObjectImage.getWidth(null) /2, yLoc - gameObjectImage.getHeight(null) /2, gameObjectImage.getWidth(null), gameObjectImage.getHeight(null));
    }


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
