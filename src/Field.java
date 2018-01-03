/**
 * Created by Екатерина on 05.08.2017.
 */
public class Field {
    int width, height;
    int[][] field;

    Field(int width, int height) {
        field = new int[width][height];
        this.width = width;
        this.height = height;
    }

    boolean cellIsFree(Point point) {
        return field[point.x][point.y] == 0;
    }

    void clearTron(int id) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (field[i][j] == id) {
                    field[i][j] = 0;
                }
            }
        }
    }

    void setTronStep(Tron tron) {
        field[tron.head.x][tron.head.y] = tron.id;
    }

    boolean containPoint(Point point) {
        return point.x >= 0 && point.y >= 0 && point.x < width && point.y < height;
    }
}
