import java.util.HashSet;
import java.util.Random;

/**
 * Created by Екатерина on 05.08.2017.
 */
public class Game {
    static final int WIDTH = 30;
    static final int HEIGHT = 20;
    private Field field;
    Tron[] trons;
    private int playersCount;
    int activePlayer;
    int frame = 0;

    Game(Point[] startPoints) {
        this.playersCount = startPoints.length;
        activePlayer = playersCount;
        field = new Field(WIDTH, HEIGHT);
        trons = generateTrons(startPoints);
    }

    void clearTrons() {
        for (Tron tron : trons) {
            if (!tron.isAlive()) {
                field.clearTron(tron.id);
            }
        }
    }

    void setTronStep(Tron tron) {
        field.setTronStep(tron);
    }

    boolean isValidStep(Point step) {
        return field.containPoint(step) && field.cellIsFree(step);
    }

    private Tron[] generateTrons(Point[] startPoints) {
        Tron[] trons = new Tron[playersCount];
        for (int i = 0; i < playersCount; i++) {
            Tron tron = new Tron();
            tron.id = i + 1;
            tron.head = new Point(startPoints[i].x, startPoints[i].y);
            tron.tail = new Point(startPoints[i].x, startPoints[i].y);
            field.setTronStep(tron);
            trons[i] = tron;
        }
        return trons;
    }

    static Point[] generateStartPoints(int playersCount) {
        Random random = new Random();
        HashSet<Point> set = new HashSet<>();
        while (set.size() < playersCount) {
            Point point = new Point(random.nextInt(WIDTH), random.nextInt(HEIGHT));
            set.add(point);
        }
        return set.toArray(new Point[playersCount]);
    }
}
