import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SingleSourcePath {
    private Graph G;
    private boolean[] visited;
    private int s;
    private int[] pre;

    public SingleSourcePath(Graph G, int s) {
        G.validateVertex(s);
        this.G = G;
        this.s = s;
        this.visited = new boolean[G.V()];
        Arrays.fill(visited, false);
        Arrays.fill(pre, -1);

        // 只需要研究 s 到其他顶点的路径, 不一定遍历整张图
        dfs(s, s);
    }

    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) {
            return res;
        }

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(cur);
        Collections.reverse(res);
        return res;
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        pre[s] = parent;
        for (Integer w : G.adj(v)) {
            if (!visited[w])
                dfs(w, v);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
    }

}
