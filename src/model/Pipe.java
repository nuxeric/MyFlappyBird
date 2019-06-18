package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

import static model.Ground.HEIGHT_OF_GROUND;
import static ui.Game.MAXIMUM_PIPE_UP_HEIGHT;
import static ui.RunableApp.SCREEN_HEIGHT;

public class Pipe extends AbstractGameObject {

    private static final int PIPE_WIDTH = 100;
    private static final int INITIAL_PIPE_HEIGHT = 100;
    private static final int PIPE_X_VELOCITY = 1;

    private boolean orientation;



    //EFFECTS: scale the Pipe to the given initialWidth, and given initialHeight, and with correct orientation
    public Pipe(boolean orientation) {
        Image image = null;
        if (orientation) {
            // this is up!!
            this.orientation = orientation;
            try {
                image = ImageIO.read(getClass().getResource("/resources/images/PipeUp.png"));
            } catch (IOException e) {
                System.out.println("Image didn't load properly for PipeDown");
                e.printStackTrace();
            }

        } else if (!orientation) {
            // this is south!!!
            this.orientation = orientation;
            try {
                image = ImageIO.read(getClass().getResource("/resources/images/PipeDown.png"));
            } catch (IOException e) {
                System.out.println("Image didn't load properly for PipeUp");
                e.printStackTrace();
            }
        }


        gameObjectImage = image;
        gameObjectImage = gameObjectImage.getScaledInstance(PIPE_WIDTH, INITIAL_PIPE_HEIGHT, Image.SCALE_SMOOTH);

        if (orientation) {
            yLoc = SCREEN_HEIGHT - gameObjectImage.getHeight(null) - HEIGHT_OF_GROUND;
        } else {
            yLoc = 0;
        }


    }

    // creates a random height of two pipes with the appropriate Pipe_Gap
    public void randomizePipeHeights() {
        Random r = new Random();
        if (orientation) {
            // if the pipe is facing up
            r.nextInt(MAXIMUM_PIPE_UP_HEIGHT);


        } else {

        }
    }

    public void setPipeXLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    public void movePipe() {
        xLoc -= PIPE_X_VELOCITY;
    }


}
