package com.company;

import java.awt.event.KeyEvent;

public class Player extends ObjectBlueprint {//Player class for level 1.

    private int dx;
    private int dy;
    private String imageName = "RunningSprite3-1.png";
    public static boolean imageSwitch = true;//value used to change the String to be used in imageName. See more in TimeFlyer class

    public Player(int x, int y) {
        super(x, y);

        initPlayer();
    }

    private void initPlayer() {//Pass a new image name to be placed inside an image icon object.
        loadImage(imageName);
        getImageDimensions();
    }

    private void drawPlayerOnly(){
        loadImage(imageName);
        getImageDimensions();
    }

    public void move() {//The x and y are continually updated by the values we get from the left and right key event.

        x += dx;
        y += dy;

    }

    public void keyPressed(KeyEvent e) {//Listens for a key event from the Level classes.

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {//if the key event is the left arrow key
            dx = -1;//move left
            imageSwitchLeft();//if we are moving left, alternate between left facing images
            drawPlayerOnly();
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;//move right
            imageSwitchRight();//the inverse of imageSwitchLeft
            drawPlayerOnly();
        }
    }

    public static void setImageSwitch(){
        imageSwitch = !imageSwitch;
    }

    public void imageSwitchRight(){//based on the boolean value set in the TimeFlyer, alternate images.
        if (imageSwitch){
            imageName = "run1.png";
        }else{
            imageName = "RunningSprite3-1.png";
        }
    }

    public void imageSwitchLeft(){
        if (imageSwitch){
            imageName = "run2left.png";
        }else{
            imageName = "RunningSprite3-1left.png";
        }
    }

    public void keyReleased(KeyEvent e) {//we pass no change to x on release of the arrow keys

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

    }
}
