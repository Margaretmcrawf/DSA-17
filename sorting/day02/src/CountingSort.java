import javax.swing.*;

public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: k + n
     */
    static void countingSort(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }

        int[] buckets = new int[max + 1];
        for (int i = 0; i < A.length; i++) {
             buckets[A[i]] = buckets[A[i]] + 1;
        }

        int index = 0;
        int bucket = 0;
        while (index < A.length) {
            if (buckets[bucket] != 0) {
                for (int j = 0; j < buckets[bucket]; j++) {
                    A[index] = bucket;
                    index++;
                }
            }
            bucket++;
        }
    }

}
