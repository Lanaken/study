import java.util.ArrayList;

public class Vertex implements Comparable<Vertex> {
    private int number;
    private ArrayList<Integer> edges = new ArrayList<>();
    private boolean mark;

    Vertex(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Integer> getEdges() {
        return edges;
    }

    public void setEdges(int edge) {
        edges.add(edge);
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark() {
        mark = true;
    }

    public int compareTo(Vertex obj) {
        return this.getNumber() - obj.getNumber();
    }
}