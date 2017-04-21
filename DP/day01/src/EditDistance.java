public class EditDistance {

    static int memo[][];

    public static int minEditDist(String a, String b) {
        memo = new int[a.length()+1][b.length()+1];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                memo[i][j] = -1;
            }
        }

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if ( i == 0 ) {
                    memo[i][j] = j;
                }

                else if ( j == 0 ) {
                    memo[i][j] = i;
                }

                else if (a.charAt(i-1) == b.charAt(j-1)) {
                    memo[i][j] = memo[i-1][j-1];
                }

                else {
                    memo[i][j] = 1 + min(memo[i][j-1],
                                        memo[i-1][j],
                                        memo[i-1][j-1]);
                }
            }
        }
        return memo[a.length()][b.length()];
    }

    static int min(int x,int y,int z)
    {
        if (x<y && x<z) return x;
        if (y<x && y<z) return y;
        else return z;
    }

}
