import java.util.ArrayList;

public class BFS {

    private ArrayList<Integer> order;
    private boolean[] visited;

    public BFS(Graph g) {
        this.order = new ArrayList<>();
        this.visited = new boolean[g.V()];
    }
}
