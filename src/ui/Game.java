package ui;

import model.Bird;
import model.Ground;
import model.Pipe;

import java.awt.*;
import java.awt.image.BufferedImage;
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


    public static final int BIRD_WIDTH = 75;
    public static final int BIRD_HEIGHT = 75;
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

    // EFFECTS: initialize all the objects in the game
    private void intializeGameObjects() {
        bird = new Bird(BIRD_WIDTH, BIRD_HEIGHT);
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
            pipeUp.setCoorespondingPipe(pipeDown);
            pipeDown.setCoorespondingPipe(pipeUp);
            pipeDown.randomizePipeHeight(PIPE_RANDOM_HEIGHT_POSITION_BOUND, PIPE_MAX_UPWARDS_BOUND);
            pipeDown.getCoorespondingPipe().setPipeHeightBasedOnOtherPipe(pipeDown, PIPE_GAP);
            pipes.add(pipeUp);
            pipes.add(pipeDown);
        }
        intializeXCoordOfPipes();
    }

    // EFFECTS: initialize the Xlocation of all pipes in pipes
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

            checkForCollisions(pipe);

            // update gameScore
            if (pipe.getOrientation() == true &&
                    pipe.getxLoc() + PIPE_WIDTH / 2  < bird.getxLoc() &&
                    pipe.getxLoc() + PIPE_WIDTH / 2  > bird.getxLoc() - 4) { // the minus 4 is there for game velocity of pipes
                gameScore++;
                System.out.println(gameScore);
            }

            // reset pipe x coordinates
            // randomize pipes height
            if (leftScreen(pipe)) {
                pipe.resetXLocation(pipes, X_GAP_BETWEEN_PIPES);
                pipe.randomizePipeHeight(PIPE_RANDOM_HEIGHT_POSITION_BOUND, PIPE_MAX_UPWARDS_BOUND);
                pipe.getCoorespondingPipe().setPipeHeightBasedOnOtherPipe(pipe, PIPE_GAP);
            }
        }
    }

    private void checkForCollisions(Pipe pipe) {
        if (gameStarted) {
            Rectangle pipe1 = pipe.getBounds();
            Rectangle pipe2 = pipe.getCoorespondingPipe().getBounds();
            Rectangle birdRect = bird.getBounds();
            if (pipe1.intersects(birdRect)) {
                Rectangle intersectRect = birdRect.intersection(pipe1);
                //collisionPixelChecker(pipe, intersectRect);
            } else if (pipe2.intersects(birdRect)) {
                Rectangle intersectRect = birdRect.intersection(pipe2);
                //collisionPixelChecker(pipe.getCoorespondingPipe(), intersectRect);
            }
        }
    }

    private void collisionPixelChecker(Pipe pipe, Rectangle interesctRect) {
        BufferedImage birdImage = bird.toBufferedImage();
        BufferedImage pipeImage = pipe.toBufferedImage();
        System.out.println("we should be hitting");

        int leftX = (int) interesctRect.getMinX();
        int rightX = (int) interesctRect.getMaxX();
        int topY = (int) interesctRect.getMinY();
        int botY = (int) interesctRect.getMaxY();

        for (int i = leftX; i <= rightX; i++) {
            for (int j = topY; j <= botY; j++) {
                if ((birdImage.getRGB(i , j) & 0xFF000000) != 0x00000000 &&
                        (pipeImage.getRGB(i, j) & 0xFF000000) != 0x00000000) {
                    System.out.println( "hellodudeee");
                    gameStarted = false;
                }
            }
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
