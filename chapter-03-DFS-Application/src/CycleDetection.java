import java.util.ArrayList;
import java.util.Arrays;

public class CycleDetection {
    private Graph G;
    private boolean[] visited;
    private boolean hasCycle;

    public CycleDetection(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        this.hasCycle = false;
        Arrays.fill(visited, false);
        // 解决非连通图的问题
        for (int i = 0; i < G.V(); i++) {
            if (!hasCycle && !visited[i])
                hasCycle = dfs(i, i);
        }
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;

        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) return true;
            } else if (w != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        CycleDetection cycleDetection = new CycleDetection(graph);
        System.out.println(cycleDetection.hasCycle());
    }

}
