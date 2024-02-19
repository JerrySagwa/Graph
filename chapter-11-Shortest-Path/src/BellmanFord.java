import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BellmanFord {

    private WeightedGraph G;
    private int[] dis;
    private int[] pre;
    private boolean hasNegativeCycle;

    public BellmanFord(WeightedGraph G) {
        this.G = G;
        this.dis = new int[G.V()];
        this.pre = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        Arrays.fill(pre, -1);

        dis[0] = 0;
        pre[0] = 0;

        for (int i = 0; i < G.V() - 1; ++i) {
            for (int v = 0; v < G.V(); v++) {
                for (Integer w : G.adj(v)) {
                    if (dis[w] != Integer.MAX_VALUE && dis[v] > dis[w] + G.getWeight(w, v)) { // 整型溢出
                        dis[v] = dis[w] + G.getWeight(v, w);
                        pre[v] = w;
                    }

                }
            }
        }

        // V : 负权环检测
        for (int v = 0; v < G.V(); v++) {
            for (Integer w : G.adj(v)) {
                if (dis[w] != Integer.MAX_VALUE)
                    if (dis[v] > dis[w] + G.getWeight(w, v))
                        hasNegativeCycle = true;
            }
        }

    }

    public boolean hasNegativeCyc() {
        return hasNegativeCycle;
    }

   public boolean isConnectedTo(int v) {
        G.validateVertex(v);
        return dis[v] != Integer.MAX_VALUE;
   }

   public ArrayList<Integer> path(int v) {
       G.validateVertex(v);
       ArrayList<Integer> res = new ArrayList<>();
       if (pre[v] == -1) // unreachable
           return res;
       int cur = v;
       while (pre[cur] != cur) {
           res.add(cur);
           cur = pre[cur];
       }

       res.add(cur);
       Collections.reverse(res);
       return res;
   }

   public int disTo(int v) {
       G.validateVertex(v);
       if (hasNegativeCycle) {
           throw new IllegalArgumentException("Negative Cycle Exists!");
       }

       return dis[v];
   }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph("g.txt");
        BellmanFord bf = new BellmanFord(wg);
        for (int v = 0; v < wg.V(); v++) {
            System.out.print(bf.disTo(v) + " ");
        }
        System.out.println();

        for (int v = 0; v < wg.V(); v++) {

        }
    }
}




































































































