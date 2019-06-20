package model;

import java.awt.*;

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

}
