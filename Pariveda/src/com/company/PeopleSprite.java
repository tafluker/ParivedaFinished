package com.company;
import java.util.Random;

public class PeopleSprite extends ObjectBlueprint {//Randomly allocated pictures for peopleSprites
    Random rand = new Random();

    public PeopleSprite(int x, int y) {
        super (x,y);

        initSprites();
    }

    private void initSprites(){
        randImage();
        getImageDimensions();
    }

    private void randImage() {
        int pic = rand.nextInt(4);

        switch(pic){

            case 0:
                loadImage("redshirt.png");
                break;
            case 1:
                loadImage("burgundyshirt1.png");
                break;
            case 2:
                loadImage("blueshirt.png");
                break;
            case 3:
                loadImage("brownshirt.png");
                break;

        }
    }
}
