package game.logic;

public class Action {
    public enum ActionType{
        TURN, MOVE
    }

    private ActionType actionType;
    private int argument;

    public Action(ActionType actionType, int argument){
        this.actionType = actionType;
        this.argument = argument;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getArgument() {
        return argument;
    }
}
