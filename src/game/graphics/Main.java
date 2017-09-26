package game.graphics;
import utilities.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.*;

public class Main extends JPanel {
    public Main(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void drawSprite(Graphics2D g2, String name, Vec2d loc) {
        BufferedImage sprite;
        try {
            sprite = ImageIO.read(new File("res/" + name + ".png"));
            g2.drawImage(sprite, loc.x, loc.y, 32, 32, null);
        }catch(Exception e) {
            System.out.println("File Not Found Loser");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.drawRect(0, 0, 800, 800);

        drawSprite(g2, "singleTree", new Vec2d(400, 400));
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
}