import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Element {
    private double x;
    private double y;
    private Element parent;
    private int depth;

    public Element(double x, double y) {
        this.x = x;
        this.y = y;
        parent = this;
        depth = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getDepth() {
        return depth;
    }

    public boolean equivalent(Element elem) {
        return this.find() == elem.find();
    }

    public void union(Element elem) {
        Element z = this.find();
        elem = elem.find();
        if (z.depth < elem.depth)
            z.parent = elem;
        else {
            elem.parent = z;
            if (z.depth == elem.depth && z != elem)
                z.depth++;
        }
    }

    public Element find() {
        if (this.parent == this)
            return this;
        else return
                this.parent.find();
    }

}
class Edge implements Comparable<Edge>{
    private double length;
    private Element a;
    private Element b;

    Edge(Element a, Element b) {
        length = Math.sqrt(Math.pow(b.getX() - a.getX(), 2) +
                Math.pow(b.getY() - a.getY(), 2));
        this.a = a;
        this.b = b;
    }

    public double getLength() {
        return length;
    }

    public Element getA() {
        return a;
    }

    public Element getB() {
        return b;
    }

    public int compareTo(Edge o) {
        if (this.length < o.length)
            return -1;
        else if (this.length > o.length)
            return 1;
        return 0;
    }
}





public class Kruskal {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Element[] elements = new Element[n];
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>();
        for (int i = 0;i < n;i++){
            double a = Double.valueOf(in.nextInt());
            double b = Double.valueOf(in.nextInt());
            elements[i] = new Element(a,b);
        }
        for (int i = 0;i < n - 1;i++)
            for (int j = i + 1;j < n;j++)
                edgePriorityQueue.add(new Edge(elements[i],elements[j]));
        double length = findLength(edgePriorityQueue,elements);
        StringBuilder s = new StringBuilder();
        s.append(new BigDecimal(length).setScale(2, RoundingMode.HALF_UP));
        System.out.print(s);
    }
    public static double findLength(PriorityQueue<Edge> elementPriorityQueue,Element[] elements){
        double length = 0;
        ArrayList<Edge> arrayList = new ArrayList<>();
        Edge edge;
        while (elementPriorityQueue.size() > 0 && arrayList.size() < elements.length - 1){
            edge = elementPriorityQueue.poll();
            if (!edge.getA().equivalent(edge.getB())){
                arrayList.add(edge);
                edge.getA().union(edge.getB());
            }
        }
        for (Edge j : arrayList)
            length += j.getLength();
        return length;
    }
}
