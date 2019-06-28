public class StrQueue implements Comparable<StrQueue>{
    private String[] str;
    private int count;
    private int head;
    private int tail = 0;
    private int countChar;
    StrQueue(int n){
        str = new String[n];
    }
    public int getCount(){
        return count;
    }
    public int getCountChar(){
        return countChar;
    }
    public int getHead(){
        return head;
    }
    public int getTail(){
        return tail;
    }
    private void increaseQueue(){
        String[] s;
        s = str;
        String[] x = new String[2 * str.length];
        System.arraycopy(str, 0, x, 0, str.length);
        str = new String[2 * x.length];
        str = x;
        int k = 0;
        for (int i = head;i < tail && i < str.length && k != count;i++) {
            str[k] = s[i];
            k++;
        }
        for (int i = 0;i < head && i < tail;i++) {
            str[k] = s[i];
            k++;
        }
        for (int i = tail;tail < count;i++){
            str[k] = s[i];
            k++;
        }
        tail = count;
        head = 0;
    }
    public boolean checkEmpty(){
        return getCount() > 0;
    }
    public void addTail(String s){
        if (count == str.length)
            increaseQueue();
        str[tail] = s;
        tail++;
        if (tail == str.length)
            tail = 0;
        count++;
        countChar += calculateSymbols(s);
    }
    public String deleteHead(){
        if (!checkEmpty())
            throw new RuntimeException("Queue is already empty");
        String x = str[head];
        str[head] = "";
        int a = calculateSymbols(x);
        countChar -= a;
        head++;
        if (head == str.length)
            head = 0;
        count--;
        return x;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = head; i < tail && i < str.length; i++) {
            s.append(str[i]);
            s.append(" ");
        }
        for (int i = 0;i < head && i < tail;i++) {
            s.append(str[i]);
            s.append(" ");
        }
        for (int i = tail; i < head && i < count;i++) {
            s.append(str[tail]);
            s.append(" ");
        }
        return s.toString();
    }
    private int calculateSymbols(String s){
        int c = 0;
        for (int i = 0;i < s.length();i++)
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z' || s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                c++;
        return c;
    }
    public int compareTo(StrQueue obj){
        return countChar - obj.countChar > 0 ? 1 : -1;
    }
}



