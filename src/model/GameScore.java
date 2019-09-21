package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameScore {

    private ArrayList<Integer> score;
    private Image zeroImage;
    private Image oneImage;
    private Image twoImage;
    private Image threeImage;
    private Image fourImage;
    private Image fiveImage;
    private Image sixImage;
    private Image sevenImage;
    private Image eightImage;
    private Image nineImage;
    private int xPos;
    private int yPos;

    /**
     * GameScore constructor which sets the width and height of the GameScore Object
     * @param width the width of the GameScore
     * @param height the height of the GameScore
     */
    public GameScore(int width, int height) {
        score = new ArrayList<Integer>();
        for (int i =0; i < 1000; i++) {
            score.add(0);
        }
        loadAllImages(width, height);
    }

    /**
     * GameScore constructor helper that loads all the correct Images into the private members
     * @param width the width of the GameScore Images
     * @param height the height of the GameScore Imagesgo
     */
    private void loadAllImages(int width, int height) {
        Image image0 = null;
        Image image1 = null;
        Image image2 = null;
        Image image3 = null;
        Image image4 = null;
        Image image5 = null;
        Image image6 = null;
        Image image7 = null;
        Image image8 = null;
        Image image9 = null;
        try {
            image0 = ImageIO.read(getClass().getResource("/resources/images/Zero.png"));
            image1 = ImageIO.read(getClass().getResource("/resources/images/One.png"));
            image2 = ImageIO.read(getClass().getResource("/resources/images/Two.png"));
            image3 = ImageIO.read(getClass().getResource("/resources/images/Three.png"));
            image4 = ImageIO.read(getClass().getResource("/resources/images/Four.png"));
            image5 = ImageIO.read(getClass().getResource("/resources/images/Five.png"));
            image6 = ImageIO.read(getClass().getResource("/resources/images/Six.png"));
            image7 = ImageIO.read(getClass().getResource("/resources/images/Seven.png"));
            image8 = ImageIO.read(getClass().getResource("/resources/images/Eight.png"));
            image9 = ImageIO.read(getClass().getResource("/resources/images/Nine.png"));
        } catch (IOException e) {
            System.out.println("Number Images didnt load properly");
            e.printStackTrace();
        }
        zeroImage = image0.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        oneImage = image1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        twoImage = image2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        threeImage = image3.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        fourImage = image4.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        fiveImage = image5.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        sixImage = image6.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        sevenImage = image7.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        eightImage = image8.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        nineImage = image9.getScaledInstance(width, height, Image.SCALE_SMOOTH);

    }

    /**
     * Clears the private ArrayList score and "resets" the GameScore for the next game
     */
    public void resetScore() {
        score.clear();
    }


    /**
     * increments the GameScore
     */
    public void incrementScore() {
        int i = 0;
        if (score.get(i) == 9 && i == 0) {
            score.set(i, 0);
            incrementScoreHelper(i + 1);
        } else if (score.get(i) < 9) {
            int h = score.get(i);
            score.set(i, h + 1);
        } else {
            incrementScoreHelper(i + 1);
        }
    }


    /**
     * Sets the x location of the given GameObject
     * @param i
     */
    private void incrementScoreHelper(int i) {
        if (score.get(i) < 9) {
            int h = score.get(i);
            score.set(i, h + 1);
        } else {
            score.set(i, 0);
            incrementScoreHelper(i + 1);
        }
    }


    /**
     * Returns the correct number sprite Image given an integer
     * @param i an integer that represents the correct Image
     * @return Image, the correct Image according to the given paramater
     */
    public Image getGameObjectImage(int i) {
        switch (i) {
            case 0 :
                return zeroImage;
            case 1:
                return oneImage;
            case 2:
                return twoImage;
            case 3:
                return threeImage;
            case 4:
                return fourImage;
            case 5:
                return fiveImage;
            case 6:
                return sixImage;
            case 7:
                return sevenImage;
            case 8:
                return eightImage;
            case 9:
                return nineImage;
        }
        return null;
    }


    /**
     * Sets the x location of the given GameObject
     * @param xPos the x coordinate of the xloc
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Sets the x location of the given GameObject
     * @param yPos the x coordinate of the xloc
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Returns the private ArrayList score
     * @return score, which is an arrayListIntegers that represent the game score
     */
    public ArrayList<Integer> getScoreList() {
        return score;
    }

    /**
     * Returns the x location of the GameScore
     * @return int
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Returns the y location of the GameScore
     * @return int
     */
    public int getyPos() {
        return yPos;
    }



}
