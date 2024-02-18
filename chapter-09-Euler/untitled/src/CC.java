import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

// connected component
public class CC {
    private Graph G;
    private int[] visited;
    private int cccount;

    public CC(Graph G) {
        this.G = G;
        this.visited = new int[G.V()];
        this.cccount = 0;
        Arrays.fill(visited, 0);
        // 解决非连通图的问题
        for (int i = 0; i < G.V(); i++) {
            if (visited[i] == 0) {
                cccount++;
                dfs(i);
            }
        }
    }

    private void dfs(int v) {
        visited[v] = cccount;
        for (Integer w : G.adj(v)) {
            if (visited[w] == 0)
                dfs(w);
        }
    }

    public int count() {
        for (int e : visited) {
            System.out.print(e + " ");
        }
        System.out.println();
        return cccount;
    }

    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[cccount];
        for (int i = 0; i < res.length; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < G.V(); v++) {
            res[visited[v] - 1].add(v);
        }
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        CC cc = new CC(graph);
//        System.out.println(cc.count());
        ArrayList<Integer>[] components = cc.components();
        for (int i = 0; i < components.length; i++) {
            System.out.print(i + " : ");
            for (Integer w : components[i]) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }

}
