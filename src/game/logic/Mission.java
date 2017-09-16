package game.logic;

import java.util.ArrayList;

public class Mission {

    private Unit unit;
    private ArrayList<Action> actions;

    public Mission(int unit, ArrayList<Action> actions){
        this.unit = unit;
        this.actions = actions;
    }

    public Unit getUnit() {
        return unit;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }
}
