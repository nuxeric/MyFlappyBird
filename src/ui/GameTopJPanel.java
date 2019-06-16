package ui;

import model.Bird;
import model.Pipe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;
// I might want to think about moving KeyListener as that is not part of drawing maybe make a new keyboard class to handle that
public class GameTopJPanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private Image backGroundImage;
    private Game game;


    GameTopJPanel() {
        super.setDoubleBuffered(true);
        timer = new Timer(20, this);
        intializeBackgroundImage();
        game = new Game();

        // all my objects must be before my timer starts

        timer.start(); // gotta put this behind the initiliazation to prevent null ptr (i think this fixes all my problems of having bird globaly defined


    }


    private void intializeBackgroundImage() {
        try {
            backGroundImage = ImageIO.read(getClass().getResource("/resources/images/GameBackGround.jpg"));
        } catch (IOException e) {
            System.out.println("background image didnt load");
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), null);
        //Make a backup so that we can reset our graphics object after using it.
        AffineTransform backup = g2d.getTransform();
        handleBirdRotationForDrawing(g2d);
        //Draw our image like normal
        g2d.drawImage(game.getBird().getGameObjectImage(), game.getBird().getxLoc(), game.getBird().getyLoc(), null);
        //Reset our graphics object so we can draw with it again.
        g2d.setTransform(backup);
        g2d.drawImage(game.getGround().getGameObjectImage(), game.getGround().getxLoc(), game.getGround().getyLoc(), null);
        for (Pipe p : game.getPipeArray()) {
            g2d.drawImage(p.getGameObjectImage(),p.getxLoc(),p.getyLoc(),null);
        }
    }

    private void handleBirdRotationForDrawing(Graphics2D g2d) {
        AffineTransform a = AffineTransform.getRotateInstance(game.getBird().getAngle(),
                game.getBird().getxLoc() + game.getBird().getGameObjectImage().getWidth(null) / 2,
                game.getBird().getyLoc() + game.getBird().getGameObjectImage().getHeight( null) / 2);
            //Set our Graphics2D object to the transform
            g2d.setTransform(a);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // invoked by timer every whatever ms
        // I should probably add a method to update everythings location in the game
        game.update();
        repaint(); // I need repaint to call paintComponent method
    }



    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // nothing here since we are not typing anything
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (game.gameStarted) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                game.getBird().flap();
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                game.gameStarted = true;
            }
        }

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // nothing here because I have not read up on released documentation
    }
}
