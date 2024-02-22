import java.util.ArrayList;
import java.util.Collections;

// Strong Connected Component
public class SCC {

    private WeightedGraph rG;
    private WeightedGraph G;
    private boolean[] visited;
    private int[] comp;
    private int scccount = 0;

    public SCC(WeightedGraph G) {
        if (!G.directed())
            throw new IllegalArgumentException("Only works for directed graph!");
        this.G = G;
        this.rG = G.reverseGraph();
        this.visited = new boolean[G.V()];
        this.comp = new int[G.V()];
        // 设 A -> B
        // 遍历反图防止出现遍历顺序为
        // A1 -> B1...BN -> A2...AN
        GraphDFS dfs = new GraphDFS(this.rG);
        ArrayList<Integer> order = dfs.post();
        Collections.reverse(order); // 按照逆后序遍历的顺序遍历节点 必定出现 Bx 先于 A 的某个顶点遍历

        for (Integer v : order) {
            if (!visited[v]) {
                dfs(v, scccount);
                scccount++;
            }
        }
    }

    private void dfs(int v, int scccount) {
        visited[v] = true;
        comp[v] = scccount;

        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, scccount);
            }
        }
    }

    public int count() {
        return scccount;
    }

    public boolean isStronglyConnected(int v, int w) {
        return comp[v] == comp[w];
    }

    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[scccount];
        for (int i = 0; i < scccount; i++) {
            res[i] = new ArrayList<>();
        }

        for (int v = 0; v < G.V(); v++) {
            res[comp[v]].add(v);
        }

        return res;
    }

}
