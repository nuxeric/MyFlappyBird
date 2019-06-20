package ui;

import model.Bird;
import model.Ground;
import model.Pipe;

import java.util.ArrayList;

import static ui.RunableApp.SCREEN_HEIGHT;
import static ui.RunableApp.SCREEN_WIDTH;


public class Game {
    public boolean gameStarted;
    public boolean gameOver;
    private Bird bird;
    private Ground ground;
    private ArrayList<Pipe> pipes;


    public static final int INITIAL_WIDTH_OF_BIRD = 50;
    public static final int INITIAL_HEIGHT_OF_BIRD = 50;
    private static final int BIRDS_INITIAL_XLOC = 0;
    private static final int BIRDS_INITIAL_YLOC = 0;

    public static final int MAXIMUM_PIPE_HEIGHT = SCREEN_HEIGHT - 300;
    private static final int MINIMUM_PIPE_HEIGHT = 100;
    public static final int PIPE_WIDTH = 100;
    public static final int INITIAL_PIPE_HEIGHT = 100;
    private static final int PIPE_X_VELOCITY = 5;


    public static final int HEIGHT_OF_GROUND = 140;


    private static final int X_GAP_BETWEEN_PIPES = 250;
    private static final int PIPE_GAP = 250;


    public Game() {
        intializeGameObjects();
    }

    // EFFECTS: intialize all the objects in the game
    private void intializeGameObjects() {
        bird = new Bird(INITIAL_WIDTH_OF_BIRD, INITIAL_HEIGHT_OF_BIRD);
        bird.setXLoc(BIRDS_INITIAL_XLOC);
        bird.setYLoc(BIRDS_INITIAL_YLOC);
        ground = new Ground(2*SCREEN_WIDTH, HEIGHT_OF_GROUND);
        ground.setYLoc(SCREEN_HEIGHT - ground.getGameObjectImage().getHeight(null) / 2);
        gameStarted = false;
        createPipes();
    }


    // EFFECTS: intialize Pipes and set Y / X Coordinates
    private void createPipes() {
        pipes = new ArrayList<>();
        boolean orientation = true;
        for (int i = 0; i < 6; i++) {
            Pipe pipeUp = new Pipe(PIPE_WIDTH , INITIAL_PIPE_HEIGHT, orientation);
            Pipe pipeDown = new Pipe(PIPE_WIDTH , INITIAL_PIPE_HEIGHT, !orientation);
            pipeUp.setYloc(SCREEN_HEIGHT - pipeUp.getGameObjectImage().getHeight(null) / 2 - HEIGHT_OF_GROUND);
            pipeDown.setYloc(0);
            pipes.add(pipeUp);
            pipes.add(pipeDown);
        }
        intializeXCoordOfPipes();
    }

    // EFFECTS: intialize the Xlocation of all pipes in pipes
    private void intializeXCoordOfPipes() {
        int xLoc = SCREEN_WIDTH;
        int counter = 1; // this is bad design i think i should consider another implementation
        for (Pipe p : pipes) {
            if (counter % 2 == 0) {
                p.setXloc(xLoc);
                xLoc += X_GAP_BETWEEN_PIPES;
                counter++;
            } else {
                p.setXloc(xLoc);
                counter++;
            }
        }
    }



    // EFFECTS: updates the game every Timer refresh rate
    public void update() {
        if (gameStarted) {
            bird.updateGravityOnBirdsYLocation();
            bird.updateRotationAngle();
            //  ground.moveGround();
            movePipes();
            updatePipes();
            checkForCollisions();
        }
    }

    // updates Pipes (resets x location and randomizes gaps between pipes)
    private void updatePipes() {
        // TODO implement this
        for (Pipe p : pipes) {
            if (leftScreen(p)) {
               p.resetXLocation(pipes, X_GAP_BETWEEN_PIPES);
                System.out.println(p.getxLoc());
            }
        }

    }

    // returns True if Pipe has left screen
    private boolean leftScreen(Pipe p) {
        if (p.getxLoc() + p.getGameObjectImage().getWidth(null) < 0 ) {
            return true;
        } else {
            return false;
        }
    }

    // updates pipes with
    private void movePipes() {
        for (Pipe p: pipes) {
            p.movePipe(PIPE_X_VELOCITY);
        }
    }

    private void checkForCollisions() {
        if (gameStarted) {
            // TODO must add implementation later once we make a design choice
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


    // TODO might want to think about making a single method to render to GameTopJpanel
}
