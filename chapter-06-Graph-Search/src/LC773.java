import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// O(n!) n: 格子总数 > O(2^n) np 问题: 无法在多项式的复杂度里解决
// O(V + E) ==> O(V + 4*V) ==> O(V) ==> V ~ n!
public class LC773 {

    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int slidingPuzzle(int[][] board) {
        String s = boardToString(board);
        String t = "123450";
        if (s.equals(t)) return 0;

        Queue<String> q = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();

        q.offer(s);
        visited.put(s, 0);

        while (!q.isEmpty()) {
            String cur = q.poll();
            ArrayList<String> nexts = nexts(cur);
            for (String next : nexts) {
                if (!visited.containsKey(next)) {
                    if (next.equals(t)) {
                        return visited.get(cur) + 1;
                    }
                    visited.put(next, visited.get(cur) + 1);
                    q.offer(next);
                }
            }
        }
        return -1;
    }

    private ArrayList<String> nexts(String s) {
        ArrayList<String> res = new ArrayList<>();
        int zIndex = -1;
        for (int i = 0; i < 6; i++) {
            if (s.charAt(i) == '0') {
                zIndex = i;
                break;
            }
        }
        int x = zIndex / 3;
        int y = zIndex % 3;

        int[][] b = stringToBoard(s);
        for (int i = 0; i < dirs.length; i++) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            if (valid(nx, ny)) {
                swap(b, x, y, nx, ny);
                res.add(boardToString(b));
                swap(b, x, y, nx, ny);
            }
        }
        return res;
    }

    private void swap(int[][] b, int x, int y, int nx, int ny) {
        int t = b[x][y];
        b[x][y] = b[nx][ny];
        b[nx][ny] = t;
    }

    private int[][] stringToBoard(String s) {
        int[][] res = new int[2][3];
        for (int i = 0; i < 6; ++i) {
            res[i / 3][i % 3] = s.charAt(i) - '0';
        }
        return res;
    }

    private String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    private boolean valid(int x, int y) {
        return x >= 0 && x < 2 && y >= 0 && y < 3;
    }
}
