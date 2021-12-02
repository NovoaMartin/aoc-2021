import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> inputs = new LinkedList<>();
        Scanner sc = new Scanner(new File("d1.1/input"));
        while (sc.hasNextInt()) {
            inputs.add(sc.nextInt());
        }
        int count = 0;
        int prev = inputs.get(0);
        for (int input : inputs) {
            if (input > prev) {
                count++;
            }
            prev = input;
        }
        System.out.println(count);
    }
}
