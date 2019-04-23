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

public class Level2 extends JPanel implements ActionListener {//see Level 1 for applicable comments
    Random rand = new Random();

    private Timer timer;
    private Player2 player2;
    private List<Pizza> slices;
    private static boolean ingame;
    private final int PLAYER_X = 175;
    private final int PLAYER_Y = 110;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 12;
    private List<PeopleSprite> people;
    private static int score = 10;
    private TimeFlyer timeCraft;

    public static void setScore(int sco){
        score = sco;
    }

    private final int[][] peoplePos ={{35,202},{70,252},{105,182},{140,230},{175,160},{210,160},{245,252},{280,218},{315,252},{350,200}};


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

    public Level2() {

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
        String win = "Help save students from LakeSide Pizza on Level 3";
        String loss = "Try Again. Don't Let Your Classmates Become Pizza!";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        g.drawString(sco, ((B_WIDTH - fm.stringWidth(sco)) / 2) ,(B_HEIGHT / 2) - 20);
        if (getScore() >= 5){
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

        checkCollisions();

        repaint();
    }

    private void inGame() {

        if (!ingame) {
            timer.stop();
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

            if (getScore() >= 5){
                RadioButton.setLevel(3);
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

    private void updatePeoples(){
        if (people.isEmpty()) {

            ingame = false;
            return;
        }
        for (int i = 0; i < people.size(); i++) {

            PeopleSprite b = people.get(i);

            if (!(b.isVisible())) {
                people.remove(i);
                score--;
            }
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

                if (r1.intersects(r2)) {

                    person.setVisible(false);
                    if(people.isEmpty()){
                        ingame = false;
                    }
                }
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

