import java.util.ArrayList;

public class FindBridges {

    private class Edge {
        private int v, w;
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return v + " -> " + w;
        }
    }

    private Graph g;
    private boolean[] visited;
    private int[] ord;
    private int[] low;
    private int order = 0;
    private ArrayList<Edge> res;

    public FindBridges(Graph g) {
        this.g = g;
        visited = new boolean[g.V()];
        ord = new int[g.V()];
        low = new int[g.V()];
        res = new ArrayList<>();

        for (int v = 0; v < g.V(); v++) {
            if (!visited[v])
                dfs(v, v);
        }
    }

    // 从 v 出发，沿着 dfs 顺序 (不沿着 v->parent 原路返回), 记录 v 能到达的点的最小 ord
    private void dfs(int v, int parent) {
        visited[v] = true;
        ord[v] = order++;
        low[v] = ord[v];

        for (Integer w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                if (low[w] > ord[v]) {
                    res.add(new Edge(v, w));
                }
                low[v] = Math.min(low[v], low[w]);
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public ArrayList<Edge> res() {
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        FindBridges findBridges = new FindBridges(g);
        System.out.println(findBridges.res());
    }
}


















































