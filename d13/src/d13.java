import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d13 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d13/input.txt"));
        ArrayList<String> input = new ArrayList<>();
        HashSet<Point> points = new HashSet<>();
        ArrayList<String> folds = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (Objects.equals(line, ""))
                continue;
            if (line.split(",").length == 2) {
                points.add(new Point(Integer.parseInt(line.split(",")[0]), Integer.parseInt(line.split(",")[1])));
            } else {
                folds.add(line);
            }
        }
        input.add("asd");
        int i = 0;
        for (String fold : folds) {
            String variable = fold.split("=")[0].split(" ")[2];
            int value = Integer.parseInt(fold.split("=")[1]);
            points = fold(points, variable, value);
            if (i == 0) {
                System.out.println("PART 1: " + points.size());
            }
            i++;
        }
        char[][] view = new char[40][40];
        for (char[] a : view)
            Arrays.fill(a, ' ');
        for (Point p : points) {
            view[p.x][p.y] = '#';
        }
        for (char[] a : view) {
            System.out.println(Arrays.toString(a));
        }
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static HashSet<Point> fold(HashSet<Point> points, String variable, int value) {
        HashSet<Point> afterFold = new HashSet<>();
        for (Point p : points) {
            if (Objects.equals(variable, "y")) {
                if (p.y > value) {
                    afterFold.add(new Point(p.x, value * 2 - p.y));
                } else if (p.y != value) {
                    afterFold.add(new Point(p.x, p.y));
                }
            } else if (Objects.equals(variable, "x")) {
                if (p.x > value) {
                    afterFold.add(new Point(value * 2 - p.x, p.y));
                } else if (p.x != value) {
                    afterFold.add(new Point(p.x, p.y));
                }
            }
        }
        return afterFold;
    }
}
