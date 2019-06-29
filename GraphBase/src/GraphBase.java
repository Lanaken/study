import java.util.*;


public class GraphBase {
    private static int time = 1;
    private static int count = 1;
    private static ArrayList<ArrayList<Integer>> condensation = new ArrayList<>();
    private static ArrayList<Integer> base = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        Vertex[] vertexes = new Vertex[N];
        for (int i = 0; i < N; i++) {
            vertexes[i] = new Vertex(i);
        }
        for (int i = 0; i < M; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            vertexes[a].addEdge(b);
        }
        Tarjan(vertexes);
        boolean matrix[][] = new boolean[condensation.size()][condensation.size()];
        int j;
        for (int i = 0; i < N; i++) {

            for (int k = 0; k < vertexes[i].getEdges().size(); k++) {
                Vertex vertex = vertexes[vertexes[i].getEdges().get(k)];
                if (vertex.getComponent() != vertexes[i].getComponent() &&
                        !matrix[vertexes[i].getComponent() - 1][vertex.getComponent() - 1]) {
                    matrix[vertexes[i].getComponent() - 1][vertex.getComponent() - 1] = true;
                }
            }
        }

        for (int i = 0; i < condensation.size(); i++) {
            for (j = 0; j < condensation.size(); j++) {
                if (matrix[j][i])
                    break;
            }
            if (j == condensation.size())
                base.add(i);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < base.size(); i++) {
            stringBuilder.append(vertexes[condensation.get(base.get(i)).get(0)].getNumber());
            stringBuilder.append(" ");
        }
        System.out.print(stringBuilder);
    }

    private static void Tarjan(Vertex[] vertexes) {
        Stack<Vertex> stack = new Stack<>();
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i].getTime() == 0)
                visit_Vertex_Tarjan(stack, vertexes, vertexes[i]);
        }
    }

    private static void visit_Vertex_Tarjan(Stack<Vertex> stack, Vertex[] vertexes, Vertex vertex) {
        vertex.setLow(time);
        vertex.setTime(vertex.getLow());
        time++;
        stack.push(vertex);
        for (Integer i : vertex.getEdges()) {
            if (vertexes[i].getTime() == 0)
                visit_Vertex_Tarjan(stack, vertexes, vertexes[i]);
            if (vertexes[i].getComponent() == 0 && vertex.getLow() > vertexes[i].getLow())
                vertex.setLow(vertexes[i].getLow());
        }
        if (vertex.getLow() == vertex.getTime()) {
            condensation.add(new ArrayList<>());
            Vertex vertex1;
            do {
                vertex1 = stack.pop();
                vertex1.setComponent(count);
                condensation.get(count - 1).add(vertex1.getNumber());
            } while (vertex1 != vertex);
            Collections.reverse(condensation.get(count - 1));
            count++;
        }
    }
}