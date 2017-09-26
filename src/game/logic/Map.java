package game.logic;

import utilities.*;
import game.logic.Mission;
import game.logic.Action;
import game.logic.Unit;
import utilities.LogicUtil;
import utilities.Vec2d;

import java.util.ArrayList;

public class Map {
    private Tiles[][] mapArray, viewableMapArray;
    private Unit unit;
    public Vec2d startTile;
    private Vec2d stopTile;
    private int x, y;

    public Map(int width, int height) {
        Tiles[][] mapArray = LogicUtil.generateMap(width, height);
        Tiles[][] viewableMapArray = new Tiles[width][height];

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                viewableMapArray[row][col] = Tiles.UNKNOWN;
            }
        }

        for(int row = 0; row < width; row++) {
            for(int col = 0; col < height; col++) {
                if(mapArray[row][col] == Tiles.START) {
                    startTile = new Vec2d(row, col);
                }else if(mapArray[row][col] == Tiles.STOP) {
                    stopTile = new Vec2d(row, col);
                }
            }
        }

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
        return viewableMapArray;
    }

    public Tiles[][] getViewableMapArray() {
        return viewableMapArray;
    }

    public enum Tiles {
        PATH, BLOCK, UNKNOWN, START, STOP
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
