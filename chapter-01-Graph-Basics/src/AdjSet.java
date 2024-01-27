import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class AdjSet {

    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    // O(E)
    public AdjSet(String filename) {
        File file = new File(filename);

        try {
            Scanner scanner = new Scanner(file);
            V = scanner.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("V must be non-negative!");
            adj =  new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
            }

            E = scanner.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("E must be non-negative!");

            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                // 无向图
                if (a == b) // 排除平行边和自环边
                    throw new IllegalArgumentException("Self-Loop is not allowed!");
                if (adj[a].contains(b))
                    throw new IllegalArgumentException("Parallel Edge is not allowed!"); // O(logV)
                adj[a].add(b);
                adj[b].add(a);
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
        return adj[v].contains(w);
    }

    // O(V)
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
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
            for (int w : adj[v]) { // v
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjSet adjSet = new AdjSet("g.txt");
        System.out.println(adjSet);
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
    }
}
