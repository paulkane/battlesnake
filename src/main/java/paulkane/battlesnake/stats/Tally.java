package paulkane.battlesnake.stats;

import java.util.HashMap;
import java.util.Map;

public class Tally {

    private static final Map<String, Integer> wins = new HashMap<>();
    private static String gameId = "";

    public static void addWin(String gameId, String snakeName) {

        if (!Tally.gameId.equals(gameId)) {

            if (wins.containsKey(snakeName)) {
                wins.put(snakeName, wins.get(snakeName) + 1);
            } else {
                wins.put(snakeName, 1);
            }
            Tally.gameId = gameId;
        }
    }

    public static void printTally() {

        System.out.println("========= Tally =========");
        for (Map.Entry<String, Integer> entry : wins.entrySet()) {
            System.out.printf("%s = %s%n", entry.getKey(), entry.getValue());
        }
    }
}
