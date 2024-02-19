public class WeightedEdge implements Comparable<WeightedEdge> {
    private int v, w, weight;

    public WeightedEdge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int getV() {
        return v;
    }

    public int getW() {
        return w;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedEdge o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d -> %d", v, w));
        return sb.toString();
    }
}
