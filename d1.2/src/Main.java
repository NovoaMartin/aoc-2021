import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> inputs = new ArrayList<>();
        Scanner sc = new Scanner(new File("d1.1/input"));
        while (sc.hasNextInt()) {
            inputs.add(sc.nextInt());
        }
        List<Integer> sum = new ArrayList<>();

        for (int i = 0; i < inputs.size() - 2; i++) {
            sum.add(inputs.get(i) + inputs.get(i + 1) + inputs.get(i + 2));
        }
        int count = 0;
        int prev = sum.get(0);
        for (int res : sum) {
            if (prev < res) {
                count++;
            }
            prev = res;
        }
        System.out.println(count);

    }
}
