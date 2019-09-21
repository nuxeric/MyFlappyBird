package model;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Bird extends AbstractGameObject {
    private double yVelocity, angle;
    private static final double BIRD_GRAVITY = 0.5;
    private static final int BIRD_ANGLE_TO_ADD = 17;
    private static final int BIRD_TILT_ANGLE = -18;



    /**
     * Bird constructor which sets the width and height of the Bird, scales the Bird to the given width and height.
     * Initially set at (0,0)
     * @param width the width of the GameScore
     * @param height the height of the GameScore
     */
    public Bird(int width, int height) {
        intializeBirdSprites();
        gameObjectImage = gameObjectImage.getScaledInstance(width,height, Image.SCALE_SMOOTH);
        xLoc = 0;
        yLoc = 0;
        yVelocity = 0;
        angle = Math.toRadians(15);
    }

    /**
     * Intializes Bird sprite Images into the gameObjectImage private member
     */
    private void intializeBirdSprites() {
        Image image = null;
        try {
            image = ImageIO.read(getClass().getResource("/resources/images/BirdImage.png"));
        } catch (IOException e) {
            System.out.println("Bird Image didn't load properly");
            e.printStackTrace();
        }
        gameObjectImage = image;

    }

    /**
     * Updates the birds ylocation and yvelocity
     */
    public void updateGravityOnBirdsYLocation() {
        yVelocity += BIRD_GRAVITY;
        yLoc += yVelocity;
    }

    /**
     * Returns true if bird is falling, false if not
     */
    public boolean falling() {
        if (yVelocity > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * "Flaps" the bird and gives it a boost in upwards momentum and updates ylocation
     */
    public void flap() {
        if (yVelocity > 0) {
            yVelocity = -8;
        } else {
            // in the case that we are already going up (-y) we want to be able to flap again with no delay
            yVelocity = -8;
        }
        yLoc += yVelocity;
    }

    /**
     * Updates the Bird's angle to rotate "downwards"
     */
    public void updateRotationAngle() {
        double angleToAdd = Math.toRadians(BIRD_ANGLE_TO_ADD);
        if (falling()) {
            if (angle < Math.toRadians(90)) {
                angle += angleToAdd;
            } else {
                angle = Math.toRadians(90);
            }
        } else if (!falling()) {
            if (angle <= Math.toRadians(BIRD_TILT_ANGLE)) {
                // if its already maxed then dont do anything
            } else {
                angleToAdd = Math.toRadians(BIRD_TILT_ANGLE);
                angle += angleToAdd;
            }
        }
    }



    /**
     * Returns the angle of the Bird
     */
    public double getAngle() {
        return angle;
    }


}
