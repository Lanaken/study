public class Edge implements Comparable<Edge>{
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