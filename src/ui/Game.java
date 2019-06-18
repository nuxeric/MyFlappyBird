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
    private static final int BIRDS_INITIAL_XLOC = 100;
    private static final int BIRDS_INITIAL_YLOC = 100;

    public static final int MAXIMUM_PIPE_HEIGHT = SCREEN_HEIGHT - 300;
    public static final int PIPE_WIDTH = 100;
    public static final int INITIAL_PIPE_HEIGHT = 100;
    private static final int PIPE_X_VELOCITY = 1;


    public static final int HEIGHT_OF_GROUND = 140;


    private static final int X_GAP_BETWEEN_PIPES = 150;
    private static final int PIPE_GAP = 250;


    public Game() {
        intializeGameObjects();
    }

    private void intializeGameObjects() {
        bird = new Bird(INITIAL_WIDTH_OF_BIRD, INITIAL_HEIGHT_OF_BIRD);
        bird.setXLoc(BIRDS_INITIAL_XLOC);
        bird.setYLoc(BIRDS_INITIAL_YLOC);
        ground = new Ground(2*SCREEN_WIDTH, HEIGHT_OF_GROUND);
        gameStarted = false;
        createPipes();
        intializeXCoordOfPipes();

    }


    private void createPipes() {
        pipes = new ArrayList<>();
        boolean orientation = true;
        for (int i = 0; i < 6; i++) {
            Pipe pipeUp = new Pipe(PIPE_WIDTH , INITIAL_PIPE_HEIGHT, orientation);
            Pipe pipeDown = new Pipe(PIPE_WIDTH , INITIAL_PIPE_HEIGHT, !orientation);
            pipeUp.setYloc(SCREEN_HEIGHT - pipeUp.getGameObjectImage().getHeight(null) - HEIGHT_OF_GROUND);
            pipeDown.setYloc(0);
            pipes.add(pipeUp);
            pipes.add(pipeDown);
        }
    }

    private void intializeXCoordOfPipes() {
        int xLoc = SCREEN_WIDTH;
        int counter = 1; // this is bad design i think i should consider another implementation
        for (Pipe p : pipes) {
            if (counter % 2 == 0) {
                p.setXloc(xLoc);
                xLoc += PIPE_GAP;
                counter++;
            } else {
                p.setXloc(xLoc);
                counter++;
            }
        }
    }



    public void update() {
        if (gameStarted) {
            bird.updateGravityOnBirdsYLocation();
            bird.updateRotationAngle();
            //  ground.moveGround();
            movePipes();
            checkForCollisions();
        }
    }

    private void movePipes() {
        for (Pipe p: pipes) {
            p.movePipe(PIPE_X_VELOCITY);
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
