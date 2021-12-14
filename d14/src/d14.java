import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d14 {
    public static void main(String[] args) throws FileNotFoundException {
        long time, time2;
        time = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++)
        solve(false);
        time2 = System.currentTimeMillis();
//        System.out.println((time2 - time) / 10000);
    }

    public static void solve(boolean part1) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d14/input.txt"));
        ArrayList<String> template = new ArrayList<>(Arrays.asList(sc.nextLine().split("")));
        sc.nextLine();
        HashMap<String, String> rules = new HashMap<>();
        while (sc.hasNextLine()) {
            String[] rule = sc.nextLine().split(" -> ");
            rules.put(rule[0], rule[1]);
        }
        HashMap<String, Long> patterns = new HashMap<>();
        HashMap<String, Long> counter = new HashMap<>();
        for (int i = 0; i < template.size() - 1; i++) {
            String pattern = template.get(i) + template.get(i + 1);
            if (patterns.containsKey(pattern))
                patterns.put(pattern, patterns.get(pattern) + 1);
            else
                patterns.put(pattern, 1L);
        }
        for (String s : template) {
            if (counter.containsKey(s))
                counter.put(s, counter.get(s) + 1);
            else
                counter.put(s, 1L);
        }

        for (int i = 0; i < (part1 ? 10 : 40); i++) {
            patterns = polV2(patterns, rules, counter);
        }
        long c = 0;
        PriorityQueue<Long> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Long> min = new PriorityQueue<>();
        for (String s : counter.keySet()) {
            c += counter.get(s);
            max.add(counter.get(s));
            min.add(counter.get(s));
        }
        System.out.println("Size: " + c / (Math.pow(1024, 4)) + "TB");
        System.out.println("Res: " + (max.poll() - min.poll()));
    }

    public static HashMap<String, Long> polV2(HashMap<String, Long> patterns, HashMap<String, String> rules,
                                              HashMap<String, Long> counter) {
        HashMap<String, Long> patterns2 = new HashMap<>();
        for (String pattern : patterns.keySet()) {
            if (!rules.containsKey(pattern)) {
                patterns2.put(pattern, patterns.get(pattern));
                continue;
            }
            long initialAmount = patterns.get(pattern);
            String toReplace = rules.get(pattern);
            String newPattern1 = pattern.charAt(0) + toReplace;
            String newPattern2 = toReplace + pattern.charAt(1);

            if (patterns2.containsKey(newPattern1))
                patterns2.put(newPattern1, patterns2.get(newPattern1) + initialAmount);
            else
                patterns2.put(newPattern1, initialAmount);
            if (patterns2.containsKey(newPattern2))
                patterns2.put(newPattern2, patterns2.get(newPattern2) + initialAmount);
            else
                patterns2.put(newPattern2, initialAmount);


            if (counter.containsKey(toReplace))
                counter.put(toReplace, counter.get(toReplace) + initialAmount);
            else
                counter.put(toReplace, initialAmount);
        }
        return patterns2;
    }
}
