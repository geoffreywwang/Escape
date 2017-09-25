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
            if(value.equals("right")) {
                return new Action(Action.ActionType.TURN_RIGHT, -1);
            } else{
                return new Action(Action.ActionType.TURN_LEFT, -1);
            }
        }

        return null;

    }
    public static Mission parseScript(Vec2d vec, String script, int cost){
        ArrayList<Action> actions = new ArrayList<>();
        String[] arr = script.split("\\\n");
        String[] command = arr[0].split(" ");
        vec= new Vec2d(0,0);
        cost = 1;
        Unit unit = new Unit(command[1], vec, cost);
        for (int i = 1; i < arr.length; i++) {
            actions.add(parseLine(arr[i]));

        }


        return new Mission(unit, actions);
    }


}
