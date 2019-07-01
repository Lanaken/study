import java.util.ArrayList;

public class Vertex {
    private int number;
    private ArrayList<Integer> badCosmonauts = new ArrayList<>();

    Vertex(int n) {
        number = n;
    }

    public void addBad(int person) {
        badCosmonauts.add(person);
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Integer> getBadCosmonauts() {
        return badCosmonauts;
    }
}