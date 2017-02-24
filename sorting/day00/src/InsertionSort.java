
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * Best-case runtime: n
     * Worst-case runtime: n^2
     * Average-case runtime: n^2
     *
     * Space-complexity: 1
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++ ) {
            int key = array[i];
            int j = i - 1;
            while ((j >= 0) && array[j] > key) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
        return array;
    }
}
