import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {

    private WeightedGraph G;
    // 标记顶点最短路径是否已找到
    private boolean[] visited;
    private int[] dis;
    private int[] pre;

    public Dijkstra(WeightedGraph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        this.dis = new int[G.V()];
        this.pre = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> dis[a] - dis[b]);
        // 0: source
        dis[0] = 0;

        pq.offer(0);

        while (!pq.isEmpty()) {
            int cur = pq.poll(); // 当前未访问的最短路节点
            if (visited[cur]) continue;

            visited[cur] = true;
            for (Integer w : G.adj(cur)) {
                if (!visited[w] && dis[w] > dis[cur] + G.getWeight(cur, w)) {
                    dis[w] = dis[cur] + G.getWeight(cur, w);
                    pre[w] = cur;
                    pq.offer(w);
                }
            }
        }
    }

    public void result() {
        for (int i = 0; i < dis.length; i++) {
            System.out.printf("%d: %d\n", i, dis[i]);
        }
    }

    public boolean isConnectedTo(int v) {
        G.validateVertex(v);
        return dis[v] != Integer.MAX_VALUE;
    }

    public int distTo(int v) {
        G.validateVertex(v);
        return dis[v];
    }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph("g.txt");
        Dijkstra dijkstra = new Dijkstra(wg);
        dijkstra.result();
    }
}
