

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

public class SparseSet<T extends Hintable> extends AbstractSet<T> {
    private int n;
    private ArrayList<T> dense = new ArrayList<>();

    public boolean contains(T x){
        return (n > 0 && dense.get(x.hint()) == x);
    }
    public boolean add(T elem){
        if (!contains(elem)) {
            elem.setHint(n);
            dense.add(n, elem);
            n++;
            return true;
        }
        return false;
    }

    public boolean remove(T x){
        if (n > 0 && contains(x)) {
            n--;
            dense.set(x.hint(), dense.get(n));
            return true;
        }
        return false;
    }

    public void clear(){
        n = 0;
    }
    public int size(){
        return n;
    }

    @Override
    public Iterator<T> iterator() {
        return new SetIterator();
    }

    public class SetIterator implements Iterator<T>{
        private int pos;

        public boolean hasNext() {
            return pos < n;
        }

        @Override
        public void remove() {
            SparseSet.this.remove(dense.get(pos - 1));
            pos++;
        }

        public T next() {
            return dense.get(pos++);
        }
    }
}