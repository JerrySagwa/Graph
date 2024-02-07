import java.util.*;

public class LC752 {
    public int openLock(String[] deadends, String target) {
        HashSet<String> deadSet = new HashSet<>();

        for (String deadend : deadends) {
            deadSet.add(deadend);
        }
        if (deadSet.contains("0000")) return -1;
        if (deadSet.contains(target)) return -1;
        if ("0000".equals(target)) return 0;

        HashMap<String, Integer> lens = new HashMap<>();
        lens.put("0000", 0);
        Queue<String> q = new LinkedList<>();
        q.offer("0000");

        while (!q.isEmpty()) {
            String v = q.poll();
            for (String nxt : next(v)) {
                if (nxt.equals(target))
                    return lens.get(v) + 1;
                    // not visited && not in deadends
                else if (!lens.containsKey(nxt) && !deadSet.contains(nxt)) {
                    lens.put(nxt, lens.get(v) + 1);
                    q.offer(nxt);
                }
            }
        }
        return -1;
    }

    // 求解相邻的状态 (节点)
    private Iterable<String> next(String v) {
        ArrayList<String> res = new ArrayList<>();
        char[] vchar = v.toCharArray();
        for (int i = 0; i < vchar.length; ++i) {
            char old = vchar[i];

            vchar[i] = Character.forDigit((old - '0' + 1) % 10, 10);
            res.add(new String(vchar));
            // 尽量用正数求余
            vchar[i] = Character.forDigit((old - '0' - 1 + 10) % 10, 10);
            res.add(new String(vchar));
            vchar[i] = old;
        }
        return res;
    }

    public static void main(String[] args) {
        LC752 solution = new LC752();
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        solution.openLock(deadends, "0202");
    }

}








































