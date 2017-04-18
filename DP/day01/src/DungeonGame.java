import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class DungeonGame {
    private static int xMax;
    private static int yMax;

    public static int minInitialHealth(int[][] map) {
        xMax = map.length-1;
        yMax = map[0].length-1;
        int memo[][] = new int[xMax+1][yMax+1];
        for (int i = 0; i < xMax+1; i++) {
            for (int j = 0; j < yMax+1; j++) {
                memo[i][j] = -1;
            }
        }
        return minHealthRecursive(map, 0, 0, memo);
    }

    private static int minHealthRecursive(int[][] map, int xInd, int yInd, int[][] memo) {
        if ((xInd == xMax) && (yInd == yMax)) { //at the final value
            return max(-1*map[xInd][yInd]+1, 1);
        }
        else if (memo[xInd][yInd] != -1) {
            return memo[xInd][yInd];
        }
        else {
            int cost;
            if (xInd == xMax) {
                cost = max(-1*map[xInd][yInd] + minHealthRecursive(map, xInd, yInd+1, memo), 1);
            }
            else if (yInd == yMax) {
                cost = max(-1 * map[xInd][yInd] + minHealthRecursive(map, xInd+1, yInd, memo), 1);
            } else {
                cost = max(-1*map[xInd][yInd] + min(minHealthRecursive(map, xInd + 1, yInd, memo),
                        minHealthRecursive(map, xInd, yInd + 1, memo)),
                        1);
            }
            memo[xInd][yInd] = cost;
            return cost;
        }
    }
}
