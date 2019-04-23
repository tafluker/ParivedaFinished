package com.company;

import java.util.Random;

public class PizzaPeople5 extends ObjectBlueprint {
    Random rand = new Random();

    private final int INITIAL_Y = 0;
    private int vary = 370;
    private boolean changeDirection = true;



    public PizzaPeople5(int x, int y) {
        super(x, y);

        initPizza();
    }

    private void initPizza() {

        loadImage("pizzaperson 2_26p.png");//Winged pizza picture.
        getImageDimensions();
    }

    public void move() {

            if (y > 300) {
                y = INITIAL_Y;
                x = rand.nextInt(387);
            }

            y += 1;

        vary--;
        if (vary % 50 == 0){
            moveX();
        }
        if (changeDirection){//This code moves the objects on level 5 left and right based on same logic as the timeflyer class
            x++;
        } else {
            x--;
        }
    }
    public void moveX(){
        changeDirection = !changeDirection;
    }
}

