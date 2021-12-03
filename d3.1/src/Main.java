import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d3.1/input.txt"));
        String s = sc.next();
        int[] res = new int[s.length()];
        Arrays.fill(res, 0);
        int i = 0;
        do {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    res[j]++;
                }
            }
            i++;
            if (sc.hasNext())
                s = sc.next();
            else
                break;
        } while (true);
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int j = 0; j < s.length(); j++) {
            s1.append(res[j] > i / 2 ? 1 : 0);
            s2.append(res[j] > i / 2 ? 0 : 1);
        }

        System.out.println(s1.toString());
        System.out.println(s2.toString());
        System.out.println
                (
                        Integer.parseInt(s1.toString(), 2)
                                *
                                Integer.parseInt(s2.toString(), 2)
                );

    }
}
