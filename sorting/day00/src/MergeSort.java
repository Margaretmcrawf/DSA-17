import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     *
     * Best-case runtime: nlogn
     * Worst-case runtime: nlogn
     * Average-case runtime: nlogn
     *
     * Space-complexity: n
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        if (array.length == 1) {
            return array;
        }
        if (array.length <= INSERTION_THRESHOLD) {
            InsertionSort insertionSort = new InsertionSort();
            insertionSort.sort(array);
            return array;
        }
        else {
            int[] array1 = Arrays.copyOfRange(array, 0, array.length/2);
            int[] array2 = Arrays.copyOfRange(array, array.length/2, array.length);
            return merge(sort(array1), sort(array2));
        }
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     */
    public int[] merge(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        int k = 0;

        int[] merged = new int[a.length + b.length];
        while ((i < a.length) && (j < b.length)) {
            if (a[i] < b[j]) {
                merged[k] = a[i];
                i++;
            } else {
                merged[k] = b[j];
                j++;
            }
            k++;
        }
        if (i < a.length) {
            while (i < a.length) {
                merged[k] = a[i];
                i++;
                k++;
            }
        } else if (j < b.length) {
            while (j < b.length) {
                merged[k] = b[j];
                j++;
                k++;
            }
        }
        return merged;
    }

}
