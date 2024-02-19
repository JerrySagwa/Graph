public class Floyed {

    private WeightedGraph G;
    private int[][] dis;
    private boolean hasNegativeCycle;

    public Floyed(WeightedGraph G) {
        this.G = G;
        this.dis = new int[G.V()][G.V()];

        // 初始化
        for (int v = 0; v < G.V(); v++) {
            for (int w = 0; w < G.V(); w++) {
                if (v == w)
                    dis[v][w] = 0;
                else if (G.hasEdge(w, v))
                    dis[v][w] = G.getWeight(w, v);
                else
                    dis[v][w] = Integer.MAX_VALUE;
            }
        }

        for (int t = 0; t < G.V(); t++) {
            for (int v = 0; v < G.V(); v++) {
                for (int w = 0; w < G.V(); w++) {
                    if (dis[v][t] + dis[t][w] < dis[v][w] && dis[v][t] != Integer.MAX_VALUE && dis[t][w] != Integer.MAX_VALUE)
                        dis[v][w] = dis[v][t] + dis[t][w];
                }
            }
        }

        // negative cycle detection
        for (int v = 0; v < G.V(); v++) {
            if (dis[v][v] < 0)
                hasNegativeCycle = false;
        }
    }

    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }

    public int dist(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return dis[v][w];
    }
}








































