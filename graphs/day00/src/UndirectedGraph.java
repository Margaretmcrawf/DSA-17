import java.util.*;

public class UndirectedGraph implements Graph {
    private int[][] adjacencyMatrix;
    private int vertices;
    private int edges;

    public UndirectedGraph(int n) {
        adjacencyMatrix = new int[n][n];
        vertices = n;
        edges = 0;
    }

    @Override
    public void addEdge(int v, int w) {
        adjacencyMatrix[v][w] = 1;
        adjacencyMatrix[w][v] = 1;
        edges++;
    }

    @Override
    public List<Integer> vertices() {
    	// O(V)
        List<Integer> vert = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            vert.add(i);
        }
        return vert;
    }

    @Override
    public int numVertices() {
        //O(1) because it is a stored value.
        return vertices;
    }

    @Override
    public int numEdges() {
        //O(1) because it is a stored value.
    	return edges;
    }

    @Override
    public Iterable<Integer> getNeighbors(int v) {
    	// O(V)
        ArrayList<Integer> neighbors = new ArrayList<>();
        int[] vthrow = adjacencyMatrix[v];
        for (int i = 0; i < vthrow.length; i++) {
            if (vthrow[i] == 1) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    @Override
    public boolean hasEdgeBetween(int v, int w) {
        //O(1)
        if ((adjacencyMatrix[v][w] == 1) || (adjacencyMatrix[w][v] == 1)) {
            return true;
        }
        return false;
    }

}
