package com.company;

import java.util.Random;

public class Broccoli3 extends ObjectBlueprint {// see comments for pizza object.
    Random rand = new Random();

    private final int INITIAL_Y = 0;

    public Broccoli3(int x, int y) {
        super(x, y);

        initBroccoli();
    }

    private void initBroccoli() {

        loadImage("broccoli_21pA.png");
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

