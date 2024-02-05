// LC 695
// 其他 floodfill 相关 200 1020 130 733 1034 529 827(hard)
public class MaxAreaOfIsland {
    // 1. 坐标映射
    // 2D ==> 1D
    // (x, y) ==> x*Y + y
    // 1D ==> 2D
    // M ==> (M / Y, M % Y)

    // 2.四联通 dirs
    //      八联通 dirs 包含8个方向

    // 3.求联通分量CC中顶点的个数 recursive

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private boolean[] visited;
    private int maxArea;
    private int[][] g;
    private int X;
    private int Y;
    public int maxAreaOfIsland(int[][] grid) {
        this.g = grid;
        this.maxArea = 0;
        this.X = g.length;
        this.Y = g[0].length;
        this.visited = new boolean[X*Y];
        for (int v = 0; v < X * Y; ++v) {
            if (!visited[v] && g[v / Y][v % Y] != 0) {
                maxArea = Math.max(maxArea, dfs(v));
            }
        }

        return maxArea;
    }

    private int dfs(int v) {
        visited[v] = true;
        int x = v / Y;
        int y = v % Y;
        int res = 1;
        // floodfill:在2D网格中从一点出发遍历整个区域
        for (int i = 0; i < dirs.length; i++) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            if (valid(nx, ny) && !visited[nx * Y + ny] && g[nx][ny] == 1) {
                res += dfs(nx * Y + ny);
            }
        }
        return res;
    }

    private boolean valid(int x, int y) {
        return x >= 0 && x < X && y >= 0 && y < Y;
    }
}








































