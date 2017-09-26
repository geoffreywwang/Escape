package game.logic;
import utilities.*;

public class Unit {
    public static boolean TURN_RIGHT = true, TURN_LEFT = false;
  
    private String name;
    private Vec2d currentPos, displacement;
    public int cost;

    public Unit(String name, Vec2d position, int cost) {
        this.name = name;
        this.cost = cost;
        currentPos = position;
        displacement = new Vec2d(1, 0);
    }

    public void move() {

        currentPos.add(new Vec2d(displacement.col, displacement.row));

    }


    public void turn(boolean turnRight) {
        /*
        (0, 1) down
        (0, -1) up
        (1, 0) right
        (-1, 0) left
         */
        if(turnRight) {
            if(displacement.col == 0 && displacement.row == -1) {
                displacement.col = 1;
                displacement.row = 0;
            }else if(displacement.col == 1 && displacement.row == 0) {
                displacement.col = 0;
                displacement.row = 1;
            }else if(displacement.col == 0 && displacement.row == 1) {
                displacement.col = -1;
                displacement.row = 0;
            }else if(displacement.col == -1 && displacement.row == 0) {
                displacement.col = 0;
                displacement.row = -1;
            }
        }else {
            if(displacement.col == 0 && displacement.row == -1) {
                displacement.col = -1;
                displacement.row = 0;
            }else if(displacement.col == -1 && displacement.row == 0) {
                displacement.col = 0;
                displacement.row = 1;
            }else if(displacement.col == 0 && displacement.row == 1) {
                displacement.col = 1;
                displacement.row = 0;
            }else if(displacement.col == 1 && displacement.row == 0) {
                displacement.col = 0;
                displacement.row = -1;
            }
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