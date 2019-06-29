import java.util.*;


public class Dividers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long number = in.nextLong();
        long i = number;
        long divider = 1;
        ArrayList<Long> numbers = new ArrayList<>();
        while (i > divider) {
            if (number % divider == 0) {
                i = number / divider;
                numbers.add(i);
                if (i != divider)
                    numbers.add(divider);
            }
            divider++;
        }
        Collections.sort(numbers);
        int size = numbers.size();
        StringBuilder out = new StringBuilder();
        out.append("graph {\n");
        int h;
        int f;
        if (number == 1)
            out.append("      " + 1 + "\n");
        for (Long j : numbers)
            out.append("      " + j + "\n");
        for (Long j : numbers){
            if (size > numbers.indexOf(j) + 1){
                for (Long k : numbers.subList(numbers.indexOf(j) + 1,size)){
                    if (k % j == 0){
                        f = 1;
                        h = numbers.indexOf(j) + 1;
                        while (numbers.get(h) * 2 <= k){
                            if (k % numbers.get(h) == 0 && numbers.get(h) % j == 0){
                                f = 0;
                                break;
                            }
                            h++;
                        }
                        if (f == 1)
                            out.append("      " + k + " -- " + j + "\n");
                    }
                }
            }
        }
        out.append("}");
        System.out.print(out);
    }
}