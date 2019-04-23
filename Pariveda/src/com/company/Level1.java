package com.company;


import javax.swing.*;//JPanel and timer
import java.awt.*;//Graphics class object used to draw the images that are visible on the frame.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;//Key adapter allows you to access the key event methods without importing unnecessary methods
import java.awt.event.KeyEvent;
import java.util.ArrayList;//Arraylist allows easier manipulation of objects than an array with remove and get methods.
import java.util.List;
import java.util.Random;

public class Level1 extends JPanel implements ActionListener {
    Random rand = new Random();

    private Timer timer;
    private Player player1;
    private List<Pizza> slices;//All the pizza that falls
    private static boolean ingame;
    private final int PLAYER_X = 40;//where the player starts
    private final int PLAYER_Y = 210;
    private final int B_WIDTH = 400;//board with not totally accurate hence the use of the number 387
    private final int B_HEIGHT = 300;
    private final int DELAY = 12;//milliseconds
    private List<PeopleSprite> people;// the people
    private static int score = 10;//starts at ten to represent ten players and is decremented
    private TimeFlyer timeCraft;

    public static void setScore(int sco){
        score = sco;
    }

    private final int[][] peoplePos ={{35,252},{70,252},{105,252},{140,252},{175,252},{210,252},{245,252},{280,252},{315,252},{350,252}};
    //positions of the 10 people in the game

    private final int[][] pos = {//random positions the pizza's fall from. Notice the random y in the y pair starts at -1600,
            //this means even when they aren't on the screen they are falling and just arrive at different times.
            //the x in the pairs allows controls where the pizza falls from to the left or right of the screen.
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600}
    };

    public Level1() {

        score = 10;
        initBoard();
    }

    public static void setIngame() {//This method is used by the frame class. If the frame is deactivated I shut the game off.
        ingame = false;             //Otherwise, you could exit a frame and the game would keep going invisibly.
                                    //This caused point errors in the past before I found the windowlistener.
    }

    public int getScore(){
        return score;
    }

    private void initBoard() {

        addKeyListener(new TAdapter());//This listener is a like a lightweight key adapter. The heavy lifting is done in the object classes
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));


        ingame = true;
        player1 = new Player(PLAYER_X, PLAYER_Y);//initialize the x to the constants
        timeCraft = new TimeFlyer(400,-50);//set the timeflyer in motion to time animations and put it out of view.
        initPizzas();
        initPeoples();//initialize the list arrays

        timer = new Timer(DELAY, this);//The timer will tell the graphics object when to paint
        timer.start();//the listener sends out the action event every 15 milliseconds and the graphic g draws the objects
    }

    public void initPizzas() {
        slices = new ArrayList<>();

        for (int[] p : pos) {
            slices.add(new Pizza(p[0], p[1]));//give the pizza's x and y values according to the numbers in the 2d int array.
        }
    }

    public void initPeoples(){
        people = new ArrayList<>();

        for (int[] j : peoplePos) {
            people.add(new PeopleSprite(j[0], j[1]));//Same as the pizzas
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//Paint component super class should be called for everything else,
                                // only two other requests need to be made.

        if (ingame) {//if we are in the game the graphics object with draw all of our sprites, if not, it'll draw the
                     //game over message
            drawObjects(g);

        } else {

            drawGameOver(g);
        }

    }

    private void drawObjects(Graphics g) {//Tell the graphics object to draw each image object that is set to visible currently
                                            // on the screen.

        if (player1.isVisible()) {
            g.drawImage(player1.getImage(), player1.getX(), player1.getY(),
                    this);//The graphics object needs the x, the y, and the image itself, and the observer passes
                                    //all the info currently available about it to the object.
        }

        if (timeCraft.isVisible()) {
            g.drawImage(timeCraft.getImage(), timeCraft.getX(), timeCraft.getY(), this);
        }

        for (Pizza pizza : slices) {
            if (pizza.isVisible()) {
                g.drawImage(pizza.getImage(), pizza.getX(), pizza.getY(), this);
            }
        }

        for (PeopleSprite person :people){
            if (person.isVisible()){
                g.drawImage(person.getImage(), person.getX(), person.getY(), this);
            }
        }

    }

    private void drawGameOver(Graphics g) {//Graphics objects draw more than pictures

        String msg = "Game Over";
        String sco = "Your score is " + getScore();
        String win = "Help save students from LakeSide Pizza on Level 2";//Make strings
        String loss = "Try Again. Don't Let Your Classmates Become Pizza!";
        Font small = new Font("Helvetica", Font.BOLD, 14);//Set styling
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,//The parameters for the strings are listed
                B_HEIGHT / 2);
        g.drawString(sco, ((B_WIDTH - fm.stringWidth(sco)) / 2) ,(B_HEIGHT / 2) - 20);
        if (getScore() >= 4){//Depending on whether the user passed the level, draw the win or loss strings
            g.drawString(win,
                    ((B_WIDTH - fm.stringWidth(win)) / 2),(B_HEIGHT / 2) - 40);
        } else {
            g.drawString(loss,//This math says, set the x to the board size, minus the size of the screen / 2, so it'll be in the middle
                    ((B_WIDTH - fm.stringWidth(loss)) / 2),(B_HEIGHT / 2) - 40);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {//Times calls action listener and we check all the objects

        inGame();//are we still playing the game?

        updatePlayer();//move the object if it's still there
        updatePizzas();//move the object if it's still there
        updatePeoples();
        updateTimeCraft();

        checkCollisions();

        repaint();
    }

    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }

    private void updatePlayer() {

        if (player1.isVisible()) {

            player1.move();
        }
    }

    private void updateTimeCraft(){
        if (timeCraft.isVisible()) {
            timeCraft.move();
        }
    }

    private void updatePizzas() {

        if (slices.isEmpty()) {//If there's no pizza the game is over

            if (getScore() >= 4){//I check the score at the end of game. If the user has 4 points they may proceed to the next level
                RadioButton.setLevel(2);//The level is set inside the radio button.
            }

            ingame = false;

            return;
        }

        for (int i = 0; i < slices.size(); i++) {//I check each slice of pizza and remove it if not visible.

            Pizza a = slices.get(i);//loop through with a for each loop

            if (a.isVisible()) {
                a.move();
            } else {
                slices.remove(i);
            }
        }
    }

    private void updatePeoples(){//If there's no people the game is over
        if (people.isEmpty()) {

            ingame = false;
            return;
        }
        for (int i = 0; i < people.size(); i++) {//We loop through each person. If they aren't visible we remove them and -- the score.

            PeopleSprite b = people.get(i);

            if (!(b.isVisible())) {
                people.remove(i);
                score--;
            }
        }

    }

    public void checkCollisions() {

        Rectangle r3 = player1.getBounds();//Construct a rectangle for the player
                                            // from the rectangle defined in the object blueprint class
        for (Pizza slice : slices) {

            Rectangle r2 = slice.getBounds();//I made each pizza in the arraylist a rectangle using the method defined in the
                                            //blueprint class

            if (r3.intersects(r2)) {//if any rectangle of pizza intersects the player then set the pizza's visibility to false.

                slice.setVisible(false);
                if (slices.isEmpty()) {//If no pizzas are visible, they were removed in updatePizza, and the game is over.
                    ingame = false;
                }
            }
        }

        for (PeopleSprite person : people) {//Same as above but I say the game is over when there are no people left.

            Rectangle r1 = person.getBounds();

            for (Pizza slice : slices) {

                Rectangle r2 = slice.getBounds();

                if (r1.intersects(r2)) {

                    person.setVisible(false);

                    if(people.isEmpty()){
                        ingame = false;
                    }
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {//Key adapter inherits the key released and pressed code

        @Override
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
        }
    }
}

