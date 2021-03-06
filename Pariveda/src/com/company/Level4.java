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

public class Level4 extends JPanel implements ActionListener {//Lines 33, 123 comments on the raised boundary line
    Random rand = new Random();

    private Timer timer;
    private Player4 player4;
    private List<PizzaPeople4> army;
    private static boolean ingame;
    private final int PLAYER_X = 175;
    private final int PLAYER_Y = 110;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 12;
    private static int score = 0;
    private static int bottom = 295;//Sets the lines initial y value
    private TimeFlyer timeCraft;

    public static void setScore(int sco){
        score = sco;
    }
    public static void setBottom(int b){bottom = b;}//Allows access to the timeflyer to manipulate the line's value

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

    public Level4() {

        score = 0;
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

        player4 = new Player4(PLAYER_X, PLAYER_Y);
        timeCraft = new TimeFlyer(400,-50);

        initPizzaPeople();

        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void initPizzaPeople() {
        army = new ArrayList<>();

        for (int[] p : pos) {
            army.add(new PizzaPeople4(p[0], p[1]));
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

        if (player4.isVisible()) {
            g.drawImage(player4.getImage(), player4.getX(), player4.getY(),
                    this);
        }

        if (timeCraft.isVisible()) {
            g.drawImage(timeCraft.getImage(), timeCraft.getX(), timeCraft.getY(), this);
        }

        for (PizzaPeople4 p : army) {
            if (p.isVisible()) {
                g.drawImage(p.getImage(), p.getX(), p.getY(), this);
            }
        }
        g.setColor(Color.RED);
        g.drawRect(0,bottom, 400, 0);//Line is raised by setting a variable to it's y value.

        g.setColor(Color.WHITE);//
        g.drawString("Points: " + getScore(), 5, 15);
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        String sco = "Your score is " + getScore();
        String win = "Help save students from LakeSide Pizza on Level 5";
        String loss = "Try Again. Don't Let Your Classmates Become Pizza!";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        g.drawString(sco, ((B_WIDTH - fm.stringWidth(sco)) / 2) ,(B_HEIGHT / 2) - 20);
        if (getScore() >= 200){
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

        if (player4.isVisible()) {

            player4.move();
        }
    }

    private void updateTimeCraft(){
        if (timeCraft.isVisible()) {
            timeCraft.move();
        }
    }

    private void updatePizzas() {

        for (int i = 0; i < army.size(); i++) {

            PizzaPeople4 a = army.get(i);

            if (a.isVisible()) {
                a.move();
            } else {
                army.remove(i);
            }
        }
    }

    public void checkCollisions() {

        Rectangle r3 = player4.getBounds();

        for (PizzaPeople4 slice : army) {

            Rectangle r2 = slice.getBounds();

            if (r3.intersects(r2)) {

                player4.setVisible(false);
                if (getScore() >= 200){
                    RadioButton.setLevel(5);
                }

                ingame = false;
            }
        }
    }


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player4.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player4.keyPressed(e);
        }
    }
}
