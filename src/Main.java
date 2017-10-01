import com.sun.corba.se.impl.orb.ParserTable;
import game.graphics.Pen;
import game.graphics.Sprite;
import game.logic.*;
import game.logic.Action;
import javafx.print.PageLayout;
import utilities.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static game.graphics.Pen.BOARDER_TILE_COUNT;
import static game.graphics.Pen.OFFSET;
import static game.graphics.Pen.TILE_SIZE;
import static game.logic.Map.REVEAL_MAP;

public class Main extends JPanel {
    public static Vec2d MAP_SIZE = new Vec2d(10,9);

    private Timer globalTimer, clockTimer;
    private int frameIndex;
    private static int buttonScore = 0;
    public int score = 0;
    private boolean scoreTimer = true;
    private World world;
    private Mission currentMission;
    private boolean hasWon = false, gameStarted = false;
    private int timeElapsed = 0;

    public Main(){
        //Game
        world = new World(new Map(MAP_SIZE.col,MAP_SIZE.row));
        currentMission = null;

        clockTimer = new Timer(1000, e -> {
            timeElapsed ++;
        });


        //Graphics
        frameIndex = 1;
        globalTimer = new Timer(1000 / 40 , e -> {

            if(frameIndex % 10 == 0) {
                if(currentMission != null){
                    currentMission.getUnit().getSprite().animate();

                    if(Objects.equals(currentMission.getUnit().getName(), "champ") && currentMission.getUnit().getCurrentPos().row == world.getMap().getStopTile().row &&currentMission.getUnit().getCurrentPos().col == world.getMap().getStopTile().col){
                        hasWon = true;
                        clockTimer.stop();
                    }
//                    currentMission.getUnit().getCurrentPos().row == world.getMap().getStopTile().row
//                            && currentMission.getUnit().getCurrentPos().col == world.getMap().getStopTile().col

                    if(!hasWon){
                        if(currentMission.getActions().size() > 0){
                            currentMission = world.getMap().updateNew(currentMission);
                        }else{
                            currentMission = null;
                        }
                    }else{
                        currentMission.getUnit().turn(true);
                    }

                }
            }

            repaint();
            if(frameIndex == 40){
                frameIndex = 1;
            }else{
                frameIndex ++;
            }
        });
        globalTimer.start();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'd') {
                    currentMission.getUnit().getSprite().turnSprite(true);
                    repaint();
                }else if(e.getKeyChar() == 'a') {
                    currentMission.getUnit().getSprite().turnSprite(false);
                    repaint();
                }else if(e.getKeyCode() == e.VK_ESCAPE) {
                    System.exit(0);
                }else if(e.getKeyChar() == 'w') {
                    currentMission.getUnit().getSprite().move();
                    repaint();
                }else if(e.getKeyChar() == e.VK_ENTER){
                    currentMission = Interpreter.parseScript("Unit unit = new Unit();\nunit.move(5);\nunit.turn(right);", new Vec2d(world.getMap().getStartTile().row,world.getMap().getStartTile().col));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        String[] options = {"Insane", "Expert", "Hard", "Medium", "Easy"};
        int option = JOptionPane.showOptionDialog(null, "Easy - 6x5 (No Clouds)\nMedium - 8x7 (No Clouds)\nHard - 6x5 (With Clouds)\nExpert - 8x7 (With Clouds)\nInsane - 24x20 (With Clouds)",
                "Escape - Haydn, Kanming, Andy, and Geoffrey",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (option  == 0 ){
            MAP_SIZE = new Vec2d(24,20);
            TILE_SIZE = 32;
        } else if(option == 1){
            MAP_SIZE = new Vec2d(8,7);
            TILE_SIZE = 64;
        }else if(option == 2){
            MAP_SIZE = new Vec2d(6,5);
            TILE_SIZE = 72;
        }else if(option == 3){
            MAP_SIZE = new Vec2d(8,7);
            REVEAL_MAP = true;
            TILE_SIZE = 64;
        }else{ // Easy
            MAP_SIZE = new Vec2d(6,5);
            REVEAL_MAP = true;
            TILE_SIZE = 72;
        }
        OFFSET = BOARDER_TILE_COUNT * TILE_SIZE;


        JFrame window = new JFrame("Escape");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(OFFSET * 2 + MAP_SIZE.col * TILE_SIZE,OFFSET * 2 + MAP_SIZE.row * TILE_SIZE); //(x, y, w, h)

        Main panel = new Main();
        panel.setFocusable(true);
        panel.grabFocus();
        window.add(panel);
        window.setVisible(true);

        JFrame textWindow = new JFrame("Write your script here...");
        textWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textWindow.setBounds(OFFSET * 2 + MAP_SIZE.col * TILE_SIZE,0,500,OFFSET * 2 + MAP_SIZE.row * TILE_SIZE); //(x, y, w, h)

        JTextArea textArea = new JTextArea("Unit unit = new Unit(normal/scout/champ);\n\nunit.move(3);\nunit.turn(right);");
        textArea.setFont(new Font("Roboto", Font.PLAIN, 18));

        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(500, OFFSET * 2 + MAP_SIZE.row * TILE_SIZE - 100));

        areaScrollPane.setFocusable(true);
        areaScrollPane.grabFocus();

        JLabel infoLabel = new JLabel("");
        infoLabel.setForeground(Color.RED);

        JButton runScriptButton = new JButton("Run Script");
        runScriptButton.setSize(100,50);
        runScriptButton.addActionListener(e -> {
            if(!panel.hasWon && !panel.gameStarted){
                panel.gameStarted = true;
                panel.clockTimer.start();
            }
            try {
                infoLabel.setText("");
                if(panel.currentMission == null) {
                    panel.currentMission = Interpreter.parseScript(textArea.getText(), new Vec2d(panel.world.getMap().getStartTile().col, panel.world.getMap().getStartTile().row));
                }
            }catch (Exception exception){
                infoLabel.setText("ERROR!!!!!!!!! Check your code and run the script again!");
            }
        });
        JButton revealButton = new JButton("Reveal Map");
        revealButton.setSize(100,50);
        revealButton.addActionListener(e -> {
            panel.world.getMap().reveal();
            setButtonScore(500);
        });

        JButton instructionsButton = new JButton("Instructions");
        instructionsButton.setSize(100,50);
        instructionsButton.addActionListener(e -> {
            JFrame instructionPanel = new JFrame("Instructions");
            instructionPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            instructionPanel.setSize(400,400);


            JTextArea instructionsText = new JTextArea("Unit unit = new Unit(normal/scout/champ);" +
                    "\nThis is character control." +
                    "\n\nPick normal scout or champ. " +
                    "\nNormal is the basic character," +
                    "\nwhich walks around revealing land." +
                    "\nScout reveals more than normal," +
                    "\nbut costs more." +
                    "\nChamp is the same as Normal," +
                    "\nbut you can only win with Champ." +
                    "\n\nunit.move(3); --> moving code" +
                    "\nunit.turn(right); --> turning code");
            instructionsText.setFont(new Font("Roboto", Font.PLAIN, 18));

            instructionPanel.add(instructionsText);

            instructionPanel.setVisible(true);

        });



        textWindow.setLayout(new FlowLayout(FlowLayout.CENTER));
        textWindow.add(areaScrollPane);
        textWindow.add(runScriptButton);
        textWindow.add(revealButton);
        textWindow.add(instructionsButton);
        textWindow.add(infoLabel);
        textWindow.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;



        Pen.drawMapWithBorder(world.getMap(),g2);
        if(currentMission != null && scoreTimer) {
            currentMission.getUnit().getSprite().display(g2);
            score += currentMission.getUnit().cost;
            scoreTimer = false;
        } else if (currentMission != null){
            currentMission.getUnit().getSprite().display(g2);
        } else {
            scoreTimer = true;
        }

        if (!hasWon) {
            g2.setFont(new Font("Roboto", Font.BOLD, 24));
            g2.setColor(Color.ORANGE);
//        g2.drawString(timeElapsed + " sec(s)", 10, OFFSET * 2 + MAP_SIZE.row * TILE_SIZE - 30);

            g2.drawString("score: " + (score + getButtonScore()), 10, OFFSET * 2 + MAP_SIZE.row * TILE_SIZE - 30);
        } else {
            g2.setFont(new Font("Roboto", Font.BOLD, 96));
            g2.setColor(Color.ORANGE);
            g2.drawString("YOU WIN!!!!", 100, 100);
            g2.setFont(new Font("Roboto", Font.BOLD, 50));
            g2.drawString("YOUR SCORE WAS " + (score + getButtonScore()), 100, OFFSET * 2 + MAP_SIZE.row * TILE_SIZE - 50);

        }



    }

    public static int getButtonScore() {
        return buttonScore;
    }

    public static void setButtonScore(int x) {
        buttonScore = x;
    }
}