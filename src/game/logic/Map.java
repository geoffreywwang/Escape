package game.logic;

import utilities.Vec2d;
import game.logic.Mission;
import game.logic.Action;
import game.logic.Unit;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Map {
    Tiles tiles;
    Tiles[][] mapArray;
    Tiles[][] viewableMapArray;
    Unit unit;
    public Vec2d startTile;
    Vec2d stopTile;
    int x;
    int y;
    int mapSize = 100;

    public Map(int width, int height, Vec2d x, Vec2d y) {
        Tiles[][] mapArray = new Tiles[width][height];
        Tiles[][] viewableMapArray = new Tiles[width][height];

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                viewableMapArray[row][col] = Tiles.UNKNOWN;
            }
        }
        startTile = x;
        stopTile = y;

        this.x = width;
        this.y = height;

    }



    public Tiles[][] update(Mission mission){


        ArrayList<Action> actionList = mission.getActions();
        unit = mission.getUnit();
        boolean actionFullStop = false;

        for (int actionListNumber = 0; actionListNumber < actionList.size(); actionListNumber++) {

                switch (actionList.get(actionListNumber).getActionType()) {
                    case TURN_RIGHT:
                        unit.turn(Unit.TURN_RIGHT); //turn right is true
                            break;
                    case TURN_LEFT:
                        unit.turn(Unit.TURN_LEFT); //turn right is true
                            break;
                    case MOVE:
                        for (int moves = 0; moves < actionList.get(actionListNumber).getArgument(); moves++) { //traverse through movements
                            unit.move();
                            int x = unit.getCurrentPos().x;
                            int y = unit.getCurrentPos().y;
                            if (mapArray[x][y] == Tiles.BLOCK) {
                                viewableMapArray[x][y] = Tiles.BLOCK;
                                actionFullStop = true;
                                break;

                            } else {
                                viewableMapArray[x][y] = Tiles.PATH;
                            }

                        }
                            break;
                }
                if (actionFullStop) {
                    break;
                }


        }


//        int x = currentPosition.x;
//        int y = currentPosition.y;
//        viewableMapArray[x][y] = mapArray[x][y]; /*the maps haven't been generated yet*/

        return viewableMapArray;
    }

    public Tiles[][] getViewableMapArray() {
        return viewableMapArray;
    }

    public enum Tiles {
        PATH, BLOCK, UNKNOWN, START, END
    }



}
