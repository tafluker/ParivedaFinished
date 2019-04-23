package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level3 extends JPanel implements ActionListener {//A few new comments to see on this level. Broccoli and
                                                                //people reappearance comments. Lines 38, 243, 299.
    Random rand = new Random();

    private Timer timer;
    private Player2 player2;
    private List<Pizza> slices;
    private static boolean ingame;
    private final int PLAYER_X = 175;//Start the player higher
    private final int PLAYER_Y = 110;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 12;
    private List<PeopleSprite> people;
    private Broccoli3 broccoli;
    private static int score = 10;
    private TimeFlyer timeCraft;

    public static void setScore(int sco){
        score = sco;
    }

    private final int[][] peoplePos ={{35,202},{70,252},{105,182},{140,230},{175,160},{210,160},{245,252},{280,218},{315,252},{350,200}};

    private final int[] broccoliPos = {//Sets the position of a single broccoli.
            rand.nextInt(387), rand.nextInt(1000) - 1600//1000 - 1600 means it won't appear too early in the game.
                                                            //compare to 1500 - 1600. -600 broccoli vs. -100 for pizza
    };

    private final int[][] pos = {
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600},
            {rand.nextInt(387), rand.nextInt(1500) - 1600}, {rand.nextInt(387), rand.nextInt(1500) - 1600}
    };

    public Level3() {

        score = 10;
        initBoard();
    }

    public static void setIngame() {
        ingame = false;
    }

    public int getScore(){
        return score;
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        player2 = new Player2(PLAYER_X, PLAYER_Y);
        timeCraft = new TimeFlyer(400,-50);
        broccoli = new Broccoli3(broccoliPos[0], broccoliPos[1]);

        initPizzas();
        initPeoples();

        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void initPizzas() {
        slices = new ArrayList<>();

        for (int[] p : pos) {
            slices.add(new Pizza(p[0], p[1]));
        }
    }

    public void initPeoples(){
        people = new ArrayList<>();

        for (int[] j : peoplePos) {
            people.add(new PeopleSprite(j[0], j[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

    }

    private void drawObjects(Graphics g) {

        if (player2.isVisible()) {
            g.drawImage(player2.getImage(), player2.getX(), player2.getY(),
                    this);
        }

        if (broccoli.isVisible()){
            g.drawImage(broccoli.getImage(), broccoli.getX(), broccoli.getY(), this);
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

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        String sco = "Your score is " + getScore();
        String win = "Help save students from LakeSide Pizza on Level 4";
        String loss = "Try Again. Don't Let Your Classmates Become Pizza!";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        g.drawString(sco, ((B_WIDTH - fm.stringWidth(sco)) / 2) ,(B_HEIGHT / 2) - 20);
        if (getScore() >= 10){
            g.drawString(win,
                    ((B_WIDTH - fm.stringWidth(win)) / 2),(B_HEIGHT / 2) - 40);
        } else {
            g.drawString(loss,
                    ((B_WIDTH - fm.stringWidth(loss)) / 2),(B_HEIGHT / 2) - 40);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updatePlayer();
        updatePizzas();
        updatePeoples();
        updateTimeCraft();
        updateBroccoli();

        checkCollisions();

        repaint();
    }

    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }

    private void updateBroccoli(){
        if (broccoli.isVisible()){
            broccoli.move();
        }
    }

    private void updatePlayer() {

        if (player2.isVisible()) {

            player2.move();
        }
    }

    private void updateTimeCraft(){
        if (timeCraft.isVisible()) {
            timeCraft.move();
        }
    }

    private void updatePizzas() {

        if (slices.isEmpty()) {

            if (getScore() >= 10){
                RadioButton.setLevel(4);
            }

            ingame = false;

            return;
        }

        for (int i = 0; i < slices.size(); i++) {

            Pizza a = slices.get(i);

            if (a.isVisible()) {
                a.move();
            } else {
                slices.remove(i);
            }
        }
    }

    private void updatePeoples(){//People are not removed from the array on this level because I needed to make them
                                //reappear when the player catches the broccoli. So, I had to check if they were all invisible
                                //before ending the game. There was no simple method to do this.
        if (!people.get(0).isVisible() && !people.get(1).isVisible() && !people.get(2).isVisible() && !people.get(3).isVisible() &&
                !people.get(4).isVisible() && !people.get(5).isVisible() && !people.get(6).isVisible() && !people.get(7).isVisible() &&
                !people.get(8).isVisible() && !people.get(9).isVisible()) {

            ingame = false;
            return;
        }

    }

    public void checkCollisions() {

        Rectangle r3 = player2.getBounds();

        for (Pizza slice : slices) {

            Rectangle r2 = slice.getBounds();

            if (r3.intersects(r2)) {

                slice.setVisible(false);

                if (slices.isEmpty()) {

                    ingame = false;
                }

            }
        }

        for (PeopleSprite person : people) {

            Rectangle r1 = person.getBounds();

            for (Pizza slice : slices) {

                Rectangle r2 = slice.getBounds();

                if (r1.intersects(r2) && person.isVisible()) {
                    score--;
                    person.setVisible(false);
                }
                if (!people.get(0).isVisible() && !people.get(1).isVisible() && !people.get(2).isVisible() && !people.get(3).isVisible() &&
                        !people.get(4).isVisible() && !people.get(5).isVisible() && !people.get(6).isVisible() && !people.get(7).isVisible() &&
                        !people.get(8).isVisible() && !people.get(9).isVisible()) {

                    ingame = false;
                }
            }
        }

        Rectangle r4 = broccoli.getBounds();
        if(broccoli.isVisible()) {
            if (r4.intersects(r3)) {//If you intersect the broccoli I set all the people back to visible.
                people.get(0).setVisible(true);
                people.get(1).setVisible(true);
                people.get(2).setVisible(true);
                people.get(3).setVisible(true);
                people.get(4).setVisible(true);
                people.get(5).setVisible(true);
                people.get(6).setVisible(true);
                people.get(7).setVisible(true);
                people.get(8).setVisible(true);
                people.get(9).setVisible(true);
                setScore(10);
                broccoli.setVisible(false);
            }
        }
    }


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player2.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player2.keyPressed(e);
        }
    }
}
