package model;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Bird extends AbstractGameObject {
    private double yVelocity, angle;
    private Image sprite1, sprite2, sprite3;


    private static final double BIRD_GRAVITY = 0.5;
    private static final int BIRD_ANGLE_TO_ADD = 17;
    private static final int BIRD_TILT_ANGLE = -18;


    // EFFECTS: scale the gameObject to the given initialWidth, and given initialHeight, intially set at (0,0)
    public Bird(int width, int height) {
        intializeBirdSprites();
        gameObjectImage = gameObjectImage.getScaledInstance(width,height, Image.SCALE_SMOOTH);
        xLoc = 0;
        yLoc = 0;
        yVelocity = 0;
        angle = Math.toRadians(15);
    }

    // TODO I NEED TO IMPLEMENT SPRITES
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

    // setters

    public void setXLoc(int x) {
        xLoc = x;
    }

    public void setYLoc(int y) {
        yLoc = y;
    }


}
