package ui;

import model.Bird;
import model.GameScore;
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
    private GameScore gameScore2;


    private static final int BIRD_WIDTH = 75;
    private static final int BIRD_HEIGHT = 75;
    private static final int BIRDS_INITIAL_XLOC = 200;
    private static final int BIRDS_INITIAL_YLOC = 200;

    private static final int PIPE_WIDTH = 100;
    private static final int INITIAL_PIPE_HEIGHT = 500; // must be super long to make sure we cant get appropriate random heights without messing up the image by scaling it
    private static final int PIPE_RANDOM_HEIGHT_POSITION_BOUND = 300; // rename this isnt actually the max pipe height // this is supposed to be bound
    private static final int PIPE_MAX_UPWARDS_BOUND = 200;
    private static final int PIPE_X_VELOCITY = 3;

    private static final int WIDTH_OF_GAMESCORE = 50;
    private static final int HEIGHT_OF_GAMESCORE = 50;
    private static final int X_POSITION_OF_GAMESCORE = SCREEN_WIDTH / 2;
    private static final int Y_POSITION_OF_GAMESCORE = 50;


    private static final int HEIGHT_OF_GROUND = 140;


    private static final int X_GAP_BETWEEN_PIPES = 250;
    private static final int PIPE_GAP = 400; // 125


    public Game() {
        initializeGameObjects();
    }

    /**
     * Initializes all Game Objects
     */
    private void initializeGameObjects() {
        bird = new Bird(BIRD_WIDTH, BIRD_HEIGHT);
        initializeGameScore();
        bird.setXLoc(BIRDS_INITIAL_XLOC);
        bird.setYLoc(BIRDS_INITIAL_YLOC);
        ground = new Ground(2 * SCREEN_WIDTH, HEIGHT_OF_GROUND);
        ground.setYLoc(SCREEN_HEIGHT - ground.getGameObjectImage().getHeight(null) / 2);
        gameStarted = false;
        createPipes();
        gameOver = false;
    }

    private void initializeGameScore() {
        gameScore2 = new GameScore(WIDTH_OF_GAMESCORE, HEIGHT_OF_GAMESCORE);
        gameScore2.setxPos(X_POSITION_OF_GAMESCORE);
        gameScore2.setyPos(Y_POSITION_OF_GAMESCORE);
    }


    // EFFECTS: intialize Pipes and set Y / X Coordinates
    /**
     * Initializes Pipes and sets Y / X coordinates
     */
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

    /**
     * Initializes x-location of all pipes in pipes
     */
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
    /**
     * Updates game, called every Timer tick
     */
    public void update() {
        if (gameStarted && gameOver == false) {
            bird.updateGravityOnBirdsYLocation();
            bird.updateRotationAngle();
            // update pipes and check for collisions rmbr
            updatePipesAndGameScore();
        }
        if (gameOver && gameStarted) {
            bird.updateGravityOnBirdsYLocation();
        }
    }




    /**
     * Returns true if a Pipe has left the screen
     * @param p Given Pipe to check if it has left the screen
     * @return True if p has left screen
     */
    private boolean leftScreen(Pipe p) {
        if (p.getxLoc() + p.getGameObjectImage().getWidth(null) < 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates all Pipes with the correct x-location and
     * checks if bird has passed a pipe to update gamescore
     */
    private void updatePipesAndGameScore() {
        for (Pipe pipe : pipes) {
            // update pipes x velocity
            pipe.movePipe(PIPE_X_VELOCITY);

            checkForCollisions(pipe);

            // update gameScore
            updateGameScoreHelper(pipe);

            // reset pipe x coordinates
            // randomize pipes height
            if (leftScreen(pipe)) {
                pipe.resetXLocation(pipes, X_GAP_BETWEEN_PIPES);
                pipe.randomizePipeHeight(PIPE_RANDOM_HEIGHT_POSITION_BOUND, PIPE_MAX_UPWARDS_BOUND);
                pipe.getCoorespondingPipe().setPipeHeightBasedOnOtherPipe(pipe, PIPE_GAP);
            }
        }
    }

    private void updateGameScoreHelper(Pipe pipe) {
        if (pipe.getOrientation() == true &&
                pipe.getxLoc() - PIPE_WIDTH / 2 < bird.getxLoc() &&
                pipe.getxLoc() - PIPE_WIDTH / 2 > bird.getxLoc() - 4) { // the minus 4 is there for game velocity of pipes
            gameScore2.incrementScore();
        }
    }

    private void checkForCollisions(Pipe pipe) {
        if (gameStarted) {
            Rectangle pipe1 = pipe.getBounds();
            Rectangle pipe2 = pipe.getCoorespondingPipe().getBounds();
            Rectangle birdRect = bird.getBounds();
            if (pipe1.intersects(birdRect)) {
                Rectangle intersectRect = birdRect.intersection(pipe1);
                collisionPixelChecker(pipe, intersectRect, birdRect, pipe1);
            } else if (pipe2.intersects(birdRect)) {
                Rectangle intersectRect = birdRect.intersection(pipe2);
                collisionPixelChecker(pipe.getCoorespondingPipe(), intersectRect, birdRect, pipe2);
            } else if (bird.getyLoc() + bird.getGameObjectImage().getHeight(null) / 2 > 700 - 140) {
                gameOver = true;
            }
        }
    }

    private void collisionPixelChecker(Pipe pipe, Rectangle interesctRect, Rectangle birdRect, Rectangle pipeRect) {
        BufferedImage birdImage = bird.toBufferedImage();
        BufferedImage pipeImage = pipe.toBufferedImage();

        int firstI = (int) (interesctRect.getMinX() - birdRect.getMinX());
        int firstJ = (int) (interesctRect.getMinY() - birdRect.getMinY());
        int bp1XHelper = (int) (birdRect.getMinX() - pipeRect.getMinX()); //helper variables to use when referring to collision object
        int bp1YHelper = (int) (birdRect.getMinY() - pipeRect.getMinY());

        for (int i = firstI; i < interesctRect.getWidth() + firstI; i++) { //
            for (int j = firstJ; j < interesctRect.getHeight() + firstJ; j++) {
                if ((birdImage.getRGB(i, j) & 0xFF000000) != 0x00
                        && (pipeImage.getRGB(i + bp1XHelper, j + bp1YHelper) & 0xFF000000) != 0x00) {
                    gameOver = true;
                }
            }
        }

    }


    // getters

    /**
     * Gets the private Bird variable bird
     * @return private bird variable
     */
    public Bird getBird() {
        return bird;
    }

    /**
     * Gets the private Ground variable ground
     * @return private ground variable
     */
    public Ground getGround() {
        return ground;
    }

    /**
     * Gets the private ArrayList of Pipe, pipes
     * @return private ArrayList variable pipes
     */
    public ArrayList<Pipe> getPipeArray() {
        return pipes;
    }

    public GameScore getGameScore() {
        return gameScore2;
    }


    // TODO might want to think about making a single method to render to GameTopJpanel
}
