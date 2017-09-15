package GameLogic;

import Utilities.Vec2d;
import org.omg.CORBA.UNKNOWN;

import java.awt.*;

/**
 * Created by student on 9/13/17.
 */
public class Map {
    Tiles tiles;
    Tiles[][] mapArray;
    Tiles[][] viewableMapArray;

    Vec2d startTile;
    Vec2d stopTile;
    int x;
    int y;
    int mapSize = 100;

    public Map(int width, int height, Vec2d x, Vec2d y) {
        Tiles[][] mapArray = new Tiles[width][height];
        Tiles[][] viewableMapArray = new Tiles[width][height];

        startTile = x;
        stopTile = y;

        this.x = width;
        this.y = height;

    }

    public Tiles[][] returner() {
        for (int row = 0; row < x; row++) {
            for (int col = 0; col < y; col++) {

            }
        }


//        for (int row = 0; row < x; row++) {
//            for (int col = 0; col < y; col++) {
//                if (viewableMapArray[row][col] != Tiles.UNKNOWN){//true means that you can see through the cloud
//                    tilesArray[row][col] = mapArray[row][col];
//                } else {
//                    tilesArray[row][col] = Tiles.UNKNOWN;
//                }
//            }
//        }


//        tilesArray[startTile.x][startTile.y] = mapArray[startTile.x][startTile.y];
//        tilesArray[stopTile.x][stopTile.y] = mapArray[stopTile.x][stopTile.y];
//
//
//        return tilesArray;
        return viewableMapArray;
    }

    public void update(Mission x){


    }




    public enum Tiles {
        PATH, BLOCK, UNKNOWN, START, STOP
    }



}
