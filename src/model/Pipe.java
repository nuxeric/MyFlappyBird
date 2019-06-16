package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Pipe extends AbstractGameObject {


    //     EFFECTS: scale the AbstractPipe to the given initialWidth, and given initialHeight, and with correct orientation
    public Pipe(int width, int height, boolean orientation ) {
        Image image = null;
        if (orientation) {
            // this is north!!
            try {
                image = ImageIO.read(getClass().getResource("/resources/images/PipeDown.png"));
            } catch (IOException e) {
                System.out.println("Image didn't load properly for PipeDown");
                e.printStackTrace();
            }
        } else if (!orientation) {
            // this is south!!!
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
        xLoc = 500;
    }
}
