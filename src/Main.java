
import utilities.*;
import game.logic.Action;
import game.logic.Map;
import game.logic.Mission;
import game.logic.Unit;
import utilities.Vec2d;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LogicUtil util = new LogicUtil();
        util.displayMap(util.generateMap(12, 12));

        Action action = Interpreter.parseLine("unit.turn(false);");
        System.out.println(action.getActionType());

        Mission mission = Interpreter.parseScript( "Unit unit = new Unit();\nunit.move(5);\nunit.turn(true);");
        System.out.println(mission.getActions().get(1).getActionType());
        Vec2d start = new Vec2d(3,3);
        Map map = new Map(10, 10, start, new Vec2d(9, 0));
        ArrayList<Action> actions = new ArrayList<Action>();




    }

}
