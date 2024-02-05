import java.util.Arrays;

//LC 785
public class IsBipartite {

    private boolean[] visited;
    private int[] color;
    private int[][] g;

    public boolean isBipartite(int[][] graph) {
        this.g = graph;
        visited = new boolean[g.length];
        color = new int[g.length];
        Arrays.fill(color, -1);
        for (int v = 0; v < g.length; v++) {
            if (!visited[v])
                if (!dfs(v, 0))
                    return false;
        }
        return true;
    }

    private boolean dfs(int v, int c) {
        visited[v] = true;
        color[v] = c;
        for (int i = 0; i < g[v].length; i++) {
            int w = g[v][i];
            if (!visited[w]) {
                if (!dfs(w, 1 - c))
                    return false;
            } else if (color[w] == color[v]) {
                return false;
            }
        }
        return true;
    }
}
