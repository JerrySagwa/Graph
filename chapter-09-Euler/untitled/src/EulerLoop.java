public class EulerLoop {

    public boolean hasEulerLoop(Graph g) {
        CC cc = new CC(g);
        if (cc.count() > 1) // 不是无向联通图
            return false;

        for (int v = 0; v < g.V(); v++) {
            if (g.degree(v) % 2 == 1)
                return false;
        }

        return true;
    }

}
