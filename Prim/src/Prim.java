import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Prim {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Vertex> arrayList = new ArrayList<>();
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        Vertex[] vertexes = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertexes[i] = new Vertex(i);
            priorityQueue.add(vertexes[i]);
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int s = in.nextInt();
            vertexes[a].getNeighbourhood().add(b);
            vertexes[a].getEdges().add(s);
            vertexes[b].getNeighbourhood().add(a);
            vertexes[b].getEdges().add(s);
        }
        vertexes[0].setS(0);
        Vertex v = priorityQueue.poll();
        int length = 0;
        while (!priorityQueue.isEmpty()) {
            for (int i = 0; i < v.getNeighbourhood().size(); i++) {
                if (priorityQueue.contains(vertexes[v.getNeighbourhood().get(i)])
                        && v.getEdges().get(i) < vertexes[v.getNeighbourhood().get(i)].getS()) {
                    vertexes[v.getNeighbourhood().get(i)].setS(v.getEdges().get(i));
                    vertexes[v.getNeighbourhood().get(i)].setParent(v.getNumber());
                    priorityQueue.remove(vertexes[v.getNeighbourhood().get(i)]);
                    priorityQueue.add(vertexes[v.getNeighbourhood().get(i)]);
                }
            }
            v = priorityQueue.poll();
            arrayList.add(v);
        }
        for (Vertex i : arrayList)
            length += i.getS();
        System.out.print(length);
    }
}
