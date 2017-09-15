package game.logic;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by student on 9/15/17.
 */
public class Mission {
    //unit and a list of actions

    private int unit;
    private ArrayList<Action> actions = new ArrayList<Action>();

    public Mission(int unit, ArrayList actions){
        this.unit = unit;
        this.actions = actions;
    }

    public int getUnit() {
        return unit;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }
}
