package utilities;

import com.sun.java.swing.action.NewAction;
import game.logic.Action;
import game.logic.Mission;
import game.logic.Unit;

import java.util.ArrayList;

/**
 * Created by student on 9/18/17.
 */
public class Interpreter {

    //converts in the format: unit.move(3);
    //possible commands: turn, move
    //for now assumes unit is the implicit parameter
    public static Action parseLine(String string){

        String[] arr = string.split("\\.");
        String[] command = arr[1].split("\\(");   //["move", "3);"]
        String method = command[0];
        String value = command[1].split("\\)")[0];


        if(method.equals("move")){
            return new Action(Action.ActionType.MOVE,Integer.parseInt(value));
        }else if(method.equals("turn")){
            if(Boolean.parseBoolean(value)==true) {
                return new Action(Action.ActionType.TURN_RIGHT, -1);
            } else{
                return new Action(Action.ActionType.TURN_LEFT, -1);
            }
        }

        return null;

    }
    public static Mission parseScript( String script){
        ArrayList<Action> actions = new ArrayList<>();
        String[] arr = script.split("\\\n");
        String[] command = arr[0].split(" ");
        Unit unit = new Unit(command[1], new Vec2d(0,0), 1);
        for (int i = 1; i < arr.length; i++) {
            actions.add(parseLine(arr[i]));

        }


        return new Mission(unit, actions);
    }


}