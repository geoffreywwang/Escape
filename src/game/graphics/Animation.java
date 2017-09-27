package game.graphics;
import utilities.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Animation {
    private BufferedImage largeImg;
    private BufferedImage[] sprites;
    private BufferedImage currSprite;
    // 0 - up, 2-down, 4-left, 6-right

    public Animation(String name) {
        try {
            largeImg = ImageIO.read(new File("res/" + name + ".png"));
            sprites = split(largeImg);
        } catch (Exception e) {
            System.out.println("Animation could not be initialized");
        }
        if(sprites.length > 0) {
            currSprite = sprites[6];
        }
    }

    public BufferedImage[] split(BufferedImage img) {
        int count = 0;
        BufferedImage[] ret = new BufferedImage[8];
        for(int r = 0; r < 2; r++) {
            for(int c = 0; c < 4; c++) {
                try {
                    ret[count] = img.getSubimage(32 * r, 32 * c, 32, 32);
                }catch (Exception e) {
                    System.out.println("subimage sux");
                }
                count++;
            }
        }
        return ret;
    }

    public void display(Graphics2D g2) {
        g2.drawImage(currSprite, 400, 400, 32, 32, null);
    }
}
