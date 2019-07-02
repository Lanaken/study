public class Test
{
    public static void main(String[] args)
    {
        Element<Integer> a = new Element<> (0),
                b = new Element<> (1),
                c = new Element<> (2),
                d = new Element<> (3),
                e = new Element<> (4),
                f = new Element<> (5);
        a.union(b);
        c.union(a);
        c.union(d);
        e.union(f);
        System.out.println("" + a.x() + "=" + d.x() + ": " +
                a.equivalent(d));
        System.out.println("" + a.x() + "=" + f.x() + ": " +
                a.equivalent(f));
    }
}