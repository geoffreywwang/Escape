package game.logic;

import utilities.LogicUtil;
import utilities.Vec2d;

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
        viewableMapArray = new Tiles[width][height];

        mapArray = LogicUtil.generateEmptyMap(width, height);

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                viewableMapArray[row][col] = Tiles.UNKNOWN;
            }
        }
        startTile = x;
        stopTile = y;
        mapArray[x.row][x.col] = Tiles.START;
        mapArray[y.row][y.col] = Tiles.STOP;
        viewableMapArray[x.row][x.col] = Tiles.START;
        viewableMapArray[y.row][y.col] = Tiles.STOP;
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


                            if (unit.getCurrentPos().row >= mapArray.length) {
                                break;
                            } else if (unit.getCurrentPos().col <= -1) {
                                break;
                            } else if (unit.getCurrentPos().row >= mapArray[0].length) {
                                break;
                            } else if (unit.getCurrentPos().row <= -1) {
                                break;
                            }





                            int col = unit.getCurrentPos().col;
                            int row = unit.getCurrentPos().row;
                            if (mapArray[row][col] == Tiles.BLOCK) {
                                viewableMapArray[row][col] = Tiles.BLOCK;
                                actionFullStop = true;
                                break;

                            } else {
                                viewableMapArray[row][col] = Tiles.PATH;
                            }



                        }



                            break;
                }
                if (actionFullStop) {
                    break;
                }


        }


//        int col = currentPosition.col;
//        int row = currentPosition.row;
//        viewableMapArray[col][row] = mapArray[col][row]; /*the maps haven't been generated yet*/

        return viewableMapArray;
    }

    public Tiles[][] getViewableMapArray() {
        return viewableMapArray;
    }

    public enum Tiles {
        PATH, BLOCK, UNKNOWN, START, END
    }

    public void print(Tiles[][] tiles) {
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                switch (tiles[row][col]) {
                    case UNKNOWN:
                        System.out.print("unknown");
                        break;
                    case PATH:
                        System.out.print("path   ");
                        break;
                    case START:
                        System.out.print("start  ");
                        break;
                    case STOP:
                        System.out.print("stop   ");
                        break;
                    case BLOCK:
                        System.out.print("block   ");
                        break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
