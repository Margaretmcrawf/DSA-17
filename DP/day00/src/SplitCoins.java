import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class SplitCoins {

    static int[][] coinsmemo;
    static int totalValue;
    static int size;

    public static int splitCoins(int[] coins) {
        size = coins.length;
        totalValue = 0;
        for (int i = 0; i < size; i++) {
            totalValue += coins[i];
        }

        int memo[][] = new int[size][totalValue+1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < totalValue+1; j++) {
                memo[i][j] = -1;
            }
        }

        return splitCoinsRecursive(coins, 0, 0, memo);
    }

    private static int splitCoinsRecursive(int[] coins, int index, int pile1sum, int[][] memo) {
        if (index == size) {
            //you've sorted all the coins woooo
            return abs(totalValue - 2*pile1sum);
        }

        //check if in memo
        if (memo[index][pile1sum] != -1) {
            return memo[index][pile1sum];
        }

        //what's the sum if you don't add the current coin?
        int sumwith = splitCoinsRecursive(coins, index+1, pile1sum, memo);

        //what's the sum if you do?
        pile1sum += coins[index];
        int sumwithout = splitCoinsRecursive(coins, index+1, pile1sum, memo);
        pile1sum -= coins[index];

        //Add the better one to the memo
        int diff =  min(sumwith, sumwithout);
        memo[index][pile1sum] = diff;

        //return
        return diff;
    }
}
