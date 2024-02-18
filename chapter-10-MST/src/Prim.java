import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prim {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst = new ArrayList<>();
    private boolean[] visited;

    public Prim(WeightedGraph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        CC cc = new CC(G);
        if (cc.count() > 1)
            return;

        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();
        for (Integer v : G.adj(0)) {
            pq.add(new WeightedEdge(0, v, G.getWeight(0, v)));
        }
        visited[0] = true;
        while (!pq.isEmpty()) {
            WeightedEdge edge = pq.poll();
            int v = edge.getV(), w = edge.getW();
            if (visited[v] && visited[w])
                continue;
            mst.add(edge);
            int newv = visited[edge.getV()] ? edge.getW() : edge.getV();
            visited[newv] = true;

            for (Integer next : G.adj(newv)) {
                if (!visited[next]) {
                    pq.add(new WeightedEdge(newv, next, G.getWeight(newv, next)));
                }
            }
        }

    }

    public void result() {
        System.out.println(mst);
    }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph("g.txt");
        Prim prim = new Prim(wg);
        prim.result();
    }
}








































