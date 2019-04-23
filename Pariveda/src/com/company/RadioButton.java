

//*Run the program with the green play button on the left side on line #18.
//DO NOT close frames before a game ends because it affects point calculation. (I gave this instruction, but then I was like
// why don't I just fix it? So I did by finding out there's a window event listener.)






package com.company;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;//Associated with the colorIUresource that decorated the JOptionPane
import java.awt.*;//Image object, colors, and eventqueue
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RadioButton extends JFrame implements ActionListener {

    private static int level = 1;

    public static void setLevel(int levelSet) {
        level = levelSet;
    }//Keeps track of level access. Set inside the level classes

    public static int getLevel() {
        return level;
    }//returns the current level to make access granting decisions.

    private Image image;
    private int w;
    private int h;

    private void loadImage() {

        ImageIcon ii = new ImageIcon("runningSprite1.png");//sets the image on the radiobutton
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }



    JRadioButton rb1, rb2, rb3, rb4, rb5;//5 level buttons
    JButton b, c;//2 bottom buttons

    RadioButton() {
        rb1 = new JRadioButton("Level 1");
        rb1.setBounds(10, 20, 80, 30);//Tedious
        rb2 = new JRadioButton("Level 2");
        rb2.setBounds(100, 20, 80, 30);
        rb3 = new JRadioButton("Level 3");
        rb3.setBounds(200, 20, 80, 30);
        rb4 = new JRadioButton("Level 4");
        rb4.setBounds(60, 80, 80, 30);
        rb5 = new JRadioButton("Level 5");
        rb5.setBounds(160, 80, 80, 30);

        ButtonGroup bg = new ButtonGroup();//By putting the 5 buttons in a button group, you can make them mutually exclusive.
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);
        bg.add(rb4);
        bg.add(rb5);

        b = new JButton("PLAY");
        b.setBounds(100, 140, 80, 30);//I had to do this position math myself. Tedious.
        b.addActionListener(this);
        b.setBackground(Color.gray);
        b.setForeground(Color.white);
        c = new JButton("LEVEL OVERRIDE");
        c.setBackground(Color.gray);//custom coloring
        c.setForeground(Color.white);
        c.setBounds(60, 180, 160,30);
        c.addActionListener(this);
        add(rb1);
        add(rb2);
        add(rb3);
        add(rb4);
        add(rb5);

        add(b);
        add(c);
        setSize(300, 280);
        setTitle("LakeSide Pizza People");
        setLocation(180,220);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//This is the only thing that can close the whole program.


        loadImage();
        setIconImage(image);
    }



    public void actionPerformed(ActionEvent e) {

        UIManager.put("OptionPane.background", new ColorUIResource(Color.gray));//Same thing from capstone friday
        UIManager.put("Panel.background", new ColorUIResource(Color.gray));
        UIManager.put("OptionPane.messageForeground", Color.white);


        if (e.getSource()==c)//allows discrimination between the two action event sources (the buttongroup and the override button
        {
            setLevel(10);//This is what the level override does
            ImageIcon y = new ImageIcon("runningSprite1.png");//Where I set the JOptionPane image icon
            JOptionPane.showMessageDialog(null, "You have overridden level restrictions and may\n" +
                    "               now play any level you like.", "LakeSide", JOptionPane.INFORMATION_MESSAGE, y);
        }

        if (e.getSource()==b) {
            if (rb1.isSelected()) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Don't let the Lakeside Pizza hit the students\n" +
                                "  Move with the left and right arrow keys\n" +
                                "    Each student saved is worth a point\n" +
                                "          Earn 4 points to reach level 2\n" +
                                "    Close frame only after a game ends\n"+
                                "        Exit to return to the main menu", "Level 1 Instructions", JOptionPane.DEFAULT_OPTION);
                if (input == 0) {
                    EventQueue.invokeLater(() -> {//this code I learned from a tutorial, means show frame after other objects are painted
                        Frame1 ex1 = new Frame1();
                        ex1.setVisible(true);
                    });
                }
            }
            if (rb2.isSelected()) {

                if (getLevel() >= 2) {
                    int input = JOptionPane.showConfirmDialog(null,//Can't easily add images to showConfirmDialogs
                            "Students have scattered and are harder to save\n" +
                                    "      You can now move all four arrow keys\n" +
                                    "              Earn 5 points to reach level 3\n" +
                                    "        Close frame only after a game ends\n"+
                                    "            Exit to return to the main menu", "Level 2 Instructions", JOptionPane.DEFAULT_OPTION);
                    if (input == 0) {//because I used show confirm dialogs, I can access the int value to see if
                        EventQueue.invokeLater(() -> {// ok was clicked, if it was, I start the game.
                            Frame2 ex2 = new Frame2();
                            ex2.setVisible(true);
                        });
                    }
                } else {//My message when access is denied
                    ImageIcon i = new ImageIcon("RunningSprite1.png");
                    JOptionPane.showMessageDialog(null, "You must pass level 1 to enter level 2", "LakeSide", JOptionPane.INFORMATION_MESSAGE, i);
                    //showMessage dialogs don't return number values. If they did, I'd have used these before my levels so I could
                    //use icons consisently on all panes.
                }
            }
            if (rb3.isSelected()) {
                if (getLevel() >= 3) {
                    int input = JOptionPane.showConfirmDialog(null,
                            "Students have scattered and are harder to save\n" +
                                    "        The falling broccoli can save everyone\n" +
                                    "               Earn 10 points to reach level 4\n" +
                                    "          Close frame only after a game ends\n"+
                                    "              Exit to return to the main menu", "Level 3 Instructions", JOptionPane.DEFAULT_OPTION);
                    if (input == 0) {
                        EventQueue.invokeLater(() -> {
                            Frame3 ex3 = new Frame3();
                            ex3.setVisible(true);
                        });
                    }
                } else {
                    ImageIcon a = new ImageIcon("broccoli_21pA.png");
                    JOptionPane.showMessageDialog(null, "You must pass level 2 to enter level 3", "LakeSide", JOptionPane.INFORMATION_MESSAGE, a);
                }
            }
            if (rb4.isSelected()) {
                if (getLevel() >= 4) {
                    int input = JOptionPane.showConfirmDialog(null,
                            "    Many students have become Pizza Monsters\n" +
                                    "           Escape their grasp as long as you can\n" +
                                    "                    Earn 200 points to survive\n" +
                                    "            Close frame only after a game ends\n"+
                                    "                Exit to return to the main menu", "Level 4 Instructions", JOptionPane.DEFAULT_OPTION);
                    if (input == 0) {
                        EventQueue.invokeLater(() -> {
                            Frame4 ex4 = new Frame4();
                            ex4.setVisible(true);
                        });
                    }
                } else {
                    ImageIcon o = new ImageIcon("pizzaPerson_23p.png");
                    JOptionPane.showMessageDialog(null, "You must pass level 3 to enter level 4", "LakeSide", JOptionPane.INFORMATION_MESSAGE, o);
                }
            }
            if (rb5.isSelected()) {
                if (getLevel() >= 5) {
                    int input = JOptionPane.showConfirmDialog(null,
                            " You escaped but now Winged Pizzas are after you\n" +
                                    "           Escape their grasp as long as you can\n" +
                                    "                    Earn 110 points to survive\n" +
                                    "            Close frame only after a game ends\n"+
                                    "                Exit to return to the main menu", "Level 5 Instructions", JOptionPane.DEFAULT_OPTION);
                    if (input == 0) {
                        EventQueue.invokeLater(() -> {
                            Frame5 ex5 = new Frame5();
                            ex5.setVisible(true);
                        });
                    }
                } else {
                    ImageIcon u = new ImageIcon("pizzaperson 2_26p.png");
                    JOptionPane.showMessageDialog(null, "You must pass level 4 to enter level 5", "LakeSide", JOptionPane.INFORMATION_MESSAGE, u);
                }
            }
        }
    }
}
