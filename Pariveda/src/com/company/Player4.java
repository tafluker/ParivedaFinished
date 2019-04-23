package com.company;

import java.awt.event.KeyEvent;

public class Player4 extends ObjectBlueprint4 {

    private int dx;
    private int dy;
    private String imageName = "RunningSprite3-1.png";
    public static boolean imageSwitch = true;//See Player class
    public static int bott = 255;//The initial y value the player cannot go beneath. it is decremented in the timeflyer class.

    public static void setBott(int b){bott = b;}//Used by the timeflyer. The timeflyer increments this to raised the low boundary.

    public Player4(int x, int y) {
        super(x, y);

        initPlayer();
    }

    private void initPlayer() {
        loadImage(imageName);
        getImageDimensions();
    }

    private void drawPlayerOnly(){
        loadImage(imageName);
        getImageDimensions();
    }

    public void move() {

        x += dx;
        y += dy;
        if (x > 360){ x=360; }//Setting the bound of the player to the side of the frame. No escaping.
        if (x<0){x=0;}     //Check to see if the player reached a side, if so, set the position to the side.
        if (y>255){y = 255;}
        if (y> bott){ y = bott;}//dynamic value that changes to raise the lowest point the player can travel to
                                //this value traces the value of the red lines on level 4 and 5.

    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {//See player 1 for comments
            dx = -1;
            imageSwitchLeft();
            drawPlayerOnly();
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            imageSwitchRight();
            drawPlayerOnly();
        }

        if (key == KeyEvent.VK_UP) {//See player two for comments
            dy = -1;
            if (imageName.equals("run1.png")){
                imageSwitchRight();
            } else if (imageName.equals("RunningSprite3-1.png")){
                imageSwitchRight();
            }
            if (imageName.equals("run2left.png")){
                imageSwitchLeft();
            } else if (imageName.equals("RunningSprite3-1left.png")){
                imageSwitchLeft();
            }
            drawPlayerOnly();
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
            if (imageName.equals("run1.png")){
                imageSwitchRight();
            } else if (imageName.equals("RunningSprite3-1.png")){
                imageSwitchRight();
            }
            if (imageName.equals("run2left.png")){
                imageSwitchLeft();
            } else if (imageName.equals("RunningSprite3-1left.png")){
                imageSwitchLeft();
            }
            drawPlayerOnly();
        }
    }

    public static void setImageSwitch4(){
        imageSwitch = !imageSwitch;
    }

    public void imageSwitchRight(){
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

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
