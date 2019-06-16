package ui;

import javax.swing.*;

public class RunableApp {
    public static final int SCREEN_WIDTH = 700, SCREEN_HEIGHT = 700;

    public static void main(String[] args) {
        // sets up the initial jframe
        JFrame frame = new JFrame();
        GameTopJPanel gameTopJPanel = new GameTopJPanel();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);



        // adding in the keyboard listener
        frame.addKeyListener(gameTopJPanel);



        // add in the Components to jframe to set up all visuals
        frame.add(gameTopJPanel);
        frame.setVisible(true); // this fixes the issue I had where I couldnt see anything untill after i resized the window

        // add in KeyBoardListener
    }
}
