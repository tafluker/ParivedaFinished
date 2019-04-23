package com.company;

import javax.swing.*;
import java.awt.*;

public class ObjectBlueprint4 {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    public ObjectBlueprint4(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    protected void getImageDimensions() {

        width = 20;
        height = 20;
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x + 12, y + 15, 16, 20);
    }
    //The values of the rectangle object needed to be altered starting at level four because the rectangles were too big.
    //Objects were destroying the player without touching it. So, I printlined the width of the actual player image. It
    // was 40 pixels. I made the width of the rectangle a little less than half of the actual image, 16p.
    //But that wasn't enough because the right side of the player was less responsive to contact with objects, but the left wasn't.
    //Turns out that the x value of the rectangle begins at the left side of the image. So I added 12 to it to place it right in the
    //middle of the player, making both sides of the image equally responsive to contact. 12+16+12 = 40. Critical thinking problem solved.
    //Similar math was done for the height.
}
