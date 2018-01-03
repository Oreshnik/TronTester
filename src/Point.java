/**
 * Created by Екатерина on 05.08.2017.
 */
public class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    Point move(Direction direction) {
        Point point = new Point(x, y);
        if (direction.equals(Direction.LEFT)) {
            point.x--;
        } else if (direction.equals(Direction.RIGHT)) {
            point.x++;
        } else if (direction.equals(Direction.DOWN)) {
            point.y++;
        } else if (direction.equals(Direction.UP)) {
            point.y--;
        }
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
