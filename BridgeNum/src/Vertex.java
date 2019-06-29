import java.util.ArrayList;

class Vertex{
    private ArrayList vertexes = new ArrayList<Integer>();
    private String mark = "white";
    private int comp = -1;
    private int parent = 0;
    private int number;
    Vertex(int n){
        number = n;
    }

    public ArrayList getVertexes() {
        return vertexes;
    }

    public String getMark() {
        return mark;
    }

    public int getComp() {
        return comp;
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

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }
}