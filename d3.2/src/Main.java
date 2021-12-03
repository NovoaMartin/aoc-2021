import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        long t = System.currentTimeMillis();
        Scanner sc = new Scanner(new File("d3.1/input.txt"));
        ArrayList<String> input1 = new ArrayList<>();
        while (sc.hasNext())
            input1.add(sc.next());
        ArrayList<String> input2 = new ArrayList<>(input1);

        int i = 0;
        while (input1.size() > 1) {
            char[] mostCommon = mostCommon(input1);
            Iterator<String> it1 = input1.iterator();
            while (it1.hasNext() && input1.size() > 1) {
                if (it1.next().charAt(i) != mostCommon[i]) {
                    it1.remove();
                }
            }
            i++;
        }
        i = 0;
        while (input2.size() > 1) {
            char[] leastCommon = leastCommon(input2);
            Iterator<String> it2 = input2.iterator();
            while (it2.hasNext() && input2.size() > 1) {
                if (it2.next().charAt(i) != leastCommon[i]) {
                    it2.remove();
                }
            }
            i++;
        }
        System.out.println(Integer.parseInt(input1.get(0), 2) * Integer.parseInt(input2.get(0), 2));
        t = System.currentTimeMillis() - t;
        System.out.println(t);
    }

    public static char[] mostCommon(ArrayList<String> in) {
        int[] res = new int[in.get(0).length()];
        char[] res2 = new char[in.get(0).length()];
        Arrays.fill(res, 0);
        int cant = 0;
        for (String val : in) {
            cant++;
            for (int i = 0; i < val.length(); i++) {
                res[i] += val.charAt(i) == '1' ? 1 : -1;
            }
        }
        for (int i = 0; i < res.length; i++) {
            res2[i] = res[i] >= 0 ? '1' : '0';
        }
        return res2;
    }

    public static char[] leastCommon(ArrayList<String> in) {
        int[] res = new int[in.get(0).length()];
        char[] res2 = new char[in.get(0).length()];
        Arrays.fill(res, 0);
        int cant = 0;
        for (String val : in) {
            cant++;
            for (int i = 0; i < val.length(); i++) {
                res[i] += val.charAt(i) == '1' ? -1 : 1;
            }
        }
        for (int i = 0; i < res.length; i++) {
            res2[i] = res[i] > 0 ? '1' : '0';
        }
        return res2;
    }
}
