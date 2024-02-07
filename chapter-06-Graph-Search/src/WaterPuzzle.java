import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

// 用 5L & 3L 的水桶，得到 4L 水
public class WaterPuzzle {

    private int[] pre = new int[100];
    private int lastStat = -1;
    public WaterPuzzle() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[100];

        q.offer(0);
        visited[0] = true;

        while (!q.isEmpty()) {
            Integer cur = q.poll();
            int a = cur / 10, b = cur % 10;
            // max a = 5, b = 3
            ArrayList<Integer> nexts = new ArrayList<>();
            int na, nb;
            // a-> 5
            if (a != 5) {
                na = 5;
                nb = b;
                nexts.add(50 + nb);
            }

            // b -> 3
            if (b != 3) {
                na = a;
                nb = 3;
                nexts.add(a * 10 + 3);
            }

            // a->b
            if (a != 0 && b != 3) {
                nb = b + a;
                na = a - (3 - b);
                if (nb > 3) nb = 3;
                if (na < 0) na = 0;
                nexts.add(na * 10 + nb);
            }
            // b->a
            if (a != 5 && b != 0) {
                na = a + b;
                nb = b - (5 - a);
                if (na > 5) na = 5;
                if (nb < 0) nb = 0;
                nexts.add(na * 10 + nb);
            }
            // a->0
            if (a != 0) {
                nexts.add(b);
            }

            // b->0
            if (b != 0) {
                nexts.add(a * 10);
            }

            for (Integer next : nexts) {
                if (!visited[next]) {
                    int nexta = next / 10, nextb = next % 10;
                    if (nexta == 4 || nextb == 4) {
                        pre[next] = cur;
                        lastStat = next;
                        return;
                    } else {
                        q.offer(next);
                        visited[next] = true;
                        pre[next] = cur;
                    }
                }
            }
        }
    }

    public Iterable<Integer> res() {
        ArrayList<Integer> res = new ArrayList<>();

        if (lastStat == -1) {
            return res;
        }

        int cur = lastStat;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(cur);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        WaterPuzzle wp = new WaterPuzzle();
        System.out.println(wp.res());
    }

}








































