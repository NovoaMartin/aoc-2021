import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d10 {
    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }

    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d10/input.txt"));
        Map<String, Integer> values = Map.of(")", 1, "]", 2, "}", 3, ">", 4);
        ArrayList<String> opens = new ArrayList<>(Arrays.asList("{", "(", "<", "["));
        ArrayList<String> closes = new ArrayList<>(Arrays.asList("}", ")", ">", "]"));
        ArrayList<Long> scores = new ArrayList<>();
        while (sc.hasNextLine()) {
            boolean incorrect = false;
            String[] line = sc.nextLine().split("");
            Stack<String> nextClose = new Stack<>();
            for (String s : line) {
                if (opens.contains(s)) {
                    nextClose.add(closes.get(opens.indexOf(s)));
                } else {
                    if (!Objects.equals(s, nextClose.pop())) {
                        incorrect = true;
                        break;
                    }
                }
            }
            if (!incorrect) {
                long count = 0;
                while (!nextClose.isEmpty()) {
                    count *= 5;
                    count += values.get(nextClose.pop());
                }
                scores.add(count);
            }
        }
        Collections.sort(scores);
        System.out.println(scores.get(scores.size() / 2));
    }

    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d10/input.txt"));
        long count = 0;
        Map<String, Integer> values = Map.of(")", 3, "]", 57, "}", 1197, ">", 25137);
        ArrayList<String> opens = new ArrayList<>(Arrays.asList("{", "(", "<", "["));
        ArrayList<String> closes = new ArrayList<>(Arrays.asList("}", ")", ">", "]"));
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split("");
            Stack<String> nextClose = new Stack<>();
            for (String s : line) {
                if (opens.contains(s)) {
                    nextClose.add(closes.get(opens.indexOf(s)));
                } else {
                    if (!Objects.equals(s, nextClose.pop())) {
                        count += values.get(s);
                        break;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
