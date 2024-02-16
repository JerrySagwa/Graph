import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class HamiltonLoop {
    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int end = -1;

    public HamiltonLoop(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];
        // 只需要从一点出发找 Hamilton 回路就好了
        dfs(0, 0, G.V());
    }

    // left: 记录还需要被访问的顶点数
    private boolean dfs(int v, int parent, int left) {
        visited[v] = true;
        pre[v] = parent;
        left--;

        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v, left))
                    return true;
            } else if (w == 0 && left == 0)  {
                // 记录汉密尔顿回路最后一个顶点
                end = v;
                return true;
            }
        }

        // 遍历了所有邻点都没有找到，回溯
        visited[v] = false;
        // 此路不通，退回去后这个顶点要被重新访问,不过实际上left是参数,在这里改动不会影响上一层的调用
//        left++;
        return false;
    }

    private boolean allVisited() {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i])
                return false;
        }
        return true;
    }

    public ArrayList<Integer> result() {
        ArrayList<Integer> res = new ArrayList<>();
        if (end == -1)
            return res;
        int v = end;
        while (v != 0) {
            res.add(v);
            v = pre[v];
        }
        res.add(v);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g2.txt");
        HamiltonLoop hl = new HamiltonLoop(graph);
        System.out.println(hl.result());
    }

}
