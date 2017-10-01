package game.logic;

import utilities.*;
import game.logic.Mission;
import game.logic.Action;
import game.logic.Unit;
import utilities.LogicUtil;
import utilities.Vec2d;

import java.util.ArrayList;
import java.util.Objects;

public class Map {

    public static boolean REVEAL_MAP = false;

    public enum Tiles {
        PATH, BLOCK, UNKNOWN, START, STOP
    }

    private Tiles[][] mapArray, viewableMapArray;
    private Vec2d startTile;
    private Vec2d stopTile;

    public Map(int cols, int rows) {
        mapArray = LogicUtil.generateMap(cols, rows);
        viewableMapArray = new Tiles[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                viewableMapArray[row][col] = Tiles.UNKNOWN;
            }
        }

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(mapArray[row][col] == Tiles.START) {
                    startTile = new Vec2d(col, row);
                    viewableMapArray[row][col] = Tiles.START;
                }else if(mapArray[row][col] == Tiles.STOP) {
                    stopTile = new Vec2d(col, row);
                    viewableMapArray[row][col] = Tiles.STOP;
                }
            }
        }
        if(REVEAL_MAP){
            reveal();
        }
    }

    public void reveal(){
        for (int row = 0; row < mapArray.length; row++) {
            for (int col = 0; col < mapArray[0].length; col++) {
                if(mapArray[row][col] != Tiles.BLOCK) {
                    viewableMapArray[row][col] = mapArray[row][col];
                }
            }
        }
    }

    public Vec2d getStopTile() {
        return stopTile;
    }

    public Vec2d getStartTile() {
        return startTile;
    }

    public Mission updateNew(Mission mission){
        ArrayList<Action> actionList = mission.getActions();
        Unit tempUnit = mission.getUnit();

        Action temp = actionList.remove(0);
        if (temp == null) {
            return mission;
        }
        switch (temp.getActionType()) {
            case TURN_RIGHT:
//                if (Objects.equals(tempUnit.getName(), "scout")){
//                    Vec2d displaceStore = tempUnit.getDisplacement();
//                    tempUnit.turn(Unit.TURN_RIGHT); //turn right is true
//                    displaceStore.add(tempUnit.getDisplacement());
//                    setViewableMap(new Vec2d(tempUnit.getCurrentPos().col + displaceStore.col, tempUnit.getCurrentPos().row + displaceStore.row));
//                } else {
                    tempUnit.turn(Unit.TURN_RIGHT); // turn right is true
//                }


                return mission;
            case TURN_LEFT:
                tempUnit.turn(Unit.TURN_LEFT); //turn right is true
                return mission;
            case MOVE:
                if (temp.getArgument() == 0){
                    return mission;
                }
                tempUnit.move();

                if (tempUnit.getCurrentPos().row >= mapArray.length) {
                    return null;
                } else if (tempUnit.getCurrentPos().col <= -1) {
                    return null;
                } else if (tempUnit.getCurrentPos().row >= mapArray[0].length) {
                    return null;
                } else if (tempUnit.getCurrentPos().row <= -1) {
                    return null;
                }

                if (Objects.equals(mission.getUnit().getName(), "scout")) {
                    if (mission.getUnit().getDisplacement().row == 0) {//moving left right
                        setViewableMap(tempUnit.getCurrentPos());
                        if (tempUnit.getCurrentPos().row < mapArray.length-1)
                            setViewableMap(new Vec2d(tempUnit.getCurrentPos().col, tempUnit.getCurrentPos().row+1));
                        if (tempUnit.getCurrentPos().row > 0)
                            setViewableMap(new Vec2d(tempUnit.getCurrentPos().col, tempUnit.getCurrentPos().row-1));
                    } else if (mission.getUnit().getDisplacement().col == 0) {//moving up down
                        setViewableMap(tempUnit.getCurrentPos());
                        if (tempUnit.getCurrentPos().col < mapArray[0].length-1)
                            setViewableMap(new Vec2d(tempUnit.getCurrentPos().col+1, tempUnit.getCurrentPos().row));
                        if (tempUnit.getCurrentPos().col > 0)
                            setViewableMap(new Vec2d(tempUnit.getCurrentPos().col-1, tempUnit.getCurrentPos().row));
                    }
                } else {
                    setViewableMap(tempUnit.getCurrentPos());
                }

                int col = tempUnit.getCurrentPos().col;
                int row = tempUnit.getCurrentPos().row;
                if (mapArray[row][col] == Tiles.BLOCK) {
                    return null;
                }

                actionList.add(0,new Action(Action.ActionType.MOVE,temp.getArgument()-1));
                return mission;
        }
        return null;
    }

    public ArrayList<Vec2d> update(Mission mission){

        ArrayList<Action> actionList = mission.getActions();
        Unit tempUnit = mission.getUnit();
        boolean actionFullStop = false;
        ArrayList<Vec2d> coorList = new ArrayList<Vec2d>();

        coorList.add(tempUnit.getCurrentPos());

        for (int actionListNumber = 0; actionListNumber < actionList.size(); actionListNumber++) {
            switch (actionList.get(actionListNumber).getActionType()) {
                case TURN_RIGHT:
                    tempUnit.turn(Unit.TURN_RIGHT); //turn right is true
                    break;
                case TURN_LEFT:
                    tempUnit.turn(Unit.TURN_LEFT); //turn right is true
                    break;
                case MOVE:
                    for (int moves = 0; moves < actionList.get(actionListNumber).getArgument(); moves++) { //traverse through movements
                        tempUnit.move();

                        if (tempUnit.getCurrentPos().row >= mapArray.length) {
                            break;
                        } else if (tempUnit.getCurrentPos().col <= -1) {
                            break;
                        } else if (tempUnit.getCurrentPos().row >= mapArray[0].length) {
                            break;
                        } else if (tempUnit.getCurrentPos().row <= -1) {
                            break;
                        }
                        coorList.add(tempUnit.getCurrentPos());
                        int col = tempUnit.getCurrentPos().col;
                        int row = tempUnit.getCurrentPos().row;

                        if (mapArray[row][col] == Tiles.BLOCK) {
                            actionFullStop = true;
                            break;
                        }
                    }
                    break;
            }

            if (actionFullStop) {
                break;
            }
        }
        return coorList;
    }

    public void setViewableMap(Vec2d vec2d){
        viewableMapArray[vec2d.row][vec2d.col] = mapArray[vec2d.row][vec2d.col];
    }

    public Tiles[][] getViewableMapArray() {
        return viewableMapArray;
    }

    public void print() {
        for (int row = 0; row < mapArray.length; row++) {
            for (int col = 0; col < mapArray[0].length; col++) {
                switch (mapArray[row][col]) {
                    case UNKNOWN:
                        System.out.print("unknown");
                        break;
                    case PATH:
                        System.out.print("path    ");
                        break;
                    case START:
                        System.out.print("start   ");
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
