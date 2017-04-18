public class EditDistance {

    static int memo[][];

    public static int minEditDist(String a, String b) {
        memo = new int[a.length()+1][b.length()+1];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return minEditRecursive(a, b, a.length(), b.length());
    }

    static int min(int x,int y,int z)
    {
        if (x<y && x<z) return x;
        if (y<x && y<z) return y;
        else return z;
    }

    private static int minEditRecursive(String a, String b, int aInd, int bInd) {
        //base cases
        if (aInd == 0) {
            return bInd;
        }
        else if (bInd == 0) {
            return aInd;
        }

//        //memoized case
//        if (memo[aInd][bInd] != -1) {
//            return memo[aInd][bInd];
//        }

        //recursive cases
        int cost;
        if (a.charAt(aInd-1) == b.charAt(bInd-1)) {
            cost = minEditRecursive(a, b, aInd-1, bInd-1);
        }

        else {
            cost = 1 + min( minEditRecursive(a, b, aInd, bInd-1),
                    minEditRecursive(a, b, aInd-1, bInd-1),
                    minEditRecursive(a, b, aInd-1, bInd-1));

        }

        //memoize
        memo[aInd][bInd] = cost;

        //return
        return cost;

    }

}
