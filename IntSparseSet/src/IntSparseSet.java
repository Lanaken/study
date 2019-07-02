import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

public class IntSparseSet extends AbstractSet<Integer> {
    private int low,high,n,m;
    private ArrayList<Integer> sparse = new ArrayList<>();
    private ArrayList<Integer> dense = new ArrayList<>();
    public IntSparseSet(int low, int high) {
        this.low = low;
        this.high = high;
        n = 0;
        for (int i = 0; i < high - low; i++)
            sparse.add(-1);
        m = high - low;
    }

    public boolean contains(Object x){
        return ((Integer) x <= high && (Integer) x >= low && 0 <= sparse.get((Integer)x - low)
                && sparse.get((Integer)x - low) < n && dense.get(sparse.get((Integer)x - low)).equals(x));
    }
    public boolean add(Integer elem){
        if (elem > high || contains(elem) || elem < low)
            return false;
        if (size() == m && !contains(elem))
            m++;
        dense.add(n,elem);
        sparse.set(elem - low,n);
        n++;
        return true;
    }

    public boolean remove(Object x){
        if (n > -1 && contains(x)) {
            n--;
            dense.set(sparse.get((Integer)x - low), dense.get(n));
            sparse.set(dense.get(n) - low, sparse.get((Integer)x - low));
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
    public Iterator<Integer> iterator() {
        return new IntIterator();
    }


    public class IntIterator implements Iterator<Integer>{
        private int pos;

        public boolean hasNext() {
            return pos < n;
        }

        @Override
        public void remove() {
            IntSparseSet.this.remove(dense.get(pos - 1));
            pos++;
        }

        public Integer next() {
            return dense.get(pos++);
        }
    }
}