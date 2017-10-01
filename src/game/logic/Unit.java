package game.logic;
import game.graphics.Sprite;
import utilities.*;

import static game.graphics.Pen.OFFSET;
import static game.graphics.Pen.TILE_SIZE;

public class Unit {
    public static boolean TURN_RIGHT = true, TURN_LEFT = false;
  
    private String name;
    private Vec2d currentPos, displacement;
    public int cost;

    private Sprite sprite;

    public Unit(String name, Vec2d position) {
        this.name = name;
        switch (name) {
            case "normal":
                cost = 1;
                break;
            case "scout":
                cost = 8;
                break;
            case "champ":
                cost = 20;
                break;
        }
        System.out.println(cost);
        currentPos = position;
        displacement = new Vec2d(1, 0);

        sprite = new Sprite("minions",new Vec2d(currentPos.col * TILE_SIZE + OFFSET, currentPos.row * TILE_SIZE + OFFSET));
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void move() {
        sprite.move();
        currentPos.add(new Vec2d(displacement.col, displacement.row));
    }


    public void turn(boolean turnRight) {
        /*
        (0, 1) down
        (0, -1) up
        (1, 0) right
        (-1, 0) left
         */
        sprite.turnSprite(turnRight);
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