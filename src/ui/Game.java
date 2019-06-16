package ui;

import model.Bird;
import model.Ground;


public class Game {
    public boolean gameStarted;
    public boolean gameOver;
    private Bird bird;
    private Ground ground;

    public static final int INITIAL_WIDTH_OF_BIRD = 50;
    public static final int INITIAL_HEIGHT_OF_BIRD = 50;


    public Game() {
        intializeGameObjects();
    }

    private void intializeGameObjects() {
        bird = new Bird(INITIAL_WIDTH_OF_BIRD, INITIAL_HEIGHT_OF_BIRD);
        ground = new Ground();
        gameStarted = false;
    }



    // getters

    public Bird getBird() {
        return bird;
    }

    public Ground getGround() {
        return ground;
    }

    public void update() {
        if (gameStarted) {
            bird.updateGravityOnBirdsYLocation();
            bird.updateRotationAngle();
            //  ground.moveGround();
            checkForCollisions();
        }
    }

    private void checkForCollisions() {
        if (gameStarted) {
        }
    }

}
