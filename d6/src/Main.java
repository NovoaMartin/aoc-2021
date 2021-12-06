import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        final boolean part2 = false;
        long time = System.currentTimeMillis();
        Scanner sc = new Scanner(new File("d6/input.txt")).useDelimiter(",");
        long count = 0;
        HashMap<Integer, Long> ages = new HashMap<>();
        for (int i = -1; i <= 8; i++) {
            ages.put(i, 0L);
        }
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            ages.put(n, ages.get(n) + 1);
            count++;
        }
        for (int i = 0; i <(part2 ? 256 : 80); i++) {
            for (int j = -1; j < 8; j++) {
                ages.put(j, ages.get(j + 1));
            }
            count += ages.get(-1);
            ages.put(8, ages.get(-1));
            ages.put(6, ages.get(6) + ages.get(-1));
        }
        long time2 = System.currentTimeMillis();
        System.out.println(count);
        System.out.println("Time : " + (time2 - time) + "ms");
    }
}
