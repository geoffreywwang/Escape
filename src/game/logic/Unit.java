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
    }

    public void move() {
        currentPos.add(displacement);
    }


    public void turn(boolean turnRight) {
        /*
        (0, 1) down
        (0, -1) up
        (1, 0) right
        (-1, 0) left
         */
        if(turnRight) {
            if(displacement.x == 0 && displacement.y == -1) {
                displacement.x = 1;
                displacement.y = 0;
            }else if(displacement.x == 1 && displacement.y == 0) {
                displacement.x = 0;
                displacement.y = 1;
            }else if(displacement.x == 0 && displacement.y == 1) {
                displacement.x = -1;
                displacement.y = 0;
            }else if(displacement.x == -1 && displacement.y == 0) {
                displacement.x = 0;
                displacement.y = -1;
            }
        }else {
            if(displacement.x == 0 && displacement.y == -1) {
                displacement.x = -1;
                displacement.y = 0;
            }else if(displacement.x == -1 && displacement.y == 0) {
                displacement.x = 0;
                displacement.y = 1;
            }else if(displacement.x == 0 && displacement.y == 1) {
                displacement.x = 1;
                displacement.y = 0;
            }else if(displacement.x == 1 && displacement.y == 0) {
                displacement.x = 0;
                displacement.y = -1;
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