package game.graphics;

import game.logic.Map;
import utilities.Vec2d;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by student on 9/28/17.
 */
public class Pen {
    public static final int BOARDER_TILE_COUNT = 2;
    public static int TILE_SIZE = 64;
    public static int OFFSET = BOARDER_TILE_COUNT * TILE_SIZE;

    public static void drawMap(Map map, Graphics2D g2, Vec2d offset){
        Map.Tiles[][] mapTiles = map.getViewableMapArray();

        for (int row = 0; row < mapTiles.length; row++) {
            for (int col = 0; col < mapTiles[row].length; col++) {
                switch (mapTiles[row][col]) {
                    case PATH:
                        drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                        break;
                    case BLOCK:
                        drawSprite(g2, "singleTree.png", TILE_SIZE, new Vec2d(col, row), offset);
                        break;
                    case START:
                        drawSprite(g2, "pathDirt.png", TILE_SIZE, new Vec2d(col, row), offset);
                        break;
                    case STOP:
                        drawSprite(g2, "singleFlower.png", TILE_SIZE, new Vec2d(col, row), offset);
                        break;
                    case UNKNOWN:
                        String[] neighbors = {"-","-","-","-","-","-","-","-"};
                        if (col == 0){
                            neighbors[0] = "0";
                            neighbors[3] = "0";
                            neighbors[5] = "0";
                        }
                        if (col == mapTiles[row].length-1){
                            neighbors[2] = "0";
                            neighbors[4] = "0";
                            neighbors[7] = "0";
                        }
                        if (row == 0){
                            neighbors[0] = "0";
                            neighbors[1] = "0";
                            neighbors[2] = "0";
                        }
                        if (row == mapTiles.length-1){
                            neighbors[5] = "0";
                            neighbors[6] = "0";
                            neighbors[7] = "0";
                        }
                        for (int i = 0; i < neighbors.length; i++) {
                            if(!neighbors[i].equals("0")){
                                if(i == 0){
                                    if(mapTiles[row - 1][col - 1] != Map.Tiles.UNKNOWN){
                                        neighbors[i] = "0";
                                    }
                                }else if(i == 1){
                                    if(mapTiles[row - 1][col] != Map.Tiles.UNKNOWN){
                                        neighbors[i] = "0";
                                    }
                                }else if(i == 2){
                                    if(mapTiles[row - 1][col + 1] != Map.Tiles.UNKNOWN){
                                        neighbors[i] = "0";
                                    }
                                }else if(i == 3){
                                    if(mapTiles[row][col - 1] != Map.Tiles.UNKNOWN){
                                        neighbors[i] = "0";
                                    }
                                }else if(i == 4){
                                    if(mapTiles[row][col + 1] != Map.Tiles.UNKNOWN){
                                        neighbors[i] = "0";
                                    }
                                }else if(i == 5){
                                    if(mapTiles[row + 1][col - 1] != Map.Tiles.UNKNOWN){
                                        neighbors[i] = "0";
                                    }
                                }else if(i == 6){
                                    if(mapTiles[row + 1][col] != Map.Tiles.UNKNOWN){
                                        neighbors[i] = "0";
                                    }
                                }else if(i == 7) {
                                    if (mapTiles[row + 1][col + 1] != Map.Tiles.UNKNOWN) {
                                        neighbors[i] = "0";
                                    }
                                }
                            }
                        }
                        String str = String.join("", neighbors);
                        if(str.equals("--------")){
                            drawSprite(g2, "cloud.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else{
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }

                        //Sides
                        if(tileIdentification(str,"=0=-----")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudTop.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=--0-=--")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudLeft.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"-----=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudBottom.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"--=-0--=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudRight.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }
                        //Corners
                        else if(tileIdentification(str,"=0=-0--=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudTopRight.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=0=0-=--")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudTopLeft.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=--0-=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudBottomLeft.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"--=-0=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudBottomRight.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }
                        //Corners with opposite inner
                        else if(tileIdentification(str,"=0=-00-=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudTopRightAlt.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=0=0-=-0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudTopLeftAlt.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=-00-=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudBottomLeftAlt.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"0-=-0=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudBottomRightAlt.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }
                        //Inner Corners
                        else if(tileIdentification(str,"0-------")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudInnerBottomRight.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"--0-----")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudInnerBottomLeft.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"-----0--")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudInnerTopRight.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"-------0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudInnerTopLeft.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }
                        //Dual Inner Corners
                        else if(tileIdentification(str,"--0----0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudInnerRight.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"0----0--")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudInnerLeft.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"0-0-----")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudInnerTop.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"-----0-0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudInnerBottom.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }
                        //Missing Inner Corners
                        else if(tileIdentification(str,"0-0--0--")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudMissingBottomRight.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"0-0----0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudMissingBottomLeft.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"0----0-0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudMissingTopRight.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"--0--0-0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudMissingTopLeft.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }
                        //Strip
                        else if(tileIdentification(str,"=0=--=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudStripHor.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=-=00=-=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudStripVer.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }
                        //Strip Ends
                        else if(tileIdentification(str,"=0=0-=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudStripEndLeft.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=0=-0=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudStripEndRight.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=0=00=-=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudStripEndTop.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=-=00=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudStripEndBottom.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }
                        //Diagonals
                        else if(tileIdentification(str,"0------0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudTopLeftToBottomRight.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"--0--0--")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudTopRightToBottomLeft.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }
                        //Side + Inners
                        else if(tileIdentification(str,"0----=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudBottomWithLeft.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"--0--=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudBottomWithRight.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"0-0--=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudBottomWithBoth.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=0=--0--")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudTopWithLeft.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=0=----0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudTopWithRight.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=0=--0-0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudTopWithBoth.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=--0-=-0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudLeftWithBottom.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=-00-=--")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudLeftWithTop.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"=-00-=-0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudLeftWithBoth.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"--=-00-=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudRightWithBottom.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"0-=-0--=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudRightWithTop.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"0-=-00-=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudRightWithBoth.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }

                        //Solo
                        else if(tileIdentification(str,"=0=00=0=")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudSolo.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"0-0--0-0")){
                            drawSprite(g2, "singlePath.png", TILE_SIZE, new Vec2d(col, row), offset);
                            drawSprite(g2, "cloudInnerAll.png", TILE_SIZE, new Vec2d(col, row), offset);
                        }else if(tileIdentification(str,"--------")){
                            //PLACEHOLDER
                        }

                        //Fail?
                        else{
                            System.out.println(str);
                        }

                        break;
                }
            }

        }

    }

    private static boolean tileIdentification(String neighbors, String target){
        for (int i = 0; i < neighbors.length(); i++) {
            if (!target.substring(i,i+1).equals("=")){
                if(!target.substring(i,i+1).equals(neighbors.substring(i,i+1))){
                    return false;
                }
            }
        }
        return true;
    }

    public static void drawMapWithBorder(Map map, Graphics2D g2){
        drawMap(map, g2, new Vec2d(OFFSET, OFFSET));
        Vec2d size = new Vec2d(BOARDER_TILE_COUNT * 2 + map.getViewableMapArray()[0].length, BOARDER_TILE_COUNT * 2 + map.getViewableMapArray().length);
        for (int rowIndex = 0; rowIndex < BOARDER_TILE_COUNT ; rowIndex++) {
            for (int colIndex = 0; colIndex <= size.col - 1; colIndex++) {
                if(rowIndex == BOARDER_TILE_COUNT -1){
                    if(colIndex >= BOARDER_TILE_COUNT && colIndex <= size.col - BOARDER_TILE_COUNT -1){
                        drawSprite(g2, "waterTop.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d(0, 0));
                    } else if(colIndex == BOARDER_TILE_COUNT - 1){
                        drawSprite(g2, "waterTopLeft.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d(0, 0));
                    } else if(colIndex == size.col - BOARDER_TILE_COUNT){
                        drawSprite(g2, "waterTopRight.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d(0, 0));
                    } else{
                        drawSprite(g2, "water.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d(0, 0));
                    }
                }else {
                    drawSprite(g2, "water.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d(0, 0));
                }
            }
        }

        for (int rowIndex = 0; rowIndex < BOARDER_TILE_COUNT ; rowIndex++) {
            for (int colIndex = 0; colIndex <= size.col - 1; colIndex++) {
                if(rowIndex == 0){
                    if(colIndex >= BOARDER_TILE_COUNT && colIndex <= size.col - BOARDER_TILE_COUNT -1){
                        drawSprite(g2, "waterBottom.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d(0, (size.row - BOARDER_TILE_COUNT) * TILE_SIZE));
                    } else if(colIndex == BOARDER_TILE_COUNT - 1){
                        drawSprite(g2, "waterBottomLeft.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d(0, (size.row - BOARDER_TILE_COUNT) * TILE_SIZE));
                    } else if(colIndex == size.col - BOARDER_TILE_COUNT){
                        drawSprite(g2, "waterBottomRight.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d(0, (size.row - BOARDER_TILE_COUNT) * TILE_SIZE));
                    } else{
                        drawSprite(g2, "water.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d(0, (size.row - BOARDER_TILE_COUNT) * TILE_SIZE));
                    }
                }else {
                    drawSprite(g2, "water.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d(0, (size.row - BOARDER_TILE_COUNT) * TILE_SIZE));
                }
            }
        }

        for (int colIndex = 0; colIndex < BOARDER_TILE_COUNT ; colIndex++) {
            for (int rowIndex = BOARDER_TILE_COUNT; rowIndex <= size.row - BOARDER_TILE_COUNT - 1; rowIndex++) {
                if(colIndex == BOARDER_TILE_COUNT - 1){
                    drawSprite(g2, "waterLeft.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d(0, 0));
                }else {
                    drawSprite(g2, "water.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d(0, 0));
                }
            }
        }

        for (int colIndex = 0; colIndex < BOARDER_TILE_COUNT ; colIndex++) {
            for (int rowIndex = BOARDER_TILE_COUNT; rowIndex <= size.row - BOARDER_TILE_COUNT - 1; rowIndex++) {
                if(colIndex == 0){
                    drawSprite(g2, "waterRight.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d((size.col - BOARDER_TILE_COUNT) * TILE_SIZE, 0));
                }else {
                    drawSprite(g2, "water.png", TILE_SIZE, new Vec2d(colIndex, rowIndex), new Vec2d((size.col - BOARDER_TILE_COUNT) * TILE_SIZE, 0));
                }
            }
        }
    }

    public static void drawSprite(Graphics2D g2, String fileName, int tileSize, Vec2d loc, Vec2d offset) {
        loc.row = loc.row * tileSize + offset.row;
        loc.col = loc.col * tileSize + offset.col;

        BufferedImage sprite;
        try {
            sprite = ImageIO.read(new File("res/" + fileName));
            g2.drawImage(sprite, loc.col, loc.row, tileSize, tileSize, null);
        } catch (Exception e) {
            System.out.println("File not found...");
        }
    }
}
