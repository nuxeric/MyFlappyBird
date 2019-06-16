package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static ui.RunableApp.SCREEN_WIDTH;


public class Ground extends AbstractGameObject {

    private static final int X_VELOCITY = -4;
    private static final int HEIGHT_OF_GROUND = 200;
    private static final int Y_LOCATION_GROUND = 580;

    public Ground() {
        Image image = null;
        try {
            image = ImageIO.read(getClass().getResource("/resources/images/Ground.png"));
        } catch (IOException e) {
            System.out.println("Ground Image didn't load properly");
            e.printStackTrace();
        }
        gameObjectImage = image;
        gameObjectImage = gameObjectImage.getScaledInstance(2*SCREEN_WIDTH,HEIGHT_OF_GROUND, Image.SCALE_SMOOTH);
        xLoc = 0;
        yLoc = Y_LOCATION_GROUND;
    }

    public void moveGround() {
        xLoc += X_VELOCITY;
    }






}
