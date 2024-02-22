import java.util.*;

// 使用 Edmonds-Karp算法 BFS 求解Max Flow问题
public class MaxFlow {

    private WeightedGraph network;
    private WeightedGraph rG; // residual graph
    private int result;
    private int s, t;

    public MaxFlow(WeightedGraph network, int s, int t) {
        this.network = network;
        this.s = s;
        this.t = t;

        if (!network.directed())
            throw new IllegalArgumentException("Only works for directed graph!");
        if (network.V() < 2)
            throw new IllegalArgumentException("Must have at least two vertexes!");
        if (s == t)
            throw new IllegalArgumentException("s cannot be the same as t!");

        this.rG = new WeightedGraph(network.V(), true);

        for (int v = 0; v < network.V(); v++) {
            for (int w : network.adj(v)) {
                int weight = network.getWeight(v, w);
                this.rG.addEdge(v, w, weight);
                this.rG.addEdge(w, v, 0);
            }
        }

        while (true) {
            ArrayList<Integer> augPath = getAugmentingPath();
            if (augPath.size() == 0) // 找不到了
                break;

            // 计算路径最小值
            int f = Integer.MAX_VALUE;
            // 更新result
            for (int i = 0; i < augPath.size() - 1; ++i) {
                f = Math.min(rG.getWeight(augPath.get(i), augPath.get(i + 1)), f);
            }
            result += f;
            // 更新rG
            for (int i = 0; i < augPath.size() - 1; ++i) {
                int v = augPath.get(i);
                int w = augPath.get(i + 1);

                this.rG.setWeight(v, w, this.rG.getWeight(v, w) - f);
                this.rG.setWeight(w, v, this.rG.getWeight(w, v) + f);
            }
        }

    }

    // 在当前残量图中找到一条增广路径
    private ArrayList<Integer> getAugmentingPath() {
        int[] pre = new int[rG.V()];
        Arrays.fill(pre, -1);
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == t) break;
            for (Integer w : rG.adj(cur)) {
                if (pre[w] == -1 && rG.getWeight(cur, w) > 0) {
                    pre[w] = cur;
                    q.offer(w);
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        if (pre[t] == -1) // 没有路径
            return result;

        int v = t;
        while (v != s) {
            result.add(v);
            v = pre[v];
        }

        result.add(s);
        Collections.reverse(result);
        return result;
    }

    public int result() {
        return result;
    }

    // 返回 v -> w 边的流量
    public int flow(int v, int w) {
        if (!rG.hasEdge(v, w))
            throw new IllegalArgumentException("No edge between v -> w");
        return rG.getWeight(w, v);
    }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph("network.txt", true);
        MaxFlow maxFlow = new MaxFlow(wg, 0, 3);
        System.out.println(maxFlow.result());
        for (int v = 0; v < wg.V(); v++) {
            for (Integer w : wg.adj(v)) {
                System.out.println(String.format("%d-%d: %d / %d", v, w, maxFlow.flow(v, w), wg.getWeight(v, w)));
            }
        }
    }
}








































