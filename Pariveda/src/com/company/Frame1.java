package com.company;

import javax.swing.*;//associated with the frame and imageIcon
import java.awt.*;//associated with the image object
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;//allows implementation of the image object

public class Frame1 extends JFrame implements WindowListener {
    //added WindowListener to check if window deactivated. If so, I end the game in the Level appropriate level class
    //see *startUI method for the actionlistener, see *windowDeactivated method for the setter I used to end the game
    //I did this to prevent point tracking errors. When you close the Frame, without a listener the game would keep going invisibly.

    private Image image;
    private int w;
    private int h;

    private void loadImage() {
        //this method creates an icon image from the image filename string passed in, and then it
        //sets that icon to the Image variable that will hold the image object.

        ImageIcon ii = new ImageIcon("RunningSprite1.png");
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    public Frame1() {
        startUI();

    }

    private void startUI() {
        add(new Level1());
        setResizable(false);
        pack();
        setTitle("LakeSide");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Allows the program to keep running after you exit this frame.
        loadImage();
        setIconImage(image);//I added cool icons to the frames for game branding!
        // I had a hunch that a setIcon method was available so I typed in s and scrolled and it was there.
        addWindowListener(this);
    }

    public void windowOpened(WindowEvent e){}
    public void windowClosing(WindowEvent e){}
    public void windowClosed(WindowEvent e){}

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        Level1.setIngame();//accesses the ingame variable for the level class specified.
    }



}
