import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    private static class Line {
        public Line(int x1, int y1, int x2, int y2, boolean part2, int[][] board) {
            if (x1 == x2) {
                if (y1 < y2) {
                    for (int i = y1; i <= y2; i++) {
                        mark(board, x1, i);
                    }
                } else {
                    for (int i = y2; i <= y1; i++) {
                        mark(board, x1, i);
                    }
                }
            } else if (y1 == y2) {
                if (x1 < x2) {
                    for (int i = x1; i <= x2; i++) {
                        mark(board, i, y1);
                    }
                } else {
                    for (int i = x2; i <= x1; i++) {
                        mark(board, i, y1);
                    }
                }
            } else {
                if (!part2) return;
                if (x1 < x2 && y1 < y2) {
                    for (int i = x1, j = y1; i <= x2; i++, j++) {
                        mark(board, i, j);
                    }
                } else if (x1 < x2 && y1 > y2) {
                    for (int i = x1, j = y1; i <= x2; i++, j--) {
                        mark(board, i, j);
                    }
                } else if (x1 > x2 && y1 < y2) {
                    for (int i = x1, j = y1; j <= y2; i--, j++) {
                        mark(board, i, j);
                    }
                } else {
                    for (int i = x1, j = y1; i >= x2; i--, j--) {
                        mark(board, i, j);
                    }
                }
            }
        }

        public void mark(int[][] board, int x, int y) {
            board[x][y]++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        final boolean part2 = true;
        long time = System.currentTimeMillis();
        int dimension = 1000;
        int[][] board = new int[dimension][dimension];
        ArrayList<Line> lines = new ArrayList<>();
        Scanner scan = new Scanner(new File("d5/input.txt"));
        while (scan.hasNext()) {
            String str = scan.next().replaceAll("[^0-9]", " ");
            scan.next();
            String str2 = scan.next().replaceAll("[^0-9]", " ");
            str = str + " " + str2;
            Scanner sc = new Scanner(str);
            lines.add(new Line(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), part2, board));
        }
        int c = Stream.of(board).flatMapToInt(IntStream::of).filter(i -> i > 1).toArray().length;
        time = System.currentTimeMillis() - time;
        System.out.println(c);
        System.out.println("Time: " + time + "ms");
    }
}
