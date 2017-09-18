package game.logic;
import game.logic.Unit;
import game.logic.Action;

import java.util.ArrayList;

import java.util.ArrayList;

public class Mission {
    private Unit unit;
    private ArrayList<Action> actions;

    public Mission(Unit unit, ArrayList<Action> actions){
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
