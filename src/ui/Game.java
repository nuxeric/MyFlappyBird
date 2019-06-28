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
    public int gameScore;


    public static final int INITIAL_WIDTH_OF_BIRD = 75;
    public static final int INITIAL_HEIGHT_OF_BIRD = 75;
    private static final int BIRDS_INITIAL_XLOC = 200;
    private static final int BIRDS_INITIAL_YLOC = 200;

    public static final int PIPE_WIDTH = 100;
    public static final int INITIAL_PIPE_HEIGHT = 500; // must be super long to make sure we cant get appropriate random heights without messing up the image by scaling it
    public static final int PIPE_RANDOM_HEIGHT_POSITION_BOUND = 300; // rename this isnt actually the max pipe height // this is supposed to be bound
    public static final int PIPE_MAX_UPWARDS_BOUND = 200;
    private static final int PIPE_X_VELOCITY = 3;


    public static final int HEIGHT_OF_GROUND = 140;


    private static final int X_GAP_BETWEEN_PIPES = 250;
    private static final int PIPE_GAP = 125;


    public Game() {
        intializeGameObjects();
    }

    // EFFECTS: intialize all the objects in the game
    private void intializeGameObjects() {
        bird = new Bird(INITIAL_WIDTH_OF_BIRD, INITIAL_HEIGHT_OF_BIRD);
        gameScore = 0;
        bird.setXLoc(BIRDS_INITIAL_XLOC);
        bird.setYLoc(BIRDS_INITIAL_YLOC);
        ground = new Ground(2 * SCREEN_WIDTH, HEIGHT_OF_GROUND);
        ground.setYLoc(SCREEN_HEIGHT - ground.getGameObjectImage().getHeight(null) / 2);
        gameStarted = false;
        createPipes();
    }


    // EFFECTS: intialize Pipes and set Y / X Coordinates
    private void createPipes() {
        pipes = new ArrayList<>();
        boolean orientation = true;
        for (int i = 0; i < 6; i++) {
            Pipe pipeUp = new Pipe(PIPE_WIDTH, INITIAL_PIPE_HEIGHT, orientation);
            Pipe pipeDown = new Pipe(PIPE_WIDTH, INITIAL_PIPE_HEIGHT, !orientation);
            pipeDown.randomizePipeHeight(PIPE_RANDOM_HEIGHT_POSITION_BOUND, PIPE_MAX_UPWARDS_BOUND);
            pipeUp.setPipeHeightBasedOnOtherPipe(pipeDown, PIPE_GAP);
            pipes.add(pipeUp);
            pipes.add(pipeDown);
        }
        intializeXCoordOfPipes();
    }

    // EFFECTS: intialize the Xlocation of all pipes in pipes
    private void intializeXCoordOfPipes() {
        int xLoc = SCREEN_WIDTH + 200;
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
            updatePipes();
            checkForCollisions();

        }
    }


    // returns True if Pipe has left screen
    private boolean leftScreen(Pipe p) {
        if (p.getxLoc() + p.getGameObjectImage().getWidth(null) < 0) {
            return true;
        } else {
            return false;
        }
    }

    // updates pipes with
    private void updatePipes() {
        for (Pipe pipe : pipes) {
            // update pipes x velocity
            pipe.movePipe(PIPE_X_VELOCITY);

            // update gameScore
            if (pipe.getOrientation() == true &&
                    pipe.getxLoc() + PIPE_WIDTH / 2  < bird.getxLoc() &&
                    pipe.getxLoc() + PIPE_WIDTH / 2  > bird.getxLoc() - 4) { // the minus 4 is there for game velocity of pipes
                gameScore++;
                System.out.println(gameScore);
            }


            // reset pipe x coordinates
            if (leftScreen(pipe)) {
                pipe.resetXLocation(pipes, X_GAP_BETWEEN_PIPES);
            }

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
