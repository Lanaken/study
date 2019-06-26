import java.util.*;
class Vertex implements Comparable<Vertex> {
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
public class MaxComponent {
    private static ArrayList<Integer> arrayList = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();

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
            if (a != b)
                vertexes[a].setEdges(b);
            vertexes[b].setEdges(a);
        }
        BFS(vertexes);
        for (ArrayList<Integer> i : arrayLists) {
            if (i.size() > arrayList.size())
                arrayList = i;
            else if (i.size() == arrayList.size()) {
                int edges1 = 0;
                int edges2 = 0;
                for (int j = 0; j < i.size(); j++) {
                    edges1 += vertexes[i.get(j)].getEdges().size();
                }
                for (int j = 0; j < arrayList.size(); j++) {
                    edges2 += vertexes[arrayList.get(j)].getEdges().size();
                }
                if (edges1 > edges2)
                    arrayList = i;
                else if (edges1 == edges2)
                    if (i.get(0) < arrayList.get(0))
                        arrayList = i;
            }
        }
        StringBuilder s = new StringBuilder();
        s.append("graph {\n");
        for (Vertex i : vertexes) {
            if (arrayList.indexOf(i.getNumber()) != -1)
                s.append(i.getNumber() + " [color = red]\n");
            else s.append(i.getNumber() + "\n");
        }
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes[i].getEdges().size(); j++) {
                if (vertexes[i].getNumber() > vertexes[i].getEdges().get(j)) {
                    vertexes[i].getEdges().remove(vertexes[i].getEdges().get(j));
                    j--;
                }
            }
        }
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes[i].getEdges().size(); j++) {
                if (arrayList.indexOf(vertexes[i].getNumber()) != -1)
                    s.append(vertexes[i].getNumber() + " -- " + vertexes[i].getEdges().get(j) + " [color = red]\n");
                else s.append(vertexes[i].getNumber() + " -- " + vertexes[i].getEdges().get(j) + "\n");
            }
        }
        s.append("}");
        System.out.print(s);
    }

    private static void BFS(Vertex[] vertexes) {
        Queue<Vertex> queue = new PriorityQueue<>();
        for (Vertex i : vertexes)
            if (!i.isMark()) {
                i.setMark();
                ArrayList<Integer> arrayList1 = new ArrayList<>();
                arrayList1.add(i.getNumber());
                queue.add(i);
                while (!queue.isEmpty()) {
                    Vertex vertex = queue.poll();
                    for (int j = 0; j < vertex.getEdges().size(); j++) {
                        if (!vertexes[vertex.getEdges().get(j)].isMark()) {
                            vertexes[vertex.getEdges().get(j)].setMark();
                            queue.add(vertexes[vertex.getEdges().get(j)]);
                            arrayList1.add(vertexes[vertex.getEdges().get(j)].getNumber());
                        }
                    }
                }
                arrayLists.add(arrayList1);
            }
    }
}
