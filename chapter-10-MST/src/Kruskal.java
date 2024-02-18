import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst = new ArrayList<>();

    private class UF {

        private int[] parent;
        public UF(int sz) {
            parent = new int[sz];
            for (int i = 0; i < sz; i++)
                parent[i] = i;
        }

        private int find(int v) {
            if (v < 0 || v >= parent.length)
                throw new IllegalArgumentException("Invalid Index!");
            if (parent[v] != v) {
                parent[v] = find(parent[v]);
            }
            return parent[v];
        }

        public boolean isConnected(int v, int w) {
            return find(w) == find(v);
        }

        public void connect(int v, int w) {
            int vR = find(v);
            int wR = find(w);
            parent[vR] = wR;
        }
    }

    public Kruskal(WeightedGraph G) {
        this.G = G;
        CC cc = new CC(G);
        if (cc.count() > 1)
            return;

        // 将所有的边加入集合中排序
        ArrayList<WeightedEdge> edges = new ArrayList<>();
        for (int v = 0; v < G.V(); v++) {
            for (Integer w : G.adj(v)) {
                if (v < w)
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));
            }
        }

        Collections.sort(edges);
        UF uf = new UF(G.V());

        for (int i = 0; i < edges.size(); i++) {
            WeightedEdge edge = edges.get(i);
            int v = edge.getV(), w = edge.getW();
            if (!uf.isConnected(v, w)) {
                uf.connect(v, w);
                mst.add(edge);
            }
        }
    }

    public void result() {
        System.out.println(mst);
    }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph("g.txt");
        Kruskal kruskal = new Kruskal(wg);
        kruskal.result();
    }

}
