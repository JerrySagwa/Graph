import java.util.ArrayList;
import java.util.Arrays;

public class GraphDFS {
    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> pre;
    private ArrayList<Integer> post;

    public GraphDFS(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        Arrays.fill(visited, false);
        pre = new ArrayList<>();
        post = new ArrayList<>();
        // 解决非连通图的问题
        for (int i = 0; i < G.V(); i++) {
            if (!visited[i])
                dfs(i);
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        pre.add(v);

        for (Integer w : G.adj(v)) {
            if (!visited[w])
                dfs(w);
        }
        post.add(v);
    }

    public Iterable<Integer> pre() {
        return this.pre;
    }

    public Iterable<Integer> post() {
        return this.post;
    }



    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        GraphDFS dfs = new GraphDFS(graph);
        System.out.println(dfs.pre());
        System.out.println(dfs.post());
    }

}
