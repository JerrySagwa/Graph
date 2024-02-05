import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// LC 827
public class MakingALargeIsland {
    int R,C;
    int[][] g;
    // 0: not visited
    // others: ccid
    int[] visited;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // key: ccid, value: area
    HashMap<Integer, Integer> map = new HashMap<>();
    public int largestIsland(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        g = grid;
        visited = new int[R*C];
        int res = 0;
        int ccid = 1;
        // dfs:
        // 1.记录每个岛屿 ccid
        // 2.记录每个岛屿 面积
        for (int i = 0; i < R*C; ++i) {
            int x = i / C;
            int y = i % C;
            if (visited[i] == 0 && grid[x][y] == 1) {
                map.put(ccid, dfs(i, ccid));
                res = Math.max(res, map.get(ccid));
                ccid++;
            }
        }

        // 遍历可以 0 -> 1 的节点
        // 添加周边的岛屿 -> 利用 union find 思想 防止岛屿重复添加
        // 由于这里并不需要真的把两个 CC 连起来，只需要用最简单的 union find 实现就行了
        for (int i = 0; i < R * C; i++) {
            int x = i / C;
            int y = i % C;
            if (grid[x][y] == 0) {
                int t = 1;
                Set<Integer> set = new HashSet<>();
                for (int d = 0; d < dirs.length; ++d) {
                    int nx = x + dirs[d][0];
                    int ny = y + dirs[d][1];
                    int v = nx * C + ny;
                    if (valid(nx, ny) && visited[v] != 0 && !set.contains(visited[v])) {
                        t += map.get(visited[v]);
                        set.add(visited[v]);
                    }
                }
                res = Math.max(t, res);
            }
        }
        return res;
    }

    private int dfs(int v, int ccid) {
        visited[v] = ccid;
        int x = v / C;
        int y = v % C;
        int ret = 1;
        for (int d = 0; d < dirs.length; d++) {
            int nx = x + dirs[d][0];
            int ny = y + dirs[d][1];
            if (valid(nx, ny) && visited[nx * C + ny] == 0 && g[nx][ny] == 1)
                ret += dfs(nx * C + ny, ccid);
        }
        return ret;
    }

    private boolean valid(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        MakingALargeIsland makingALargeIsland = new MakingALargeIsland();
        int[][] grid = {{1, 1}, {1, 0}};
        System.out.println(makingALargeIsland.largestIsland(grid));
    }
}
