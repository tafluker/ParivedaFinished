package com.company;

import javax.swing.*;
import java.awt.*;

public class ObjectBlueprint {

    protected int x;//tracks the upper left corner of the image file
    protected int y;//same as ^
    protected int width;//Needed to wrapper colliding object in rectangles to check if they intersect.
    protected int height;
    protected boolean visible;
    protected Image image;//Image object will hold the image file and allow it to be drawn by the graphics object in the board class

    public ObjectBlueprint(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    protected void getImageDimensions() {

        width = image.getWidth(null);//the observer gives as much information as is currently available about the image object
                                             //not necessary, we just want the height and width of the image file
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);//Cool class that converts image files into objects.
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
        return new Rectangle(x, y, width, height);
    }
    //Rectangle class is used to track image locations. The values passed into it are the image's.
    //Imagine the rectangle as a list of pixel locations that are turned on and form a square. As object move around, if
    //they share a common pixel location, that means they intersect. There's some cool math around this.
}
