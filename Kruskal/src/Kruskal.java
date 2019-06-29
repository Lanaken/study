import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

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