import java.util.ArrayList;

public class Graph {
    private ArrayList<Integer> firstGroup = new ArrayList<>();
    private ArrayList<Integer> secondGroup = new ArrayList<>();

    public ArrayList<Integer> getFirstGroup() {
        return firstGroup;
    }

    public ArrayList<Integer> getSecondGroup() {
        return secondGroup;
    }

    public void addToFirstGroup(int elem) {
        firstGroup.add(elem);
    }

    public void addToSecondGroup(int elem) {
        secondGroup.add(elem);
    }
}