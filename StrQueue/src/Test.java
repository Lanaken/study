
import java.util.Arrays;

public class Test {
    public static void main(String[] args){
        StrQueue[] a = new StrQueue[]{
                new  StrQueue(5),
                new StrQueue(5),
                new StrQueue(5)
        };
        a[0].addTail("hgkhkg21");
        a[0].addTail("dsf");
        a[1].addTail("23123123ljk");
        a[2].addTail("hdgs1");
//        a[2].deleteHead();
        Arrays.sort(a);
        for (StrQueue i: a){
            System.out.println(i);
        }
    }
}