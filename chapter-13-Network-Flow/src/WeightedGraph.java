import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
// 有向带权图
public class WeightedGraph {

    private int V;
    private int E;
    // key: vertex, value: weight
    private TreeMap<Integer, Integer>[] adj;
    private boolean directed;

    // O(E)
    public WeightedGraph(String filename, boolean directed) {
        this.directed = directed;
        File file = new File(filename);

        try {
            Scanner scanner = new Scanner(file);
            V = scanner.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("V must be non-negative!");
            adj = new TreeMap[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap<>();
            }
            this.E = 0;
            int e = scanner.nextInt();
            if (e < 0)
                throw new IllegalArgumentException("E must be non-negative!");

            for (int i = 0; i < e; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                int weight = scanner.nextInt();
                addEdge(a, b, weight);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public WeightedGraph(TreeMap<Integer, Integer>[] adj, boolean directed) {
        this.directed = directed;
        this.adj = adj;
        this.V = adj.length;
        for (int i = 0; i < adj.length; ++i) {
            this.E += adj[i].size();
        }
    }

    public WeightedGraph(int V, boolean directed) {
        this.V = V;
        this.directed = directed;
        this.E = E;
        this.adj = new TreeMap[V];
        for (int i = 0; i < adj.length; ++i) {
            adj[i] = new TreeMap<>();
        }
    }

    public void addEdge(int a, int b, int weight) {
        validateVertex(a);
        validateVertex(b);
        if (a == b) // 排除平行边和自环边
            throw new IllegalArgumentException("Self-Loop is not allowed!");
        // 简单图
        if (adj[a].containsKey(b))
            throw new IllegalArgumentException("Parallel Edge is not allowed!"); // O(logV)
        adj[a].put(b, weight);
        if (!directed) // 有向图就不需要添加了
            adj[b].put(a, weight);
        this.E++;
    }

    public WeightedGraph(String filename) { // 默认为无向图
        this(filename, false);
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

    // 将 v -> w 权值设置为 newWeight
    public void setWeight(int v, int w, int newWeight) {
        if (!hasEdge(v, w))
            throw new IllegalArgumentException("No edge between v w");

        adj[v].put(w, newWeight);
        if (!directed) {
            adj[w].put(v, newWeight);
        }
    }

    public boolean directed() {
        return directed;
    }

    public int getWeight(int v, int w) {
        if (hasEdge(v, w))
            return adj[v].get(w);
        throw new IllegalArgumentException(String.format("No edge %d %d", v, w));
    }

    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (adj[v].containsKey(w)) {
            E--;
        }
        adj[v].remove(w);
        if (!directed) adj[w].remove(v);
    }

    public int degree(int v) {
        if (directed)
            throw new IllegalArgumentException("Only works for directed graph!");
        validateVertex(v);
        return adj[v].size();
    }

    public WeightedGraph reverseGraph() {
        TreeMap<Integer, Integer>[] radj = new TreeMap[adj.length];
        for (int i = 0; i < radj.length; i++) {
            radj[i] = new TreeMap<>();
        }

        for (int i = 0; i < adj.length; i++) {
            for (Map.Entry<Integer, Integer> entry : adj[i].entrySet()) {
                radj[entry.getKey()].put(i, entry.getValue());
            }
        }

        return new WeightedGraph(radj, true);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d, directed = %b\n", V, E, directed));
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
        WeightedGraph weightedGraph = new WeightedGraph("wg.txt", true);
        System.out.println(weightedGraph);
    }
}
