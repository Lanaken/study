import java.util.*;


public class Mars {
    private static ArrayList<Integer> arrayList;
    private static ArrayList<Integer> groupOne = new ArrayList<>();
    private static ArrayList<Integer> groupTwo = new ArrayList<>();
    private static ArrayList<Integer> conflictCosmonauts = new ArrayList<>();
    private static ArrayList<Integer> notConflictedCosmonauts = new ArrayList<>();
    private static ArrayList<Integer> finalGroup = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int n;
        boolean[] a = new boolean[N];
        boolean[] b = new boolean[N];
        String[][] matrix = new String[N][N];
        Vertex[] cosmonauts = new Vertex[N];
        for (int i = 0; i < N; i++) {
            cosmonauts[i] = new Vertex(i);
            for (int j = 0; j < N; j++) {
                matrix[i][j] = in.next();
                if (matrix[i][j].equals("+")) {
                    cosmonauts[i].addBad(j);
                    if (conflictCosmonauts.indexOf(cosmonauts[i].getNumber()) == -1) {
                        conflictCosmonauts.add(cosmonauts[i].getNumber());
                    }
                }
            }
            if (conflictCosmonauts.indexOf(cosmonauts[i].getNumber()) == -1)
                notConflictedCosmonauts.add(cosmonauts[i].getNumber());
        }
        boolean[] c = new boolean[conflictCosmonauts.size()];
        Graph[] groups = new Graph[conflictCosmonauts.size()];
        for (int i = 0; i < conflictCosmonauts.size(); i++)
            groups[i] = new Graph();
        int graphSize = 0;
        for (int i = 0; i < conflictCosmonauts.size(); i++) {
            if (!c[i]) {
                groups[i].addToFirstGroup(conflictCosmonauts.get(i));
                graphSize++;
            }
            ArrayList<Integer> enemies = cosmonauts[conflictCosmonauts.get(i)].getBadCosmonauts();
            for (int j = 0; j < enemies.size(); j++) {
                Vertex enemy = cosmonauts[enemies.get(j)];
                if (!c[conflictCosmonauts.indexOf(enemy.getNumber())]) {
                    groups[i].addToSecondGroup(enemy.getNumber());
                    c[conflictCosmonauts.indexOf(enemy.getNumber())] = true;
                    makeGroups(cosmonauts, enemy, groups[i], c);

                }
            }
        }
        Set<Integer>[] sets = new Set[graphSize * 2];
        for (int i = 0, k = 0; i < conflictCosmonauts.size() && k < graphSize * 2; i++) {
            if (groups[i].getFirstGroup().size() != 0) {
                sets[k] = new LinkedHashSet<>(groups[i].getFirstGroup());
                k++;
                sets[k] = new LinkedHashSet<>(groups[i].getSecondGroup());
                k++;
            }

        }
        for (int i = 0; i < N; i++)
            findSize(b, a, cosmonauts[i], cosmonauts);
        if (groupTwo.size() < groupOne.size())
            n = groupTwo.size();
        else
            n = groupOne.size();
        ArrayList<Integer> comparedArrayList = new ArrayList<>();
        int k;
        int size1;
        int size2;
        int m;
        if ( sets.length > 4) {
            for (int j = 0; j < 2; j++) {
                k = sets.length - 1;
                m = 0;
                while (m < (1 << graphSize) / 2 && k >= sets.length / 2) {
                    size1 = 2;
                    size2 = 2;
                    ArrayList<Set<Integer>> arrayList1 = new ArrayList<>();
                    ArrayList<Set<Integer>> arrayList2 = new ArrayList<>();
                    if (k >= sets.length - 2) {
                        arrayList1.add(sets[j]);
                        arrayList1.add(sets[k]);
                        arrayList2.add(sets[j]);
                        arrayList2.add(sets[k]);
                        for (int i = j + 2; i < k; i += 2) {
                            if (i % 2 == 0) {
                                arrayList1.add(sets[i]);

                                arrayList2.add(sets[i + 1]);
                                size1++;
                                size2++;
                            } else {
                                arrayList1.add(sets[i - 1]);
                                arrayList2.add(sets[i]);
                                size1++;
                                size2++;
                            }
                            if (size1 == graphSize && size1 == size2) {
                                m++;
                                comparedArrayList = compareGroups(arrayList1, arrayList2, n);
                                break;
                            }
                        }
                    }
                    k--;
                    if (finalGroup.isEmpty())
                        finalGroup = comparedArrayList;
                    else if (finalGroup.size() >= comparedArrayList.size())
                        finalGroup = compareGroups1(finalGroup, comparedArrayList, n);
                }
            }
        }
        else if (sets.length != 0){
            ArrayList<Set<Integer>> arrayList1 = new ArrayList<>();
            ArrayList<Set<Integer>> arrayList2 = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                arrayList1.add(sets[i]);
                arrayList2.add(sets[i + 1]);
                for (int j = i + 2;j < sets.length - 1;j++){
                    if (j % 2 == 0)
                        if (arrayList1.indexOf(sets[j + 1]) == -1){
                            arrayList1.add(sets[j]);
                            arrayList2.add(sets[j + 1]);
                        }
                        else if (arrayList1.indexOf(sets[j - 1]) == -1) {
                            arrayList1.add(sets[j]);
                            arrayList2.add(sets[j - 1]);
                        }
                    comparedArrayList = compareGroups(arrayList1,arrayList2,n);
                }
                comparedArrayList = compareGroups(arrayList1,arrayList2,n);
                finalGroup = comparedArrayList;
            }
        }
        else for (int i = 0;i < n;i++){
                System.out.println(notConflictedCosmonauts.get(i) + 1);
            }
        for (int i = 0;i < finalGroup.size();i++){
            System.out.print(finalGroup.get(i) + 1 + " ");
        }
    }


    static void findSize(boolean[] b, boolean[] a, Vertex cosmonaut, Vertex[] cosmonauts) {
        if (!a[cosmonaut.getNumber()]) {
            a[cosmonaut.getNumber()] = true;
            if (groupOne.size() <= groupTwo.size())
                groupOne.add(cosmonaut.getNumber());
            else groupTwo.add(cosmonaut.getNumber());
        }
        if (!b[cosmonaut.getNumber()]) {
            b[cosmonaut.getNumber()] = true;
            arrayList = cosmonaut.getBadCosmonauts();
            if (arrayList != null && arrayList.size() != 0)
                for (int j = 0; j < arrayList.size(); j++) {
                    if (!a[arrayList.get(j)]) {
                        if (groupTwo.indexOf(cosmonaut.getNumber()) == -1)
                            groupTwo.add(cosmonaut.getBadCosmonauts().get(j));
                        else groupOne.add(cosmonaut.getBadCosmonauts().get(j));
                        a[cosmonauts[arrayList.get(j)].getNumber()] = true;
                        findSize(b, a, cosmonauts[arrayList.get(j)], cosmonauts);

                    }
                }
        }
    }

    static void makeGroups(Vertex[] cosmonauts, Vertex badCosmonaut, Graph graph, boolean[] c) {
        for (int i = 0; i < badCosmonaut.getBadCosmonauts().size(); i++) {
            Vertex cos = cosmonauts[badCosmonaut.getBadCosmonauts().get(i)];
            if (!c[conflictCosmonauts.indexOf(cos.getNumber())])
                if (graph.getFirstGroup().indexOf(badCosmonaut.getNumber()) == -1) {
                    graph.addToFirstGroup(cosmonauts[badCosmonaut.getBadCosmonauts().get(i)].getNumber());
                    c[conflictCosmonauts.indexOf(cos.getNumber())] = true;
                    makeGroups(cosmonauts, cosmonauts[badCosmonaut.getBadCosmonauts().get(i)], graph, c);
                } else {
                    graph.addToSecondGroup(cosmonauts[badCosmonaut.getBadCosmonauts().get(i)].getNumber());
                    c[conflictCosmonauts.indexOf(cos.getNumber())] = true;
                    makeGroups(cosmonauts, cosmonauts[badCosmonaut.getBadCosmonauts().get(i)], graph, c);
                }
            else if (graph.getFirstGroup().indexOf(badCosmonaut.getNumber()) == -1 && graph.getFirstGroup().indexOf(cos.getNumber()) == -1) {
                System.out.println("No solution");
                System.exit(0);
            } else if (graph.getSecondGroup().indexOf(badCosmonaut.getNumber()) == -1 && graph.getSecondGroup().indexOf(cos.getNumber()) == -1) {
                System.out.println("No solution");
                System.exit(0);
            }

        }
    }

    private static ArrayList<Integer> compareGroups(ArrayList<Set<Integer>> A, ArrayList<Set<Integer>>B,int n) {
        int l = 0;
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        for (Set<Integer> i : A){
            a.addAll(i);
        }
        for (Set<Integer> i : B){
            b.addAll(i);
        }
        for (int i = a.size(); i < n; i++) {

            a.add(notConflictedCosmonauts.get(l));
            l++;
        }
        l = 0;
        for (int i = b.size(); i < n; i++) {
            b.add(notConflictedCosmonauts.get(l));
            l++;
        }
        Collections.sort(a);
        Collections.sort(b);
        for (int i = 0; i < n; i++) {
            if (a.get(i) < b.get(i))
                return a;
            if (b.get(i) < a.get(i))
                return b;
        }
        return a;
    }
    private static ArrayList<Integer> compareGroups1(ArrayList<Integer> a, ArrayList<Integer> b,int n) {
        int l = 0;
        for (int i = a.size(); i < n; i++) {
            a.add(notConflictedCosmonauts.get(l));
            l++;
        }
        l = 0;
        for (int i = b.size(); i < n; i++) {
            b.add(notConflictedCosmonauts.get(l));
            l++;
        }
        Collections.sort(a);
        Collections.sort(b);
        for (int i = 0; i < n; i++) {
            if (a.get(i) < b.get(i))
                return a;
            if (b.get(i) < a.get(i))
                return b;
        }
        return a;
    }
}
