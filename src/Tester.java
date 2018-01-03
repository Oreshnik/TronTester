import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Екатерина on 05.08.2017.
 */
public class Tester {

    public static void main(String args[]) throws Exception {
        int playerCount = Integer.valueOf(args[2]);
        int gamesCount = Integer.valueOf(args[3]);
        int[] places = new int[playerCount];
        int placesSum = 0;
        int playedGames = 0;

        for (int i = 0; i < gamesCount; i++) {
            Point[] startPoints = Game.generateStartPoints(playerCount);
            for (int testedPlace = 0; testedPlace < playerCount; testedPlace++) {
                playedGames ++;
                System.out.print("Game " + playedGames + ": ");
                int place = playGame(args, testedPlace, startPoints);
                places[place - 1]++;
                placesSum += place;
                System.out.print(", avg " + String.format("%.2f", (placesSum * 1.0 / playedGames)) + "\n\n");
            }
            System.out.println();
        }



        System.out.println("Results for bot " + args[0]);
        for (int i = 0; i < playerCount; i++) {
            System.out.println((i + 1) + " place " + places[i] + " (" + places[i] * 100.0 / playedGames + " %)");
        }
        System.out.println("Average place " + placesSum * 1.0 / playedGames);
    }

    private static int playGame(String[] args, int testedBot, Point[] startPoints) throws Exception {
        int playerCount = Integer.valueOf(args[2]);
        Game game = new Game(startPoints);
        printPositions(startPoints, testedBot);

        List<TronProcess> players = getPlayerProcess(args[0], args[1], playerCount, testedBot);

        while (game.activePlayer > 1 && game.trons[testedBot].isAlive()) {
            for (int i = 0; i < playerCount; i++) {
                Tron tron = game.trons[i];
                if (!tron.isAlive()) {
                    continue;
                }
                printToPlayer(players.get(i), playerCount + " " + i);
                for (int j = 0; j < playerCount; j++) {
                    printToPlayer(players.get(i), game.trons[j].toString());
                }
                players.get(i).flushOut();
                players.get(i).clearErrorStream();
                String input = readFromPlayer(players.get(i));
                Direction direction = null;
                try {
                    direction = Direction.valueOf(input);
                } catch (IllegalArgumentException e) {
                    killTron(game, tron);
                }
                if (direction != null) {
                    Point step = tron.getNextStep(direction);
                    if (game.isValidStep(step)) {
                        tron.move(direction);
                        game.setTronStep(tron);
                    } else {
                        killTron(game, tron);
                    }
                }
                game.frame++;
                if (game.activePlayer == 1 || !game.trons[testedBot].isAlive()) {
                    break;
                }
            }
        }

        int place = 0;
        if (game.trons[testedBot].isAlive()) {
            place = 1;
        } else {
            place = game.activePlayer + 1;
        }
        System.out.print("frame " + game.frame + "; place " + place);
        destroyAll(players);
        return place;
    }

    private static List<TronProcess> getPlayerProcess(String newPlayer, String oldPlayer, int playerCount, int testedBot) throws Exception{
        ArrayList<ProcessBuilder> playersBuilders = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            if (i == testedBot) {
                playersBuilders.add(new ProcessBuilder(newPlayer.split(" ")));
            } else {
                playersBuilders.add(new ProcessBuilder(oldPlayer.split(" ")));
            }
        }
        List<TronProcess> players = new ArrayList<>();
        for (ProcessBuilder builder : playersBuilders) {
            players.add(new TronProcess(builder.start()));
        }
        return players;
    }

    private static void killTron(Game game, Tron tron) {
        game.activePlayer--;
        tron.kill();
        game.clearTrons();
    }

    private static void printToPlayer(TronProcess process, String string) {
        process.print(string);
    }

    private static String readFromPlayer(TronProcess process) {
        return process.readLine();
    }

    private static void destroyAll(List<TronProcess> processes) throws Exception {
        for (TronProcess process : processes) {
            process.destroy();
        }
    }

    private static void printPositions(Point[] points, int position) {
        String string = "";
        for (Point point : points) {
            string += "(" + point + ")";
        }
        System.out.println(string.replace(" ", ",") + " position " + position);
    }
}
