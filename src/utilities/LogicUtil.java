package utilities;
import game.logic.*;
import java.util.ArrayList;

public class LogicUtil {
    public static Map.Tiles[][] generateMap(int width, int height) {
        Map.Tiles[][] map = new Map.Tiles[width][height];
        for(int row = 0; row < width; row++) {
            for(int col = 0; col < height; col++) {
                map[row][col] = Map.Tiles.BLOCK;
            }
        }
        ArrayList<Wall> walls = new ArrayList<>();
        int randstart = (int)(Math.random() * map.length);
        map[randstart][0] = Map.Tiles.START;

        // Stores initial walls
        if(randstart-1>0) {
            walls.add(new Wall(randstart-1, 0));
        }
        if(randstart+1<map.length) {
            walls.add(new Wall(randstart+1, 0));
        }
        walls.add(new Wall(randstart, 1));

        //Prim's Algorithm
        while(walls.size() > 0) {
            int randIndex = (int)(Math.random() * walls.size());
            Wall chosen = walls.get(randIndex);
            if(chosen.checkAdjacent(map)) {
                map[chosen.x][chosen.y] = Map.Tiles.PATH;
                int x = chosen.x;
                int y = chosen.y;
                if(x-1>=0 && map[x-1][y].equals(Map.Tiles.BLOCK)) {
                    walls.add(new Wall(x-1, y));
                }
                if(x+1 < map.length && map[x+1][y].equals(Map.Tiles.BLOCK)) {
                    walls.add(new Wall(x+1, y));
                }
                if(y-1 >= 0 && map[x][y-1].equals(Map.Tiles.BLOCK)) {
                    walls.add(new Wall(x, y-1));
                }
                if(y+1 < map[0].length && map[x][y+1].equals(Map.Tiles.BLOCK)) {
                    walls.add(new Wall(x, y+1));
                }
            }
            walls.remove(chosen);
        }

        int randend = (int)(Math.random() * map.length);
        while(map[randend][map[0].length - 1] != Map.Tiles.PATH) {
            randend = (int)(Math.random() * map.length);
        }
        map[randend][map[0].length - 1] = Map.Tiles.STOP;

        return map;
    }

    public static void displayMap(Map.Tiles[][] map) {
        for(int row = 0; row < map.length; row++) {
            for(int col = 0; col < map[0].length; col++) {
                if(map[row][col] == Map.Tiles.BLOCK) {
                    System.out.print("X ");
                }else if(map[row][col] == Map.Tiles.PATH) {
                    System.out.print("  ");
                }else if(map[row][col] == Map.Tiles.START) {
                    System.out.print("S ");
                }else {
                    System.out.print("E ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static class Wall {
        int x, y;
        Wall(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean checkAdjacent(Map.Tiles[][] map) {
            int adjacentpaths = 0;
            if(x-1>=0 && (map[x-1][y].equals(Map.Tiles.PATH) || map[x-1][y].equals(Map.Tiles.START))) {
                adjacentpaths++;
            }
            if(x+1 < map.length && (map[x+1][y].equals(Map.Tiles.PATH) || map[x+1][y].equals(Map.Tiles.START))) {
                adjacentpaths++;
            }
            if(y-1 >= 0 && (map[x][y-1].equals(Map.Tiles.PATH) || map[x][y-1].equals(Map.Tiles.START))) {
                adjacentpaths++;
            }
            if(y+1 < map[0].length && (map[x][y+1].equals(Map.Tiles.PATH) || map[x][y+1].equals(Map.Tiles.START))) {
                adjacentpaths++;
            }

            if(adjacentpaths==1) {
                return true;
            }else {
                return false;
            }
         }
    }
}
