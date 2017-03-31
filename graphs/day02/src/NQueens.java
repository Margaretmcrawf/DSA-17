import java.util.ArrayList;
import java.util.List;

public class NQueens {

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    public static List<char[][]> nQueensSolutions(int n) {
        List<char[][]> allBoards = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        nQueensBacktrack(n, board, allBoards, 0);
        return allBoards;
    }

    private static void nQueensBacktrack(int n, char[][] currentBoard, List<char[][]> allBoards, int row) {
        if (n == 0) {
            allBoards.add(copyOf(currentBoard));
            return;
        }
            for (int j = 0; j < currentBoard.length; j++) {
                if (currentBoard[row][j] == '.') {
                    if (isValid(currentBoard.length, row, j, currentBoard)) {
                        currentBoard[row][j] = 'Q';
                        nQueensBacktrack(n-1, currentBoard, allBoards, row + 1);
                        currentBoard[row][j] = '.';
                    }
                }
            }
    }

    private static boolean isValid(int n, int row, int column, char[][] currentBoard) {
        for (int i = 0; i < n; i++) {
            if (currentBoard[i][column] == 'Q') { return false; }
            if (currentBoard[row][i] == 'Q') { return false; }
        }

            int y = row - 1;
            int x = column - 1;
            while (y >= 0 && x >= 0) {
                if (currentBoard[y][x] == 'Q') return false;
                x--;
                y--;
            }
            y = row - 1;
            x = column + 1;
            while (y >= 0 && x < currentBoard[0].length) {
                if (currentBoard[y][x] == 'Q') return false;
                x++;
                y--;
            }
        return true;
    }

}
