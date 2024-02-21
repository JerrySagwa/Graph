import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TopoSort {

    private boolean hasCycle;
    private ArrayList<Integer> res = new ArrayList<>();

    public TopoSort(WeightedGraph G) {
        if (!G.directed())
            throw new IllegalArgumentException("Only works for directed graph!");
        Queue<Integer> q = new LinkedList<>();

        int[] indegrees = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            indegrees[v] = G.indegree(v);
            if (G.indegree(v) == 0)
                q.offer(v);
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            res.add(v);
            for (int w : G.adj(v)) {
                indegrees[w]--;
                if (indegrees[w] == 0)
                    q.offer(w);
            }
        }

        if (res.size() != G.V()) {
            hasCycle = true;
            res.clear();
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public ArrayList<Integer> res() {
        return res;
    }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph("wg.txt", true);
        TopoSort topoSort = new TopoSort(wg);
        if (!topoSort.hasCycle()) {
            System.out.println(topoSort.res());
        } else {
            System.out.println("Graph has cycle!");
        }
    }

}








































