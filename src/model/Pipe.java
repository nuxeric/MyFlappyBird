package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

import static ui.Game.*;
import static ui.RunableApp.SCREEN_HEIGHT; // these static calls should be reduced to reduce coupling!

public class Pipe extends AbstractGameObject {




    private boolean orientation;



    //EFFECTS: scale the Pipe to the given width, and given height, and with correct orientation and intially set at yLoc = 0;
    public Pipe(int width, int height, boolean orientation) {
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
        gameObjectImage = gameObjectImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        yLoc = 0;


    }

    // creates a random height of two pipes with the appropriate Pipe_Gap
    public void randomizePipeHeight() {
        Random r = new Random();
            int newHeight = r.nextInt(MAXIMUM_PIPE_HEIGHT);
            gameObjectImage = gameObjectImage.getScaledInstance(PIPE_WIDTH, newHeight, Image.SCALE_SMOOTH);
    }

    //
    public void setPipeHeightBasedOnPipe(Pipe pipe, int gap) {
        int height1 =  pipe.gameObjectImage.getHeight(null);
        int height2 = height1 - gap;

    }

    public void setXloc(int x) {
        xLoc = x;
    }

    public void setYloc(int y) {
        yLoc = y;
    }

    public void movePipe(int xvelocity) {
        xLoc -= xvelocity;
    }


}
