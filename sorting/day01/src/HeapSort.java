public class HeapSort extends SortAlgorithm {
    private int size; //the amount of the array that is heap.
    private int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Recursively corrects the position of element indexed i: check children, and swap with larger child if necessary.
    private void heapify(int i) {
        int rightChild = rightChild(i);
        int leftChild = leftChild(i);
        if (rightChild < size) {
            int max = (heap[leftChild] > heap[rightChild]) ? leftChild : rightChild;
            if (heap[i] < heap[max]) {
                swap(heap, i, max);
                heapify(max);
            }
        } else if (leftChild < size) { //case where only one child
            if (heap[i] < heap[leftChild]) {
                swap(heap, i, leftChild);
                heapify(leftChild);
            }
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position.
    public void buildHeapFrom(int[] array) {
        this.heap = array;
        this.size = array.length;
        for (int j = size/2 -1; j >= 0; j--) {
            heapify(j);
        }
    }

    /**
     * Best-case runtime: nlogn
     * Worst-case runtime: nlogn
     * Average-case runtime: nlogn
     *
     * Space-complexity: logn?
     */
    @Override
    public int[] sort(int[] array) {
        buildHeapFrom(array);
        for (int i=size-1; i>0; i--) {
            swap(heap, i, 0);
            size--;
            heapify(0);

        }
        return heap;
    }
}
