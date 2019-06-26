import java.util.*;

class Vertex implements Comparable<Vertex> {
    private int number;
    private ArrayList<Integer> edges = new ArrayList<>();
    private int[] distFromReferenceVertexes;
    private boolean mark = false;
    private int mark1 = Integer.MAX_VALUE;

    Vertex(int number) {
        this.number = number;
    }

    public int getMark1() {
        return mark1;
    }

    public void changeMark1(int mark1) {
        this.mark1 = mark1;
    }

    public void makeArray(int size){
        distFromReferenceVertexes = new int[size];
    }

    public int[] getDistFromReferenceVertexes() {
        return distFromReferenceVertexes;
    }

    public void changeDistFromReferenceVertexes(int n, int i){
        distFromReferenceVertexes[i] = n;
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

    public void makeFalse() {
        mark = false;
    }

    public void setMark() {
        mark = true;
    }

    public int compareTo(Vertex obj) {
        return this.getNumber() - obj.getNumber();
    }
}

public class EqDist {
    private static ArrayList<Integer> arrayList = new ArrayList<>();
    private static ArrayList<Integer> referenceVertexes = new ArrayList<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Vertex[] vertexes = new Vertex[n];
        for (int i = 0; i < n; i++)
            vertexes[i] = new Vertex(i);
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            vertexes[a].setEdges(b);
            vertexes[b].setEdges(a);
        }
        int k = in.nextInt();
        for (int i = 0;i < n;i++)
            vertexes[i].makeArray(k);
        for (int i = 0; i < k; i++) {
            referenceVertexes.add(in.nextInt());
        }
        Collections.sort(referenceVertexes);
        for (int i = 0; i < k; i++) {
            Queue<Integer> queue = new LinkedList<>();
            vertexes[referenceVertexes.get(i)].changeMark1(0);
            Dijkstra(vertexes, referenceVertexes.get(i),queue);
            for (int j = 0; j < n; j++) {
                vertexes[j].getDistFromReferenceVertexes()[i] = vertexes[j].getMark1();
                vertexes[j].makeFalse();
                vertexes[j].changeMark1(Integer.MAX_VALUE);
            }
        }
        for (Vertex i : vertexes) {
            int j = 0;
            int l = 0;
            if (i.getDistFromReferenceVertexes().length > 0)
                l = i.getDistFromReferenceVertexes()[0];
            while (j < i.getDistFromReferenceVertexes().length && l == i.getDistFromReferenceVertexes()[j]
                    && l != Integer.MAX_VALUE) {
                j++;
            }
            if (j == i.getDistFromReferenceVertexes().length && l != 0) {
                arrayList.add(i.getNumber());
            }
        }
        if (!arrayList.isEmpty()) {
            StringBuilder s = new StringBuilder();
            arrayList.forEach(x -> s.append(x + " "));
            System.out.print(s);
        } else System.out.print("-");
    }

    public static void Dijkstra(Vertex[] vertexes, int number, Queue<Integer> queue){
        if (!vertexes[number].isMark()){
            for(int i = 0;i < vertexes[number].getEdges().size();i++){
                int a = vertexes[number].getMark1() + 1;
                queue.add(vertexes[number].getEdges().get(i));
                if (vertexes[vertexes[number].getEdges().get(i)].getMark1() > a)
                    vertexes[vertexes[number].getEdges().get(i)].changeMark1(a);
            }
            vertexes[number].setMark();
            for(int i = 0;i < vertexes[number].getEdges().size();i++){
                int b = queue.poll();
                Dijkstra(vertexes,b,queue);
            }
        }
    }
}
