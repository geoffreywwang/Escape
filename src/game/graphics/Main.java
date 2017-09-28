package game.graphics;
import utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;

public class Main extends JPanel {
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.drawRect(0, 0, 800, 800);

        s.display(g2);
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