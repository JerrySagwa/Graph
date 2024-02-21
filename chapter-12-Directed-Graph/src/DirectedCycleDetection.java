public class DirectedCycleDetection {

    private WeightedGraph G;
    private boolean hasCycle;
    private boolean[] path;
    private boolean[] visited;

    public DirectedCycleDetection(WeightedGraph G) {
        if (!G.directed())
            throw new IllegalArgumentException("Only works for directed graph!");
        this.path = new boolean[G.V()];
        this.visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                if (dfs(v)) {
                    hasCycle = true;
                    return;
                }
        }

    }

    private boolean dfs(int v) {
        path[v] = true;
        visited[v] = true;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w)) return true;
            } else if (path[w]) return true;
        }

        // 遍历结束后 v 不再在 path 上
        path[v] = false;
        return false;
    }
}
