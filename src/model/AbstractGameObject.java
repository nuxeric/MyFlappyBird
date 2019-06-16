package model;

import java.awt.*;

public abstract class AbstractGameObject {
    protected Image gameObjectImage;
    protected int xLoc, yLoc;

    public int getxLoc() {
        return xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public Image getGameObjectImage() {
        return gameObjectImage;
    }

    public Image getImageBoundaries() {
    }
}
