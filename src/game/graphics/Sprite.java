package game.graphics;
import utilities.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Sprite {
    private BufferedImage largeImg;
    private BufferedImage[] sprites;
    private BufferedImage currSprite;
    private int spriteIndex = 6;
    // 0 - up, 2-down, 4-left, 6-right

    public Sprite(String name) {
        try {
            largeImg = ImageIO.read(new File("res/" + name + ".png"));
            sprites = split(largeImg);
        } catch (Exception e) {
            System.out.println("Sprite could not be initialized");
        }
        if(sprites.length > 0) {
            currSprite = sprites[spriteIndex];
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
                    System.out.println("Subimage failure");
                }
                count++;
            }
        }
        return ret;
    }

    public void display(Graphics2D g2) {
        g2.drawImage(sprites[spriteIndex], 400, 400, 32, 32, null);
    }

    public void turnSprite(boolean turnRight) {
        if(turnRight) {
            if(spriteIndex-4 < 0) {
                spriteIndex = 4;
            }
            if(spriteIndex < -2) {
                spriteIndex = 6;
            }else {
                spriteIndex -= 4;
            }
            System.out.println(spriteIndex);
        }
    }
}
