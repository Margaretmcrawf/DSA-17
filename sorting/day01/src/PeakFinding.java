public class PeakFinding {

    // Return -1 is left is higher, 1 if right is higher, 0 if peak
    private static int peak(int i, int[] nums) {
        if (i>0 && nums[i] < nums[i-1])
            return -1;
        if (i<nums.length-1 && nums[i] < nums[i+1])
            return 1;
        return 0;
    }

    // Return -1 is left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x>0 && nums[y][x] < nums[y][x-1])
            return -1;
        if (x<nums[0].length-1 && nums[y][x] < nums[y][x+1])
            return 1;
        return 0;
    }

    // Return -1 is up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y>0 && nums[y][x] < nums[y-1][x])
            return -1;
        if (y<nums.length-1 && nums[y][x] < nums[y+1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }
    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums){
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (hi+lo)/2;
            int direction = peak(mid, nums);
            if (direction == 0) return mid;
            else if (direction == -1) hi = mid;
            else if (direction == 1) lo = mid+1;
        }
        return -1;
    }

    public static int[] findTwoDPeak(int[][] nums) {

        return findTwoDPeakRange(nums, 0, nums.length, 0, nums.length);
    }

    //lo and hi are max and min y values.
    public static int[] findTwoDPeakRange(int[][] nums, int loy, int hiy, int lox, int hix) {
        int xMiddle = (hix + lox)/2;
        int yMiddle = (hiy + loy)/2;

        int maxYindex = maxYIndex(xMiddle, loy, hiy, nums);
        int xside = peakX(xMiddle, maxYindex, nums);
        // check if there is a peak at the middle x position and the max y
        if (xside == 0) {
            int[] newarr = {maxYindex, xMiddle};
            return newarr;
        }

        //if not, go left or right
        else if (xside == 1) { //right is higher
            int maxXIndex = maxXIndex(yMiddle, xMiddle + 1, hix, nums);
            //if this is a peak in the y direction too, it must be a 2D peak, return.
            int yside = peakY(maxXIndex, yMiddle, nums);
            if (yside == 0) {
                int[] newarr = {yMiddle, maxXIndex};
                return newarr;
            } else {
                if (yside == 1) {
                    return findTwoDPeakRange(nums, yMiddle + 1, hiy, xMiddle + 1, hix);
                } else {
                    return findTwoDPeakRange(nums, loy, yMiddle, xMiddle + 1, hix);
                }
            }
        }

        else { //left is higher
            int maxXIndex = maxXIndex(yMiddle, lox, xMiddle, nums);
            //if this is a peak in the y direction too, it must be a 2D peak, return.
            int yside = peakY(maxXIndex, yMiddle, nums);
            if (yside == 0) {
                int[] newarr = {yMiddle, maxXIndex};
                return newarr;
            } else {
                if (yside == 1) {
                    return findTwoDPeakRange(nums, yMiddle + 1, hiy, lox, xMiddle);
                } else {
                    return findTwoDPeakRange(nums, loy, yMiddle, lox, xMiddle);
                }
            }
        }
    }

}
