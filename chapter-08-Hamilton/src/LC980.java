public class LC980 {
    private boolean[] visited;
    private int[][] g;
    private int R, C, total;
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int start, end;
    private int res = 0;

    /**
     * 1 -- starting
     * 2 -- ending
     * 0 -- empty
     * -1 -- obstacle
     */
    public int uniquePathsIII(int[][] grid) {
        this.R = grid.length;
        this.C = grid[0].length;
        this.total = R * C;
        this.visited = new boolean[R*C];
        this.g = grid;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1)
                    start = i * this.C + j;
                if (grid[i][j] == 2)
                    end = i * this.C + j;
                if (grid[i][j] == -1)
                    total--;
            }
        }

        dfs(start, total);
        return res;
    }

    private void dfs(int v, int left) {
        // 递归到底的情况
        if (v == end) {
            if (left == 1)
                res++;
            return;
        }
        int x = v / C;
        int y = v % C;
        visited[v] = true;
        left --;

        for (int i = 0; i < dirs.length; i++) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            int n = nx * C + ny;
            if (valid(nx, ny) && !visited[n] && g[nx][ny] != -1) {
                dfs(n, left);
            }
        }
        visited[v] = false;
    }

    private boolean valid(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}








































