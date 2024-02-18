import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Hierholzer {

    private Graph g;
    private Stack<Integer> curPath = new Stack<>();
//    private Stack<Integer> loop = new Stack<>();

    public Hierholzer(Graph g) {
        try {
            this.g = (Graph) g.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Integer> result() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!new EulerLoop().hasEulerLoop(g))
            return res;

        curPath.push(0);

        while (!curPath.isEmpty()) {
            int v = curPath.peek();
            if (!g.adj(v).isEmpty()) {
                int w = g.adj(v).first();
                g.deleteEdge(v, w);
                curPath.push(w);
            } else {
                int w = curPath.pop();
                res.add(w);
            }
        }

        Collections.reverse(res);
        return res;
    }

    public ArrayList<Integer> resultRecursive() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!new EulerLoop().hasEulerLoop(g))
            return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        Hierholzer h = new Hierholzer(g);

        System.out.println(h.result());
    }

}








































