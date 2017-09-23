package utilities;
import game.logic.*;
import java.util.ArrayList;

public class LogicUtil {
    public static void main(String[] args) {
        Map.Tiles[][] arr1 = new Map.Tiles[][] {
                {Map.Tiles.BLOCK, Map.Tiles.BLOCK, Map.Tiles.BLOCK},
                {Map.Tiles.PATH, Map.Tiles.BLOCK, Map.Tiles.PATH},
                {Map.Tiles.BLOCK, Map.Tiles.BLOCK, Map.Tiles.BLOCK},
                {Map.Tiles.PATH, Map.Tiles.BLOCK, Map.Tiles.PATH},
                {Map.Tiles.BLOCK, Map.Tiles.BLOCK, Map.Tiles.BLOCK},
                {Map.Tiles.PATH, Map.Tiles.BLOCK, Map.Tiles.PATH},
        };
        Map.Tiles[][] arr2 = new Map.Tiles[][] {
                {Map.Tiles.PATH, Map.Tiles.BLOCK, Map.Tiles.PATH},
                {Map.Tiles.BLOCK, Map.Tiles.BLOCK, Map.Tiles.BLOCK},
                {Map.Tiles.PATH, Map.Tiles.BLOCK, Map.Tiles.PATH},
                {Map.Tiles.BLOCK, Map.Tiles.BLOCK, Map.Tiles.BLOCK},
                {Map.Tiles.PATH, Map.Tiles.BLOCK, Map.Tiles.PATH},
                {Map.Tiles.BLOCK, Map.Tiles.BLOCK, Map.Tiles.BLOCK},
        };
        Map.Tiles[][] merged = merge(arr1, arr2, false);
        for(int row = 0; row < merged.length; row++) {
            for(int col = 0; col < merged[0].length; col++) {
                System.out.print(merged[row][col] + ", ");
            }
            System.out.println();
        }
    }

    public void generateMap() {

    }

    public static Map.Tiles[][] merge(Map.Tiles[][] first, Map.Tiles[][] second, boolean horizontal) {
        if(horizontal) {
            Map.Tiles[][] ret = new Map.Tiles[Math.max(first.length, second.length)][first[0].length + second[0].length];
            for(int row = 0; row < first.length; row++) {
                for(int col = 0; col < first[0].length; col++) {
                    ret[row][col] = first[row][col];
                }
            }
            for(int row = 0; row < second.length; row++) {
                for(int col = 0; col < second[0].length; col++) {
                    ret[row][first[0].length + col] = second[row][col];
                }
            }
            return ret;
        }else {
            Map.Tiles[][] ret = new Map.Tiles[first.length + second.length][Math.max(first[0].length, second[0].length)];
            for(int row = 0; row < first.length; row++) {
                for(int col = 0; col < first[0].length; col++) {
                    ret[row][col] = first[row][col];
                }
            }
            for(int row = 0; row < second.length; row++) {
                for(int col = 0; col < second[0].length; col++) {
                    ret[first.length + row][col] = second[row][col];
                }
            }
            return ret;
        }
    }

    public ArrayList<Map.Tiles[][]> split(Map.Tiles[][] array, boolean horizontal, int index) {
        ArrayList<Map.Tiles[][]> ret = new ArrayList<>();
        if(horizontal) {
            Map.Tiles[][] temp1 = new Map.Tiles[index][array[0].length];
            Map.Tiles[][] temp2 = new Map.Tiles[1][array[0].length];
            Map.Tiles[][] temp3 = new Map.Tiles[array.length - 1 - index][array[0].length];

            System.out.println(temp1.length + " " + temp2.length + " " + temp3.length);

            for(int row = 0; row < temp1.length; row++) {
                for(int col = 0; col < temp1[0].length; col++) {
                    temp1[row][col] = array[row][col];
                }
            }
            ret.add(temp1);

            for(int i = 0; i < array[0].length; i++) {
                temp2[0][i] = array[index][i];
            }
            ret.add(temp2);

            for(int row = 0; row < temp3.length; row++) {
                for(int col = 0; col < temp3[0].length; col++) {
                    temp3[row][col] = array[index+row+1][col];
                }
            }
            ret.add(temp3);
            return ret;
        }else {
            Map.Tiles[][] temp1 = new Map.Tiles[array.length][index];
            Map.Tiles[][] temp2 = new Map.Tiles[array.length][1];
            Map.Tiles[][] temp3 = new Map.Tiles[array.length][array[0].length - 1 - index];

            for(int row = 0; row < temp1.length; row++) {
                for(int col = 0; col < temp1[0].length; col++) {
                    temp1[row][col] = array[row][col];
                }
            }
            ret.add(temp1);

            for(int i = 0; i < temp2.length; i++) {
                temp2[i][0] = array[i][index];
            }
            ret.add(temp2);

            for(int row = 0; row < temp3.length; row++) {
                for(int col = 0; col < temp3[0].length; col++) {
                    temp3[row][col] = array[row][index+col+1];
                }
            }
            ret.add(temp3);

            return ret;
        }
    }
}
