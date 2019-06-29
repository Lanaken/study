import java.util.ArrayList;
import java.util.Collections;

public class Vertex {
    private int number;
    private int time = 0;
    private int component = 0;
    private int low;
    private ArrayList<Integer> edges = new ArrayList<>();

    Vertex(int number) {
        this.number = number;
        low = number;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Integer> getEdges() {
        return edges;
    }

    public int getComponent() {
        return component;
    }

    public int getLow() {
        return low;
    }

    public int getTime() {
        return time;
    }

    public void setComponent(int component) {
        this.component = component;
    }

    public void addEdge(Integer edge) {
        edges.add(edge);
        Collections.sort(edges);
    }

    public void setLow(int low) {
        this.low = low;
    }

    public void setTime(int time) {
        this.time = time;
    }
}