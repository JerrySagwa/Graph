import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Path {
    private Graph G;
    private boolean[] visited;
    private int s;
    private int t;
    private int[] pre;

    public Path(Graph G, int s, int t) {
        G.validateVertex(s);
        G.validateVertex(t);
        this.G = G;
        this.s = s;
        this.t = t;
        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];
        Arrays.fill(visited, false);
        Arrays.fill(pre, -1);

        // 只需要研究 s 到其他顶点的路径, 不一定遍历整张图
        dfs(s, s);
    }

    public boolean isConnected() {
        return visited[t];
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected()) {
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

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;

        if (v == t) return true;

        for (Integer w : G.adj(v)) {
            if (!visited[w])
                if (dfs(w, v))
                    return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        Path path = new Path(graph, 0, 2);
        System.out.println(path.path());
    }

}
