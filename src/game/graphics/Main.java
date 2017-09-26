package game.graphics;
import javax.swing.*;
import java.awt.*;

/**
 * Created by michael_hopps on 9/29/16.
 */
public class Main extends JPanel{

    public Main(){

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;



    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Title!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 800, 800); //(x, y, w, h)
        Main panel = new Main();
        panel.setFocusable(true);
        panel.grabFocus();
        window.add(panel);
        window.setVisible(true);
    }
}