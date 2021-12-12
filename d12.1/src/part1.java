import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
        res();
    }

    public static class Node {
        String name;
        HashSet<String> adyacents;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public Node(String name, String adyacent) {
            this.name = name;
            this.adyacents = new HashSet<>();
            this.adyacents.add(adyacent);
        }

        public void addAdyacent(String adyacent) {
            this.adyacents.add(adyacent);
        }
    }

    public static void res() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d12.1/input.txt"));
        HashMap<String, Node> nodes = new HashMap<>();

        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split("-");

            if (nodes.containsKey(line[0])) {
                nodes.get(line[0]).addAdyacent(line[1]);
            } else {
                nodes.put(line[0], new Node(line[0], line[1]));
            }
            if (nodes.containsKey(line[1])) {
                nodes.get(line[1]).addAdyacent(line[0]);
            } else {
                nodes.put(line[1], new Node(line[1], line[0]));
            }
        }
        HashSet<String> visited = new HashSet<>();
        visited.add("start");
        System.out.println(findPaths(nodes, "start", visited));
    }

    public static int findPaths(HashMap<String, Node> graph, String start, HashSet<String> visited) {
        Node startNode = graph.get(start);
        int count = 0;
        if (!Character.isUpperCase(start.charAt(0)) && !start.equals("end"))
            visited.add(start);
        if (Objects.equals(startNode.name, "end"))
            return 1;
        for (String adyacent : startNode.adyacents) {
            if (!visited.contains(adyacent)) {
                count += findPaths(graph, adyacent, new HashSet<>(visited));
            }
        }
        return count;
    }

}
