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
    private int spriteIndex = 0;
    private Vec2d position;
    // 0,1-up, 2,3-down, 4,5-left, 6,7-right

    public Sprite(String name) {
        position = new Vec2d(400, 400);
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
        g2.drawImage(sprites[spriteIndex], position.col, position.row, 32, 32, null);
    }

    public void turnSprite(boolean turnRight) {
        if(turnRight) {
            if (spriteIndex == 0 || spriteIndex == 1) {
                spriteIndex = 6;
            } else if (spriteIndex == 2 || spriteIndex == 3) {
                spriteIndex = 4;
            } else if (spriteIndex == 4 || spriteIndex == 5) {
                spriteIndex = 0;
            } else if (spriteIndex == 6 || spriteIndex == 7) {
                spriteIndex = 2;
            }
        }else {
            if (spriteIndex == 0 || spriteIndex == 1) {
                spriteIndex = 4;
            } else if (spriteIndex == 2 || spriteIndex == 3) {
                spriteIndex = 6;
            } else if (spriteIndex == 4 || spriteIndex == 5) {
                spriteIndex = 2;
            } else if (spriteIndex == 6 || spriteIndex == 7) {
                spriteIndex = 0;
            }
        }
    }

    // USES SPRITE INDEX
    public void move() {
        if(spriteIndex == 0) {
            position.row -= 32;
            spriteIndex = 1;
        }else if(spriteIndex == 2) {
            position.row += 32;
            spriteIndex = 3;
        }else if(spriteIndex == 4) {
            position.col -= 32;
            spriteIndex = 5;
        }else if(spriteIndex == 6) {
            position.col += 32;
            spriteIndex = 7;
        }else if(spriteIndex == 1) {
            position.row -= 32;
            spriteIndex = 0;
        }else if(spriteIndex == 3) {
            position.row += 32;
            spriteIndex = 2;
        }else if(spriteIndex == 5) {
            position.col -= 32;
            spriteIndex = 4;
        }else if(spriteIndex == 7) {
            position.col += 32;
            spriteIndex = 6;
        }
    }
}
