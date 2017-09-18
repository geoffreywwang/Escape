package game.logic;
import game.logic.Unit;
import game.logic.Action;

import java.util.ArrayList;

/**
 * Created by student on 9/15/17.
 */
public class Mission {
    //unit and a list of actions
    Unit unit;
    ArrayList<Action> actionArrayList = new ArrayList<Action>();

    public Mission() {
    }

    public Unit getUnit() {
        return unit;
    }

    public ArrayList<Action> getActionArrayList() {
        return actionArrayList;
    }
}
