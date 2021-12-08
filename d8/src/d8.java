import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d8 {
    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }

    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d8/input.txt"));
        long count = 0;
        while (sc.hasNextLine()) {
            Patterns p = new Patterns();
            String[] line = sc.nextLine().split("\\| ");
            String[] dataArr = line[0].split(" ");
            ArrayList<String> data = new ArrayList<>();
            for (String s : dataArr) {
                char[] sArr = s.toCharArray();
                Arrays.sort(sArr);
                data.add(new String(sArr));
            }
            String[] outArr = line[1].split(" ");
            ArrayList<String> out = new ArrayList<>();
            for (String s : outArr) {
                char[] sArr = s.toCharArray();
                Arrays.sort(sArr);
                out.add(new String(sArr));
            }

            while (data.size() > 0) {
                data.removeIf(s -> p.getRes(s) != -1);
            }
            StringBuilder res = new StringBuilder();
            for (String s : out) {
                res.append(p.getRes(s));
            }
            count += Integer.parseInt(res.toString());
        }
        System.out.println(count);
    }

    public static class Patterns {
        HashMap<Integer, String> patterns = new HashMap<>();

        public int intersects(String pattern, String toCheck) {
            int count = 0;
            for (char c : pattern.toCharArray()) {
                for (char c2 : toCheck.toCharArray()) {
                    if (c == c2)
                        count++;
                }
            }
            return count;
        }

        public int getRes(String s) {
            int l = s.length();
            if (l == 2) {
                patterns.put(1, s);
                return 1;
            }
            if (l == 4) {
                patterns.put(4, s);
                return 4;
            }
            if (l == 3) {
                patterns.put(7, s);
                return 7;
            }
            if (l == 7) {
                patterns.put(8, s);
                return 8;
            }
            if (l == 5) {
                if (!patterns.containsKey(1) || !patterns.containsKey(4)) {
                    return -1;
                }
                if (intersects(patterns.get(1), s) == 2) {
                    patterns.put(3, s);
                    return 3;
                } else if (intersects(patterns.get(4), s) == 2) {
                    patterns.put(2, s);
                    return 2;
                } else {
                    patterns.put(5, s);
                    return 5;
                }
            }
            if (l == 6) {
                if (!patterns.containsKey(4) || !patterns.containsKey(7)) {
                    return -1;
                }
                if (intersects(patterns.get(4), s) == 4) {
                    patterns.put(9, s);
                    return 9;
                }
                if (intersects(patterns.get(7), s) == 3) {
                    patterns.put(0, s);
                    return 0;
                } else {
                    patterns.put(6, s);
                    return 6;
                }
            }
            return -1;
        }
    }


    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d8/input.txt"));
        int count = 0;
        while (sc.hasNext()) {
            String s = sc.nextLine().split("\\|")[1];
            Scanner sc2 = new Scanner(s);
            while (sc2.hasNext()) {
                int l = sc2.next().length();
                if (l == 2 || l == 3 || l == 4 || l == 7) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
