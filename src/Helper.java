import java.util.ArrayList;
import java.util.stream.IntStream;

public class Helper {
    public static ArrayList<Integer> getRangeList(int s) {
        int[] range = IntStream.iterate(0, n -> n + 1).limit(s).toArray();
        ArrayList<Integer> list = new ArrayList();
        for (int i = 0; i < range.length; i++) {
            list.add(range[i]);
        }
        return list;
    }

    public static int[] toArray(ArrayList<Integer> list) {
        int[] retval = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {

            retval[i] = list.get(i);
        }
        return retval;
    }

    static void displayArray(int[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            displayArray(tab[i]);
            System.out.println();
        }
        System.out.println();
    }

    static void displayArray(int[] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + " ");
        }
    }
}
