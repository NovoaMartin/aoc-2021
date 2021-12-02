import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int h = 0, d = 0, aim = 0;
        Scanner sc = new Scanner(new File("d2.1/input"));
        while (sc.hasNext()) {
            char order = sc.next().charAt(0);
            int n = sc.nextInt();
            if (order == 'f') {
                h += n;
                d += aim * n;
            }
            if (order == 'd') {
                aim += n;
            }
            if (order == 'u') {
                aim -= n;
            }
        }
        System.out.println(d * h);
    }
}
