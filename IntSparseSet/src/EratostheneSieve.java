import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class EratostheneSieve {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int n = in.nextInt();

        Set<Integer> primes = new IntSparseSet(2, n+1) {{
            for (int i = 2; i <= n; i++) add(i);
            for (int i = 2; i*i <= n; i++) {
                if (contains(i)) {
                    for (int j = i*i; j <= n; j += i) remove(j);
                }
            }
        }};

        for (int x : new TreeSet<>(primes)) {
            System.out.printf("%d ", x);
        }
        System.out.println();
    }
}