import java.util.LinkedList;
import java.util.Queue;

// 无权图最短路径，想到 BFS
public class ShortestPathInBinaryMatrix {

    private int[][] g;
    private int R;
    private int C;
    // 8联通
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    private boolean[] visited;
    public int shortestPathBinaryMatrix(int[][] grid) {
        this.g = grid;
        this.R = g.length;
        this.C = g[0].length;
        this.visited = new boolean[R * C];
        return bfs();
    }

    // from (0, 0) to (R - 1, C - 1)
    private int bfs() {
        if (g[0][0] == 1 || g[R - 1][C - 1] == 1)
            return -1;
        int len = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int cur = q.poll();
                int x = cur / C, y = cur % C;
                if (x == R - 1 && y == C - 1)
                    return len;
                else {
                    for (int d = 0; d < dirs.length; d++) {
                        int nx = x + dirs[d][0];
                        int ny = y + dirs[d][1];
                        if (valid(nx, ny) && !visited[nx * C + ny] && g[nx][ny] == 0) {
                            // 入队时标记为 true
                            q.offer(nx * C + ny);
                            visited[nx * C + ny] = true;
                        }
                    }
                }
            }
            // 完成一轮BFS相邻节点加入，最短路径 + 1
            len++;
        }
        return -1;
    }

    private boolean valid(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
