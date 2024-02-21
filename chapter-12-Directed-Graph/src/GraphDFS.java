import java.util.ArrayList;

public class GraphDFS {
    private ArrayList<Integer> post;
    private boolean[] visited;
    private WeightedGraph G;

    public GraphDFS(WeightedGraph G) {
        this.G = G;
        this.post = new ArrayList<>();
        this.visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                dfs(v);
        }
    }

    private void dfs(int v) {
        visited[v] = true;

        for (Integer w : G.adj(v)) {
            if (!visited[w])
                dfs(w);
        }

        post.add(v);
    }

    public ArrayList<Integer> post() {
        return post;
    }
}
