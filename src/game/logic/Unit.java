package game.logic;
import utilities.*;

public class Unit {
    public String name;
    private Vec2d currentPos, displacement;
    public int cost;

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
            displacement.x = 1;
        }else {
            displacement.x = -1;
        }
    }
}