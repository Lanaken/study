import java.util.*;

class Vertex{
    ArrayList vertexes = new ArrayList<Integer>();
    String mark = "white";
    int comp = -1;
    int parent = 0;
    int number;
    Vertex(int n){
        number = n;
    }
}
public class BridgeNum{
    private static int component = 0;
    public static void DFS1(Vertex[] V,LinkedList queue,int N){
        for (int i = 0;i < N;i++){
            if (V[i].mark.equals("white")){
                V[i].parent = -1;
                component--;
                VisitVertex1(V,queue,i);
            }
        }
    }
    public static void VisitVertex1(Vertex[] V,LinkedList queue,int v){
        V[v].mark = "gray";
        queue.add(V[v]);
        for (int i = 0;i < V[v].vertexes.size();i++){
            if (V[(Integer)V[v].vertexes.get(i)].mark.equals("white") ){
                V[(Integer)V[v].vertexes.get(i)].parent = v;
                VisitVertex1(V,queue,(Integer)V[v].vertexes.get(i));
            }
        }
        V[v].mark = "black";
    }

    public static int DFS2(Vertex[] V, LinkedList queue){
        int c = 0;
        while (!queue.isEmpty()){
            Vertex A = (Vertex) queue.pop();
            if (A.comp == -1){
                VisitVertex2(V,A,c,A.number);
                c++;
            }
        }
        return c;
    }

    public static void VisitVertex2(Vertex[] V,Vertex A,int c,int v ){
        A.comp = c;
        for (int i = 0;i < A.vertexes.size();i++){
            if (V[(Integer)A.vertexes.get(i)].comp == -1 && V[(Integer)A.vertexes.get(i)].parent != v){
                VisitVertex2(V,V[(Integer)A.vertexes.get(i)],c,(Integer)A.vertexes.get(i));
            }
        }
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        Vertex[] V = new Vertex[N];
        for (int i = 0;i < N;i++){
            V[i] = new Vertex(i);
        }
        for (int i = 0;i < M;i++){
            int u = in.nextInt();
            int v = in.nextInt();
            V[u].vertexes.add(v);
            V[v].vertexes.add(u);
        }
        LinkedList queue = new LinkedList<Vertex>();
        DFS1(V,queue,N);
        component += DFS2(V,queue);
        System.out.println(component);
    }
}
