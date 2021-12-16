import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d16 {
    int count = 0;

    public static void main(String[] args) throws FileNotFoundException {
        d16 solve = new d16();
    }

    public d16() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d16/input.txt"));
        Queue<Character> input = new ArrayDeque<>(Arrays.asList(hexToBin(sc.nextLine()).chars().mapToObj(ch -> (char) ch).toArray(Character[]::new)));
        long res = readPackage(input);
        System.out.println("Res: " + res);
        System.out.println("Count: " + this.count);
    }

    public long readPackage(Queue<Character> subPackage) {
        int version = Integer.parseInt(readChars(3, subPackage), 2);
        int type = Integer.parseInt(readChars(3, subPackage), 2);
        this.count += version;
        if (type == 4) {
            return readLiteralPackage(subPackage);
        } else {
            return readOperationPackage(subPackage, type);
        }
    }

    private long readOperationPackage(Queue<Character> pack, int type) {
        ArrayList<Long> operators = new ArrayList<>();
        if (Objects.equals(readChars(1, pack), "0")) {
            int length = Integer.parseInt(readChars(15, pack), 2);

            Queue<Character> subPackage = new ArrayDeque<>();
            for (int i = 0; i < length; i++) {
                subPackage.add(pack.poll());
            }
            while (!subPackage.isEmpty())
                operators.add(readPackage(subPackage));
        } else {
            int amount = Integer.parseInt(readChars(11, pack), 2);
            for (int i = 0; i < amount; i++) {
                operators.add(readPackage(pack));
            }
        }
        operate(type, operators);
        return operators.get(0);
    }

    public long readLiteralPackage(Queue<Character> subPackage) {
        StringBuilder res = new StringBuilder();
        while (Objects.equals(readChars(1, subPackage), "1")) {
            res.append(readChars(4, subPackage));
        }
        res.append(readChars(4, subPackage));
        return Long.parseLong(res.toString(), 2);
    }

    public void operate(int type, ArrayList<Long> operators) {
        if (type == 0) {
            long count = 0;
            for (Long n : operators)
                count += n;
            operators.clear();
            operators.add(count);
        } else if (type == 1) {
            long count = 1;
            for (Long n : operators)
                count *= n;
            operators.clear();
            operators.add(count);
        } else if (type == 2) {
            long min = operators.get(0);
            for (Long n : operators)
                min = n < min ? n : min;
            operators.clear();
            operators.add(min);
        } else if (type == 3) {
            long max = operators.get(0);
            for (Long n : operators)
                max = n > max ? n : max;
            operators.clear();
            operators.add(max);
        } else if (type == 5) {
            long res = operators.get(0) > operators.get(1) ? 1 : 0;
            operators.clear();
            operators.add(res);
        } else if (type == 6) {
            long res = operators.get(0) < operators.get(1) ? 1 : 0;
            operators.clear();
            operators.add(res);
        } else if (type == 7) {
            long res = Objects.equals(operators.get(0), operators.get(1)) ? 1 : 0;
            operators.clear();
            operators.add(res);
        }
    }


    public String readChars(int n, Queue<Character> input) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++)
            res.append(input.poll());
        return res.toString();
    }

    public String hexToBin(String hex) {
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }
}
