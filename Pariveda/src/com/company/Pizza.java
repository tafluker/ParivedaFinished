package com.company;

import java.util.Random;

public class Pizza extends ObjectBlueprint {
    Random rand = new Random();

    private final int INITIAL_Y = 0;//Falls from the top of the screen. This is overridden in the board though

    public Pizza(int x, int y) {
        super(x, y);

        initPizza();
    }

    private void initPizza() {

        loadImage("pizzaPic_16p.png");//This was the first thing I drew for the project.
        getImageDimensions();
    }

    public void move() {

            if (y > 300) {//if it has reached the bottom of the screen.
                y = INITIAL_Y;
                x = rand.nextInt(387);//Each time the pizza hits the bottom of the frame, we move it back to the top.
            }

            y += 1;//Moving down the screen a pixel at a time.
    }
}

