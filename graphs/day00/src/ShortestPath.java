import java.util.*;

public class ShortestPath {

    public static List<Integer> shortestPath(Graph g, int v, int w) {
        if (distanceBetween(g, v, w) == -1) {
            return null;
        }

        Map<Integer, Integer> distances = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        distances.put(v, 0);
        queue.add(v);

        while (queue.size() != 0) {
            int node = queue.remove();
            for (int n : g.getNeighbors(node)) {
                if (!distances.containsKey(n)) {
                    distances.put(n, distances.get(node) + 1);
                    queue.add(n);
                }
            }
        }

        int current = w;
        List<Integer> path =  new LinkedList<>();
        while (current != v) {
            Iterable<Integer> neighbors = g.getNeighbors(current);
            int minDist = Integer.MAX_VALUE;
            int minVal = current;
            for (int n : neighbors) {
                if (distances.get(n) < minDist) {
                    minVal = n;
                    minDist = distances.get(n);
                }
            }
            path.add(0, current);
            current = minVal;

        }
        path.add(0, current);
        return path;

    }

    public static int distanceBetween(Graph g, int v, int w) {
        Map<Integer, Integer> distances = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        distances.put(v, 0);
        queue.add(v);

        while (queue.size() != 0) {
            int node = queue.remove();
            for (int n : g.getNeighbors(node)) {
                if (!distances.containsKey(n)) {
                    distances.put(n, distances.get(node) + 1);
                    queue.add(n);
                }
            }
        }
        if (distances.get(w) != null) {
            return distances.get(w);
        }
        return -1;
    }
}

