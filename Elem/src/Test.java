



public class Test {
    public static void main(String[] args){
        Elem a = new Elem(5);
        Elem b = new Elem(10);
        Elem c = new Elem (15);
        System.out.println(a);
        a.insertAfter(b);
        c.insertAfter(a);
        System.out.println(b.findNumber(15));
    }
}
