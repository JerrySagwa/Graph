import java.util.ArrayList;
import java.util.Arrays;

public class FindCutPoints {

    private Graph g;
    private int[] ord;
    private int[] low;
    private boolean[] visited;
    private ArrayList<Integer> res;
    private int order = 0;

    public FindCutPoints(Graph g) {
        this.g = g;
        this.ord = new int[g.V()];
        this.low = new int[g.V()];
        this.visited = new boolean[g.V()];
        res = new ArrayList<>();
        for (int v = 0; v < g.V(); v++) {
            if (!visited[v])
                dfs(v, v);
        }
    }

    // v == parent -> v 为 dfs 过程中该联通分量的根节点
    private void dfs(int v, int parent) {
        visited[v] = true;
        ord[v] = order++;
        low[v] = ord[v];

        int child = 0;
        for (Integer w : g.adj(v)) {
            if (!visited[w]) {
                child++;
                dfs(w, v);
                low[v] = Math.min(low[w], low[v]);
                if (low[w] >= ord[v] && v != parent)
                    res.add(v);
                if (v == parent && child > 1)
                    res.add(v);
            } else if (w != parent) {
                low[v] = Math.min(low[w], low[v]);
            }
        }
    }

    public ArrayList<Integer> res() {
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        FindCutPoints findCutPoints = new FindCutPoints(graph);
        System.out.println(findCutPoints.res());
    }
}
