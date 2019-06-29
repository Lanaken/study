


public class Element {
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