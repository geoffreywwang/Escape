package game.graphics;
import game.logic.Map;
import utilities.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.File;

public class Main extends JPanel {
    static int constantx = 4;
    static int constanty = 4;
    Sprite s = new Sprite("minions");
    public Main(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'd') {
                    s.turnSprite(true);
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Andy Wang!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 800, 800); //(x, y, w, h)
        Main panel = new Main();
        panel.setFocusable(true);
        panel.grabFocus();
        window.add(panel);
        window.setVisible(true);

    }

    public static void drawMap(Graphics2D g2, Map.Tiles[][] map) {
        try {
            for (int row = 0; row < map.length; row++) {
                for (int col = 0; col < map[0].length; col++) {
                    switch (map[row][col]) {
                        case PATH:
                            drawSprite(g2, "singlePath", new Vec2d(row, col));
                            break;
                        case BLOCK:
                            drawSprite(g2, "singleTree", new Vec2d(row, col));
                            break;
                        case START:
                            drawSprite(g2, "singlePath", new Vec2d(row, col));
                            break;
                        case STOP:
                            drawSprite(g2, "singlePath", new Vec2d(row, col));
                            break;
                        case UNKNOWN:
                            drawSprite(g2, "singleFlow", new Vec2d(row, col));
                            break;
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("no map");
        }

    }

    public static void drawSprite(Graphics2D g2, String name, Vec2d loc) {

        loc.row = loc.row * 32 + constantx;
        loc.row = loc.row * 32 + constanty;
        BufferedImage sprite;
        try {
            sprite = ImageIO.read(new File("res/" + name + ".png"));
            g2.drawImage(sprite, loc.col, loc.row, 32, 32, null);
        } catch (Exception e) {
            System.out.println("File Not Found Loser");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.drawRect(0, 0, 800, 800);
        Map map = new Map(10, 10);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                map.viewableMapSet(new Vec2d(row, col));
            }

        }


        map.print(map.getViewableMapArray());
        drawMap(g2, map.getViewableMapArray());
        s.display(g2);
    }


}