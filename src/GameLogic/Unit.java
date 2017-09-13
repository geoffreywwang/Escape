package GameLogic;
import Utilities.*;

public class Unit {
    String name;
    Vec2d currentpos, displacement;
    int cost;

    Unit(String n, Vec2d pos, int c) {
        name = n;
        cost = c;
        currentpos = pos;
    }

    void move() {
        currentpos.add(displacement);
    }

    void turn(Vec2d dir) {
        displacement = dir;
    }
}