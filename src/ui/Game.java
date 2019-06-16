package ui;

import model.Bird;
import model.Ground;
import model.Pipe;

import java.util.ArrayList;


public class Game {
    public boolean gameStarted;
    public boolean gameOver;
    private Bird bird;
    private Ground ground;
    private ArrayList<Pipe> pipes;

    public static final int INITIAL_WIDTH_OF_BIRD = 50;
    public static final int INITIAL_HEIGHT_OF_BIRD = 50;


    public Game() {
        intializeGameObjects();
    }

    private void intializeGameObjects() {
        bird = new Bird(INITIAL_WIDTH_OF_BIRD, INITIAL_HEIGHT_OF_BIRD);
        ground = new Ground();
        gameStarted = false;
        pipes = new ArrayList<>();
        pipes.add(new Pipe(100, 100, true));
        pipes.add(new Pipe(100, 100, false));
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
        // must add implementation later once we make a design choice
        if (gameStarted) {
        }
    }

    // getters

    public Bird getBird() {
        return bird;
    }

    public Ground getGround() {
        return ground;
    }

    public ArrayList<Pipe> getPipeArray() {
        return pipes;
    }


    // might want to think about making a single method to render to GameTopJpanel
}
