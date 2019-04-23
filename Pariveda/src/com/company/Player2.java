package com.company;

import java.awt.event.KeyEvent;

public class Player2 extends ObjectBlueprint {

    private int dx;
    private int dy;
    private String imageName = "RunningSprite3-1.png";
    public static boolean imageSwitch = true;

    public Player2(int x, int y) {
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

    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            imageSwitchLeft();
            drawPlayerOnly();
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            imageSwitchRight();
            drawPlayerOnly();
        }

        if (key == KeyEvent.VK_UP) {//Up and down image choices were more complicated because there were four possible appropriate
            dy = -1;                //images that would be appropriate to display. It wasn't sufficient to set the image based
            if (imageName.equals("run1.png")){// on which key arrow was set. So the work around was to set the image name based
                imageSwitchRight();           //on what the image was previously!!! This was also a place where critical thinking
            } else if (imageName.equals("RunningSprite3-1.png")){//yielded the solution.
                imageSwitchRight();
            }
            if (imageName.equals("run2left.png")){
                imageSwitchLeft();
            } else if (imageName.equals("RunningSprite3-1left.png")){
                imageSwitchLeft();
            }
            drawPlayerOnly();
        }

        if (key == KeyEvent.VK_DOWN) {//same as above
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

    public static void setImageSwitch2(){
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
