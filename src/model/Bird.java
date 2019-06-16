package model;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Bird extends AbstractGameObject {
    private double yVelocity, angle;


    private static final double BIRD_GRAVITY = 0.5;
    private static final int BIRD_ANGLE_TO_ADD = 17;
    private static final int BIRD_TILT_ANGLE = -18;
    private static final int BIRDS_INITIAL_XLOC = 100; // might want to change these
    private static final int BIRDS_INITIAL_YLOC = 100; // because it doesnt really seem to belong here , you probs want to choose from gametop panel where it goes


    // EFFECTS: scale the gameObject to the given initialWidth, and given initialHeight
    public Bird(int width, int height) {
        Image image = null;
        try {
            image = ImageIO.read(getClass().getResource("/resources/images/BirdImage.png"));
        } catch (IOException e) {
            System.out.println("Bird Image didn't load properly");
            e.printStackTrace();
        }
        gameObjectImage = image;
        gameObjectImage = gameObjectImage.getScaledInstance(width,height, Image.SCALE_SMOOTH);
        xLoc = BIRDS_INITIAL_XLOC;
        yLoc = BIRDS_INITIAL_YLOC;
        yVelocity = 0;
        angle = Math.toRadians(15);
    }

    // EFFECTS: updates the birds ylocation and yvelocity
    public void updateGravityOnBirdsYLocation() {
        yVelocity += BIRD_GRAVITY;
        yLoc += yVelocity;
    }

    // EFFECTS: returns true if bird is falling, false if not
    public boolean falling() {
        if (yVelocity > 0) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: updates the bird's yvelocity and ylocation when user presses space
    public void flap() {
        if (yVelocity > 0) {
            yVelocity = -8;
        } else {
            // in the case that we are already going up (-y) we want to be able to flap again with no delay
            yVelocity = -8;
        }
        yLoc += yVelocity;
    }

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




    // Getters

    public double getAngle() {
        return angle;
    }


}
