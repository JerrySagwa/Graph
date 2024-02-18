import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class WeightedGraph {

    private int V;
    private int E;
    // key: vertex, value: weight
    private TreeMap<Integer, Integer>[] adj;

    // O(E)
    public WeightedGraph(String filename) {
        File file = new File(filename);

        try {
            Scanner scanner = new Scanner(file);
            V = scanner.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("V must be non-negative!");
            adj =  new TreeMap[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap<>();
            }

            E = scanner.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("E must be non-negative!");

            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                int weight = scanner.nextInt();
                // 无向图
                if (a == b) // 排除平行边和自环边
                    throw new IllegalArgumentException("Self-Loop is not allowed!");
                // 简单图
                if (adj[a].containsKey(b))
                    throw new IllegalArgumentException("Parallel Edge is not allowed!"); // O(logV)
                adj[a].put(b, weight);
                adj[b].put(a, weight);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Vertex " + v + " is invalid!");
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    // O(1)
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].containsKey(w);
    }

    // O(V)
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v].keySet();
    }

    public int getWeight(int v, int w) {
        if (hasEdge(v, w))
            return adj[v].get(w);
        throw new IllegalArgumentException(String.format("No edge %d %d", v, w));
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(v + " : ");
            for (int w : adj[v].keySet()) { // v;
                sb.append(String.format("(%d: %d)", w, adj[v].get(w)));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("g.txt");
        System.out.println(weightedGraph);
    }
}
