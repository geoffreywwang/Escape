package game.logic;

/**
 * Created by student on 9/13/17.
 */
public class Action {

    private ActionType actionType;
    private int argument;
    public enum ActionType{
        TURN, MOVE
    }

    public Action(ActionType actionType, int argument ){
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
