import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d9 {

    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }

    public static int visit(int i, int j, ArrayList<int[]> nums, boolean[][] visited) {
        visited[i][j] = true;
        if (nums.get(i)[j] == 9) {
            return 0;
        }
        int count = 1;
        if (i != 0 && !visited[i - 1][j])
            count += visit(i - 1, j, nums, visited);
        if (i != nums.size() - 1 && !visited[i + 1][j])
            count += visit(i + 1, j, nums, visited);
        if (j != 0 && !visited[i][j - 1])
            count += visit(i, j - 1, nums, visited);
        if (j != nums.get(i).length - 1 && !visited[i][j + 1])
            count += visit(i, j + 1, nums, visited);
        return count;
    }

    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d9/input.txt"));
        ArrayList<int[]> nums = new ArrayList<>();
        while (sc.hasNextLine()) {
            nums.add(Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray());
        }
        boolean[][] visited = new boolean[100][100];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).length; j++) {
                pq.add(visit(i, j, nums, visited));
            }
        }
        int res = 1;
        for (int i = 0; i < 3; i++) {
            if (!pq.isEmpty())
                res *= pq.poll();
        }
        System.out.println(res);
    }

    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d9/input.txt"));
        ArrayList<int[]> nums = new ArrayList<>();
        while (sc.hasNextLine()) {
            nums.add(Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray());
        }

        int count = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).length; j++) {
                int n = nums.get(i)[j];
                int top = Integer.MAX_VALUE, bot = Integer.MAX_VALUE, left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
                if (i != 0)
                    top = nums.get(i - 1)[j];
                if (i != nums.size() - 1)
                    bot = nums.get(i + 1)[j];
                if (j != 0)
                    left = nums.get(i)[j - 1];
                if (j != nums.get(i).length - 1)
                    right = nums.get(i)[j + 1];

                if (n < top && n < bot && n < left && n < right)
                    count += n + 1;
            }
        }
        System.out.println(count);

    }
}
