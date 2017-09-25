
import utilities.*;

public class Main {
    public static void main(String[] args) {
        LogicUtil util = new LogicUtil();
        util.displayMap(util.generateMap(12, 12));
      
        Action action = Interpreter.parseLine("unit.turn(false);");
        System.out.println(action.getActionType());

        Mission mission = Interpreter.parseScript( "Unit unit = new Unit();\nunit.move(5);\nunit.turn(true);");
        System.out.println(mission.getActions().get(1).getActionType());
    }
}
