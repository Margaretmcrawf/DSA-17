import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * Best-case runtime: nlogn
     * Worst-case runtime: n^2
     * Average-case runtime: nlogn
     *
     * Space-complexity: logn
     */

    // Shuffle method courtesy of http://www.vogella.com/tutorials/JavaAlgorithmsShuffle/article.html

    public void shuffleArray(int[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }

    void swap(int[] a, int i, int change) {
        int helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }

    @Override
    public int[] sort(int[] array) {
        shuffleArray(array); //shuffling the array will avoid the case where the array is already sorted.
        if (array.length < INSERTION_THRESHOLD) {
            InsertionSort insertionSort = new InsertionSort();
            return insertionSort.sort(array);
        } else {
            quickSort(array, 0, array.length-1);
            return array;
        }
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param low The beginning index of the subarray being considered (inclusive)
     * @param high The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int low, int high) {

        if (low < high) {
            int p = partition(a, low, high);
            quickSort(a, low, p - 1);
            quickSort(a, p+1, high);
        }
    }

    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param low The beginning index of the subarray being considered (inclusive)
     * @param high The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int low, int high) {
        //using the Hoare partition scheme.

        int pivot = array[low];
        int i = low + 1;
        int j = high;
        while (i <= j) {
            while ((array[i] < pivot) && (i < array.length -1)) {
                i++;
            }

            while ((array[j] > pivot) && (j > 0)) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                i++;
                j--;
            }
        }

        array[low] = array[i-1];
        array[i-1] = pivot;
        return i-1;
    }
}
