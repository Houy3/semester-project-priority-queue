
public class Main {
    public static void main(String[] args) {

        PriorityQueue qu = new PriorityQueue(11);
//add, remove, element

        qu.add(45);
        qu.add(11);
        qu.add(18);
        qu.add(27);
        qu.add(67);
        qu.add(12);
        qu.add(11);
        qu.add(7);


        int size = qu.size();
        for (int i = 0; i < size; i++) {
            System.out.print(qu.poll() + " ");
        }

    }
}
