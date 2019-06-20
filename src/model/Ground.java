package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;



public class Ground extends AbstractGameObject {

    private static final int X_VELOCITY = -4;

    public Ground(int width, int height) {
        Image image = null;
        try {
            image = ImageIO.read(getClass().getResource("/resources/images/Ground.png"));
        } catch (IOException e) {
            System.out.println("Ground Image didn't load properly");
            e.printStackTrace();
        }
        gameObjectImage = image;
        gameObjectImage = gameObjectImage.getScaledInstance(width,height, Image.SCALE_SMOOTH);
        xLoc = 0;

    }

    public void moveGround() {
        xLoc += X_VELOCITY;
    }


    public void setYLoc(int y) {
        yLoc = y;
    }

}
