import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderWriter {
    public static int[][] readFile(String filePath) throws FileNotFoundException {
        ArrayList<int[]> retval = new ArrayList();

        int[][] tmp = null;
        int[] t = null;
        Scanner sc = new Scanner(new File(filePath));
        try {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                t = splitStringToInt(str, " ");
                retval.add(t);
            }
            tmp = new int[t.length][t.length];
            for (int i = 0; i < retval.size(); i++) {
                for (int j = 0; j < t.length; j++) {
                    if (j < retval.get(i).length) {
                        tmp[i][j] = retval.get(i)[j];
                    } else {
                        tmp[i][j] = retval.get(j)[i];
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("nope");
        }
        return tmp;
    }
    static int[] splitStringToInt(String str, String reg) {
        String[] arr = str.split(reg);
        int[] list = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            list[i] = Integer.parseInt(arr[i]);
        }
        return list;
    }
}
