import java.util.*;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size()/2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size()/2-1))/2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        //got some help from http://www.dsalgo.com/2013/02/RunningMedian.php.html
        PriorityQueue<Integer> lowerQueue = new PriorityQueue<>(
                20,new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {

                return -o1.compareTo(o2);
            }

        });

        PriorityQueue<Integer> upperQueue = new PriorityQueue<>();
        upperQueue.add(Integer.MAX_VALUE);
        lowerQueue.add(Integer.MIN_VALUE);

        for (int i = 0; i < inputStream.length; i++) {
            if(inputStream[i] >=upperQueue.peek())
                upperQueue.add(inputStream[i]);
            else
                lowerQueue.add(inputStream[i]);

            if(upperQueue.size()-lowerQueue.size()==2)
                lowerQueue.add(upperQueue.poll());
            else if(lowerQueue.size()-upperQueue.size()==2)
                upperQueue.add(lowerQueue.poll());

            if (upperQueue.size()==lowerQueue.size()) {
                runningMedian[i] = (upperQueue.peek()+lowerQueue.peek())/2.0;
            } else if (upperQueue.size()>lowerQueue.size()) {
                runningMedian[i] = upperQueue.peek();
            } else {
                runningMedian[i] = lowerQueue.peek();
            }
        }

        return runningMedian;
    }

}
