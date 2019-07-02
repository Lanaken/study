



public class Element<T> {
    private T x;
    private Element<T> parent;
    private int depth;

    public Element(T x) {
        this.x = x;
        parent = this;
        depth = 0;
    }

    public T x() {
        return x;
    }

    public boolean equivalent(Element<T> elem) {
        return this.find() == elem.find();
    }


    public void union(Element<T> elem) {
        Element<T> z = this.find();
        elem = elem.find();
        if (z.depth < elem.depth)
            z.parent = elem;
        else {
            elem.parent = z;
            if (z.depth == elem.depth && z != elem)
                z.depth++;
        }
    }
    private Element<T> find(){
        if (this.parent == this)
            return this;
        else return
                this.parent.find();
    }
}