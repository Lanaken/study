import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


public class MapRoute {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        short n = (short)in.nextInt();
        Vertex[][] map = new Vertex[n][n];
        if (n == 1){
            System.out.print(in.nextInt());
            return;
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                map[i][j] = new Vertex(i, j,(byte) in.nextInt());
            }
        map[0][0].setMark(map[0][0].number);
        dejikstra(map, n);
        System.out.print(map[n - 1][n - 1].mark);
    }

    public static void dejikstra(Vertex[][] map, short n) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.add(map[0][0]);
        while (!queue.isEmpty()) {
            Vertex v = queue.poll();
            v.index = -1;
            ArrayList<Vertex> vertexArrayList = findEdges(v, map, n);
            for (Vertex x : vertexArrayList) {
                if (x.index != -1 && relax(v, x, x.number )) {
                    queue.remove(x);
                    queue.add(x);
                }
            }
        }
    }

    public static boolean relax(Vertex u, Vertex v, int w) {
        if (u.mark + w < v.mark) {
            v.mark = u.mark + w;
            v.parent = u;
            return true;
        }
        return false;
    }

    public static ArrayList<Vertex> findEdges(Vertex v, Vertex[][] map, short n) {
        ArrayList<Vertex> arrayList = new ArrayList<>();
        int i = v.i;
        int j = v.j;
        if (i == 0 && j == 0) {
            arrayList.add(map[i][j + 1]);
            arrayList.add(map[i + 1][j]);
            return arrayList;
        }
        if (i == 0 && j == n - 1) {
            arrayList.add(map[i][j - 1]);
            arrayList.add(map[i + 1][j]);
            return arrayList;
        }
        if (i == n - 1  && j == 0) {
            arrayList.add(map[i][j + 1]);
            arrayList.add(map[i - 1][j]);
            return arrayList;
        }
        if (i == n - 1 && j == n - 1) {
            arrayList.add(map[i - 1][j]);
            arrayList.add(map[i][j - 1]);
            return arrayList;
        }
        if (i == 0) {
            arrayList.add(map[i + 1][j]);
            arrayList.add(map[i][j + 1]);
            arrayList.add(map[i][j - 1]);
            return arrayList;
        }
        if (i == n - 1) {
            arrayList.add(map[i - 1][j]);
            arrayList.add(map[i][j + 1]);
            arrayList.add(map[i][j - 1]);
            return arrayList;
        }
        if (j == 0) {
            arrayList.add(map[i + 1][j]);
            arrayList.add(map[i][j + 1]);
            arrayList.add(map[i - 1][j]);
            return arrayList;
        }
        if (j == n - 1) {
            arrayList.add(map[i + 1][j]);
            arrayList.add(map[i - 1][j]);
            arrayList.add(map[i][j - 1]);
            return arrayList;
        }
        arrayList.add(map[i + 1][j]);
        arrayList.add(map[i - 1][j]);
        arrayList.add(map[i][j - 1]);
        arrayList.add(map[i][j + 1]);
        return arrayList;
    }

    private static class Vertex implements Comparable<Vertex> {
        private int i, j;
        private byte index = 1;
        private byte number;
        private Vertex parent = null;
        private int mark = Integer.MAX_VALUE;

        Vertex(int i, int j, byte number) {
            this.i = i;
            this.j = j;
            this.number = number;
        }

        public byte getNumber() {
            return number;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        public int getMark() {
            return mark;
        }

        @Override
        public int compareTo(Vertex o) {
            return (this.mark - o.mark);
        }

        public void setParent(Vertex parent) {
            this.parent = parent;
        }

        public Vertex getParent() {
            return parent;
        }
    }

}
