import java.util.*;

class Vertex{
    private ArrayList vertexes = new ArrayList<Integer>();
    private String mark = "white";
    private int comp = -1;
    private int parent = 0;
    private int number;
    Vertex(int n){
        number = n;
    }

    public ArrayList getVertexes() {
        return vertexes;
    }

    public String getMark() {
        return mark;
    }

    public int getComp() {
        return comp;
    }

    public int getNumber() {
        return number;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }
}
public class BridgeNum{
    private static int component = 0;
    public static void DFS1(Vertex[] V,LinkedList queue,int N){
        for (int i = 0;i < N;i++){
            if (V[i].getMark().equals("white")){
                V[i].setParent(1);
                component--;
                VisitVertex1(V,queue,i);
            }
        }
    }
    public static void VisitVertex1(Vertex[] V,LinkedList queue,int v){
        V[v].setMark("gray");
        queue.add(V[v]);
        for (int i = 0;i < V[v].getVertexes().size();i++){
            if (V[(Integer)V[v].getVertexes().get(i)].getMark().equals("white") ){
                V[(Integer)V[v].getVertexes().get(i)].setParent(v);
                VisitVertex1(V,queue,(Integer)V[v].getVertexes().get(i));
            }
        }
        V[v].setMark("black");
    }

    public static int DFS2(Vertex[] V, LinkedList queue){
        int c = 0;
        while (!queue.isEmpty()){
            Vertex A = (Vertex) queue.pop();
            if (A.getComp() == -1){
                VisitVertex2(V,A,c,A.getNumber());
                c++;
            }
        }
        return c;
    }

    public static void VisitVertex2(Vertex[] V,Vertex A,int c,int v ){
        A.setComp(c);
        for (int i = 0;i < A.getVertexes().size();i++){
            if (V[(Integer)A.getVertexes().get(i)].getComp() == -1 && V[(Integer)A.getVertexes().get(i)].getParent() != v){
                VisitVertex2(V,V[(Integer)A.getVertexes().get(i)],c,(Integer)A.getVertexes().get(i));
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
            V[u].getVertexes().add(v);
            V[v].getVertexes().add(u);
        }
        LinkedList queue = new LinkedList<Vertex>();
        DFS1(V,queue,N);
        component += DFS2(V,queue);
        System.out.println(component);
    }
}
