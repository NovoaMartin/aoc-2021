import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        int h = 0, d = 0;
        Scanner sc = new Scanner(new File("d2.1/input"));
        while (sc.hasNext()) {
            char order = sc.next().charAt(0);
            if (order == 'f') {
                h += sc.nextInt();
            }
            if (order == 'd') {
                d += sc.nextInt();
            }
            if (order == 'u') {
                d -= sc.nextInt();
            }
        }
        System.out.println(d * h);
    }
}
