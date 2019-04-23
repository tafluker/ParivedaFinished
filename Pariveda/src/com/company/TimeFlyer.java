package com.company;
//READ THIS
//The timeflyer is simply an image moving across the screen. By manipulating variables as the image moves across the screen
//at predictable intervals, I am able to create animations based on the time it takes to reach each interval.
//It's like using a timer, but less complicated.
//I didn't learn anything about the technique on any tutorial
//It's a completely original solution to solving the animation issues of alternating images to simulate running,
//setting a reasonable count speed to points, and raising the line on levels 4 and 5.


import static com.company.Level4.setBottom;//Raises the line drawn at the bottom of level 4
import static com.company.Level4.setScore;//Increases the score on level 4
import static com.company.Level5.setLow;//Raises the line drawn at the bottom of level 5
import static com.company.Level5.setScore5;//Increases the score on level 5
import static com.company.Player4.setBott;//Traces the lines on level 4 and 5 by actually
import static com.company.Player.setImageSwitch;//Changes the boolean value in the player classes that alternates between the two
import static com.company.Player2.setImageSwitch2;//different image options displayed while running left and running right.
import static com.company.Player4.setImageSwitch4;//^^^^^^^^^^^^^^^

public class TimeFlyer extends ObjectBlueprint {//Parent class of all objects

    private final int INITIAL_X = 400;//Sets the initial position of the Timeflyer image to the right of the board
    private int vary = 370;//I begin to access the timeflyer when it reaches pixel x value 370.
    private boolean changeDirection = true;
    private int up = 0;//Level 4 and 5. I start the score at 0.
    private int halfSpeed = 295;//Level 4 and 5. I start the position of the line at y position 295.
    private int bottTrace = 255;//Level 4 and 5. The Player's y position values actually differ from the Screen's and y position values.
    //For instance, the bottom of the screen is at y 295, but if the player is AT the bottom of the screen, he is at 255.
    //Hence, the restriction on the player's movement traces the line, but the player's restriction is NOT the line itself. An illusion.

    public TimeFlyer(int x, int y) {//initialized on the boards
        super(x, y);

        initPlayer();
    }

    private void initPlayer() {

        loadImage("runningRight.png");//inherited from the ObjectBluePrint class. I pass in the file image.
        getImageDimensions();//Inherited
    }

    public void move() {
        if (x < 0) { //Sets the motion of the flyer in a loop. When it hits left side of board, returns to the right
            x = INITIAL_X;
        }
        x -= 1;//Moves the flyer to the left.

        vary--;//Decrements the value that will be operated on.
        if (vary % 20 == 0){ //We check that integer value with modulus.
            moveY();
            setImageSwitch();//This code changes the boolean value that chooses between the running images
            setImageSwitch2();//for the player. The boolean value changes every time the pixel values the timeflyer
            setImageSwitch4();//crosses is divisible by 20.
            up++;
            setScore(up);//Level 4 and 5.
            setScore5(up);
        }
        if (vary % 60 == 0){//The speed of the redline should be slower than the amount of time it takes  to increase the score
            halfSpeed--;
            bottTrace--;
            setBottom(halfSpeed);//Level 4 and 5. setBottom is the redline
            setBott(bottTrace);//Level 4 and 5. bottTrace is the lowest the player can go.
            setLow(halfSpeed);
        }

        /*if (changeDirection){
            imageSwitch = true;
        } else {
            imageSwitch = false;
        }*/

    }

    public void moveY(){
        changeDirection = !changeDirection;
    }
}
