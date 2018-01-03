/**
 * Created by Екатерина on 05.08.2017.
 */
public class Tron {
    int id;
    Point head, tail;

    boolean isAlive() {
        return tail.x != -1 && tail.y != -1;
    }

    void kill() {
        head = new Point(-1, -1);
        tail = new Point(-1, -1);
    }

    public Point getNextStep(Direction direction) {
        return head.move(direction);
    }

    public void move(Direction direction) {
        head = getNextStep(direction);
    }

    @Override
    public String toString() {
        return tail + " " + head;
    }
}
