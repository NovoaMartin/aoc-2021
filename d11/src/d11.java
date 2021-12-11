import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class d11 {
    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }

    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d11/input.txt"));
        int[][] vals = new int[10][10];
        int i = 0;
        while (sc.hasNextLine()) {
            vals[i] = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
            i++;
        }
        for (i = 0; true; i++) {
            if (step(vals, new boolean[10][10]) == 100)
                break;
        }
        System.out.println(i + 1);
    }

    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d11/input.txt"));
        int[][] vals = new int[10][10];
        int i = 0;
        while (sc.hasNextLine()) {
            vals[i] = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
            i++;
        }
        int count = 0;
        for (i = 0; i < 100; i++) {
            count += step(vals, new boolean[10][10]);
        }
        System.out.println(count);
    }

    public static int step(int[][] vals, boolean[][] flashed) {
        int count = 0;
        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals[i].length; j++) {
                vals[i][j]++;
            }
        }
        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals[i].length; j++) {
                if (vals[i][j] > 9)
                    count += flash(vals, i, j, flashed);
            }
        }
        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals[i].length; j++) {
                if (flashed[i][j]) {
                    vals[i][j] = 0;
                }
            }
        }
        return count;
    }

    public static int flash(int[][] vals, int i, int j, boolean[][] flashed) {
        if (i < 0 || j < 0 || i > vals.length - 1 || j > vals[0].length - 1)
            return 0;
        vals[i][j]++;
        int count = 0;
        if (vals[i][j] > 9 && !flashed[i][j]) {
            vals[i][j] = 0;
            flashed[i][j] = true;
            vals[i][j]++;
            count++;
            count += flash(vals, i - 1, j, flashed);
            count += flash(vals, i + 1, j, flashed);
            count += flash(vals, i, j - 1, flashed);
            count += flash(vals, i, j + 1, flashed);
            count += flash(vals, i - 1, j - 1, flashed);
            count += flash(vals, i - 1, j + 1, flashed);
            count += flash(vals, i + 1, j - 1, flashed);
            count += flash(vals, i + 1, j + 1, flashed);
        }
        return count;
    }
}
