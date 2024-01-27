import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// adjacency matrix
public class AdjMatrix {

    private int V;
    private int E;
    private int[][] adj;

    // O(E)
    public AdjMatrix(String filename) {
        File file = new File(filename);

        try {
            Scanner scanner = new Scanner(file);
            V = scanner.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("V must be non-negative!");
            adj = new int[V][V];

            E = scanner.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("E must be non-negative!");

            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                // 无向图
                if (a == b || adj[a][b] == 1) // 排除平行边和自环边
                    throw new IllegalArgumentException("Parallel edge or self-loop is not allowed!");
                adj[a][b] = 1;
                adj[b][a] = 1;
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
        return adj[v][w] == 1;
    }

    // O(V)
    public ArrayList<Integer> adj(int v) {
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1)
                res.add(i);
        }
        return res;
    }

    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix);
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
    }
}


















































