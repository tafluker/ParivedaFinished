package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Frame4 extends JFrame implements WindowListener {

    private Image image;
    private int w;
    private int h;

    private void loadImage() {

        ImageIcon ii = new ImageIcon("pizzaPerson_23p.png");
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    public Frame4() {
        startUI();
    }
    private void startUI() {
        add(new Level4());
        setResizable(false);
        pack();
        setTitle("LakeSide");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadImage();
        setIconImage(image);
        addWindowListener(this);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

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
        Level4.setIngame();
    }

}
