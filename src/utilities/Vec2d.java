package utilities;

public class Vec2d {
    public int col;
    public int row;
    public Vec2d(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public void add(Vec2d other) {
        col += other.col;
        row += other.row;
    }
}
