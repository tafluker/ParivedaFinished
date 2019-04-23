package com.company;

import java.util.Random;

public class PizzaPeople4 extends ObjectBlueprint {
    Random rand = new Random();

    private final int INITIAL_Y = 0;

    public PizzaPeople4(int x, int y) {
        super(x, y);

        initPizza();
    }

    private void initPizza() {

        loadImage("pizzaPerson_23p.png");//See comments for pizza object. Only thing different is the picture.
        getImageDimensions();
    }

    public void move() {

            if (y > 300) {
                y = INITIAL_Y;
                x = rand.nextInt(387);
            }

            y += 1;


    }
}

