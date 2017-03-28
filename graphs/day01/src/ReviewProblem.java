public class ReviewProblem {

    public static int minimumLengthSubArray(int[] A, int desiredSum) {
        int min_length = A.length + 1;
        int start = 0;
        int end = 0;
        int sum = 0;
        while (end < A.length) {
            sum += A[end];
            end++;

            while (sum >= desiredSum) {
                if ((end - start) < min_length) {
                    min_length = end - start;
                }
                sum -= A[start];
                start++;
            }
        }

        if (min_length <= A.length) {
            return min_length;
        }
        return 0;
    }

}
