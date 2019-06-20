package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


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

        } else {
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

//    // creates a random height of two pipes with the appropriate
//    public void randomizePipeHeight(int maxPipeHeight) {
//        Random r = new Random();
//            int newHeight = r.nextInt(maxPipeHeight);
//            gameObjectImage = gameObjectImage.getScaledInstance(gameObjectImage.getWidth(null), newHeight, Image.SCALE_SMOOTH);
//    }
//
//    //
//    public void setPipeHeightBasedOnPipeAndGround(Pipe pipe, int gap, int minimumHeight) {
//        int heightdownwards =  pipe.gameObjectImage.getHeight(null);
//        int heightupwards = minimumHeight + heightdownwards - gap ;
//        if (heightupwards <= 0) {
//            heightupwards = minimumHeight;
//        }
//        gameObjectImage = gameObjectImage.getScaledInstance(gameObjectImage.getWidth(null), heightupwards, Image.SCALE_SMOOTH);
//
//    }

    public void setXloc(int x) {
        xLoc = x;
    }

    public void setYloc(int y) {
        yLoc = y;
    }

    public void movePipe(int xvelocity) {
        xLoc -= xvelocity;
    }


    public boolean getOrientation() {
        return orientation;
    }


    public void resetXLocation(ArrayList<Pipe> pipes, int space) {
        setXloc((pipes.size() / 2) * space - gameObjectImage.getWidth(null) /2);
    }
}
