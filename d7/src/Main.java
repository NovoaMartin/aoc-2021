import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }

    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d7/input.txt")).useDelimiter(",");
        ArrayList<Integer> positions = new ArrayList<>();
        int sum = 0;
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            sum += n;
            positions.add(n);
        }
        int avg = sum / (positions.size()-1);
        System.out.println(avg);
        long fuel = 0;
        for (int n : positions) {
            int m = Math.abs(n - avg);
            fuel += (long) m * (m + 1) / 2;
        }
        System.out.println(fuel);
    }

    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d7/input.txt")).useDelimiter(",");
        ArrayList<Integer> positions = new ArrayList<>();
        while (sc.hasNextInt())
            positions.add(sc.nextInt());
        Collections.sort(positions);
        int median = positions.get(positions.size() / 2);
        long fuel = 0;
        for (int n : positions) {
            fuel += Math.abs(n - median);
        }
        System.out.println(fuel);
    }
}
