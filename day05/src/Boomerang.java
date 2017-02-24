import java.util.ArrayList;
import java.util.Map;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        int[][] dists = new int[points.length][points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = i; j < points.length; j++) {
                dists[i][j] = getSquaredDistance(points[i], points[j]);
                dists[j][i] = getSquaredDistance(points[i], points[j]);
            }
        }
        int total = 0;
        for (int i = 0; i < points.length; i++) {
            int[] eq = dists[i];
            Map<Integer, Integer> counts = getCountMap(eq);
            for (int dist : counts.keySet()) {
                total += (counts.get(dist) * (counts.get(dist) - 1));
            }

        }
        return total;
    }

    public static Map<Integer, Integer> getCountMap(int[] arr) {
        Map<Integer, Integer> map = new MyLinearMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) != null) {
                int prev = map.get(arr[i]);
                prev++;
                map.put(arr[i], prev);
            } else {
                map.put(arr[i], 1);
            }
        }
        return map;
    }

    private static int getSquaredDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        return dx*dx + dy*dy;
    }
}
