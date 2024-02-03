import java.util.ArrayList;
import java.util.Arrays;

public class BipartitionDetection {
    private Graph G;
    private boolean[] visited;
    // -1: not visited 0, 1
    private int[] colors; // 记录遍历过程中顶点的颜色
    private boolean isBipartite;

    public BipartitionDetection(Graph G) {
        this.G = G;
        this.isBipartite = true;
        this.visited = new boolean[G.V()];
        this.colors = new int[G.V()];
        Arrays.fill(visited, false);
        Arrays.fill(colors, -1);
        // 解决非连通图的问题
        for (int i = 0; i < G.V(); i++) {
            if (!visited[i])
                if (!dfs(i, 0)) {
                    isBipartite = false;
                    break;
                }
        }
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) return false;
            } else if (colors[w] == color) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        BipartitionDetection bipartitionDetection = new BipartitionDetection(graph);
        System.out.println(bipartitionDetection.isBipartite());
    }

}
