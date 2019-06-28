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


    public void randomizePipeHeight(int bound, int maxUpwardsBound) {
        Random r = new Random();
        int newYLocation = r.nextInt(bound) - maxUpwardsBound;
        setYloc(newYLocation); // need the + - for negative values as well ( so the pipe can move upwards)
    }

    public void setPipeHeightBasedOnOtherPipe(Pipe p, int gap) {
        int pipeOnScreenSpace;
        if (p.yLoc <= 0) {
            pipeOnScreenSpace = p.yLoc + p.gameObjectImage.getHeight(null) / 2;
        } else {
            pipeOnScreenSpace = p.gameObjectImage.getHeight(null) / 2 + p.yLoc;
        }

        // set a minimum on this so that the bottom pipe still always renders.
        this.setYloc(pipeOnScreenSpace + gap + this.gameObjectImage.getHeight(null) / 2);

    }

    public void movePipe(int xvelocity) {
        xLoc -= xvelocity;
    }


    public boolean getOrientation() {
        return orientation;
    }


    public void resetXLocation(ArrayList<Pipe> pipes, int space) {
        setXloc((pipes.size() / 2) * space - gameObjectImage.getWidth(null) / 2);
    }

}
