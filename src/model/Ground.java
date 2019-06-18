package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static ui.RunableApp.SCREEN_HEIGHT;


public class Ground extends AbstractGameObject {

    private static final int X_VELOCITY = -4;

    // I must change all this later to make it more abstract, a ground object should be able
    // to make any ground object given widthheight
    // not just a specific one with static variable that should be in game
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
        yLoc = SCREEN_HEIGHT - gameObjectImage.getHeight(null);
    }

    public void moveGround() {
        xLoc += X_VELOCITY;
    }






}
