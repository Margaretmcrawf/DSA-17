import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        // TODO
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] + 100;
        }
        RadixSort rs = new RadixSort();
        rs.radixSort(A, 10);
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] - 100;
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }
    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        int b = 26;
        LinkedList<String>[] L = new LinkedList[b];
        for (int i = 0; i < b; i++)
            L[i] = new LinkedList<>();
        for (String i : A) {
            int digit = getNthCharacter(i, n);
            L[digit].add(i);
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            for (String i : list) {
                A[j] = i;
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for (int i = 0; i < stringLength; i++) {
            countingSortByCharacter(S, i);
        }
    }

}
