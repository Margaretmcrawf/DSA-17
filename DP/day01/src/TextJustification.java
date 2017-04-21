import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    private static int len;

    public static List<Integer> justifyText(String[] w, int m) {
        len = m;
        List<Integer> lineIndices = new ArrayList<>();

        double[] memo = new double[w.length + 1];
        int[] pointers = new int[w.length];
        for (int i = 0; i < pointers.length; i++) {
            memo[i] = -1;
            pointers[i] = -1;
        }
        memo[w.length] = 0;
        justifyRecursive(w, m, 0, memo, pointers);

        int i = 0;
        while (i < w.length) {
            lineIndices.add(i);
            if (i == pointers[i]) { //prevents loops from happening with only one element
                return lineIndices;
            }
            i = pointers[i];
        }
        return lineIndices;
    }

    private static double justifyRecursive(String[] w, int m, int index, double[] memo, int[] pointers) {
        //iterate through all possible j and find the one with the min cost.
        if (memo[index] != -1) {
            return memo[index];
        }
        double minCost = Double.MAX_VALUE;
        int mindex = 0;
        for (int j = index+1; j <= w.length; j++) {
            double cost;
            if (cost(w, m, index, j) != Double.MAX_VALUE) {
                cost = cost(w, m, index, j) + justifyRecursive(w, m, j, memo, pointers);
            }
            else {
                cost = Double.MAX_VALUE;
            }
            if (cost < minCost) {
                minCost = cost;
                mindex = j;
            }
        }

        memo[index] = minCost;
        pointers[index] = mindex;
        return memo[index];
    }

    private static double cost(String[] w, int m, int a, int b) {
        int totalLength = 0;
        for (int i = a; i < b; i++) {
            totalLength += w[i].length() + 1;
        }
        totalLength -= 1;
        if (totalLength > m) {
            return Double.MAX_VALUE;
        }
        else {
            return Math.pow((m - totalLength), 3);
        }
    }

}