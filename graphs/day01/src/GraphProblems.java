import java.util.*;

public class GraphProblems {

    public static boolean connected(Graph g, int v, int u) {
        boolean[] visited = new boolean[g.numVertices()];
        dfsConnected(g, v, u, visited);
        if (u > g.numVertices() - 1) {
            return false;
        }
        return visited[u];
    }

    private static void dfsConnected(Graph G, int v, int w, boolean[] marked) {
        marked[v] = true;
        for (int x : G.getNeighbors(v)) {
            if (!marked[x]) {
                dfsConnected(G, x, w, marked);
            }
        }
    }

    public static List<Integer> topologicalOrder(Digraph g) {
        LinkedList<Integer> topo = new LinkedList<>();
        int[] visited = new int[g.numVertices()];
        for (int n : g.vertices()) {
            if (visited[n] == 0) {
                dfsTopo(g, n, visited, topo);
            }
        }
        return topo;
    }

    private static void dfsTopo(Graph G, int v, int[] marked, LinkedList<Integer> topo) {
        if (marked[v] == 0) {
            marked[v] = 1;
        }
        for (int x : G.getNeighbors(v)) {
            if (marked[x] == 0) {
                dfsTopo(G, x, marked, topo);
            }
        }
        if (marked[v] == 1) {
            topo.addFirst(v);
        }
        marked[v] = 2;
    }

    public static boolean hasCycle(UndirectedGraph g) {
        Set<Integer> visited = new HashSet<>();
        for (int n : g.vertices()) {
            if (!visited.contains(n)) {
                if (dfsCycle(g, -1, n, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfsCycle(UndirectedGraph g, int prev, int v, Set<Integer> visited) {
        visited.add(v);
        for (int n : g.getNeighbors(v)) {
            if (!visited.contains(n)) {
                if (dfsCycle(g, v, n, visited)) {
                    return true;
                }
            } else if ((n != prev)) {
                return true;
            }
        }
        return false;
    }

}