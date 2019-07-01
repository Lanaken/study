import java.util.ArrayList;

public class Vertex implements Comparable<Vertex> {
    private int S = 999999999;
    private int number;
    private ArrayList<Integer> neighbourhood = new ArrayList<>();
    private ArrayList<Integer> edges = new ArrayList<>();
    private int parent = -1;

    Vertex(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void setS(int s) {
        S = s;
    }

    public int getS() {
        return S;
    }

    public ArrayList<Integer> getEdges() {
        return edges;
    }

    public ArrayList<Integer> getNeighbourhood() {
        return neighbourhood;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.getS() - o.getS();
    }
}