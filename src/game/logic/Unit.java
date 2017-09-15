package game.logic;
import utilities.*;

public class Unit {
    private String name;
    private Vec2d currentPos, displacement;
    private int cost;

    public Unit(String name, Vec2d position, int cost) {
        this.name = name;
        this.cost = cost;
        currentPos = position;
    }

    public void move() {
        currentPos.add(displacement);
    }

    public void turn(boolean turnRight) {
        if(turnRight) {

        }else {

        }
    }

    public void performAction(Action action) {
    }

    public int getCost() {
        return cost;
    }

    public Vec2d getCurrentPos() {
        return currentPos;
    }

    public Vec2d getDisplacement() {
        return displacement;
    }

    public String getName() {
        return name;
    }
}