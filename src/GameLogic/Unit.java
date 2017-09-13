package GameLogic;
import Utilities.*;

public class Unit {
    public String name;
    private Vec2d currentpos, displacement;
    public int cost;

    public Unit(String n, Vec2d pos, int c) {
        name = n;
        cost = c;
        currentpos = pos;
    }

    public void move() {
        currentpos.add(displacement);
    }

    public void turn(Vec2d dir) {
        displacement = dir;
    }
}