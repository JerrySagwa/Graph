import java.util.*;

// 无权图单源最短路径
public class ShortestSingleSourcePath {

    private ArrayList<Integer> order;
    private int[] pre;
    private Graph g;
    // s: source vertex
    private int s;
    private int[] dis;

    public ShortestSingleSourcePath(Graph g, int s) {
        g.validateVertex(s);
        this.g = g;
        this.order = new ArrayList<>();
        this.pre = new int[g.V()];
        this.dis = new int[g.V()];
        this.s = s;
        Arrays.fill(pre, -1);
        Arrays.fill(dis, -1);
        bfs(s, s);
        for (int i = 0; i < g.V(); i++) {
            System.out.print(dis[i] + " ");
        }
        System.out.println();
    }

    private void bfs(int v, int parent) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        // set true after enqueue
        pre[v] = parent;
        dis[v] = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            order.add(cur);
            for (Integer w : g.adj(cur)) {
                if (pre[w] == -1) {
                    q.offer(w);
                    pre[w] = cur;
                    dis[w] = dis[cur] + 1;
                }
            }
        }
    }

    public Iterable<Integer> path(int t) {
        this.g.validateVertex(t);
        ArrayList<Integer> res = new ArrayList<>();
        if (pre[t] == -1) // unreachable
            return res;
        while (pre[t] != s) {
            res.add(t);
            t = pre[t];
        }

        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        ShortestSingleSourcePath sssp = new ShortestSingleSourcePath(graph, 0);
        System.out.println(sssp.path(5));
    }
}
