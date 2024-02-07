
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {

    private ArrayList<Integer> order;
    private boolean[] visited;
    private Graph g;

    public GraphBFS(Graph g) {
        this.g = g;
        this.order = new ArrayList<>();
        this.visited = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!visited[i])
                bfs(i);
        }
    }

    private void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        // set true after enqueue
        visited[v] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            order.add(cur);
            for (Integer w : g.adj(cur)) {
                if (!visited[w]) {
                    q.offer(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> result() {
        return this.order;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        GraphBFS bfs = new GraphBFS(graph);
        System.out.println("BFS order : " + bfs.result());
    }
}








































