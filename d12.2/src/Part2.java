import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        part2();
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

public static void part2() throws FileNotFoundException {
    Scanner sc = new Scanner(new File("d12.2/input.txt"));
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
    System.out.println(findPaths(nodes, "start", visited, true));
}

public static int findPaths(HashMap<String, Node> graph, String start, HashSet<String> visited, boolean canRepeat) {
    Node startNode = graph.get(start);
    int count = 0;
    if (!Character.isUpperCase(start.charAt(0)) && !start.equals("end"))
        visited.add(start);
    if (Objects.equals(startNode.name, "end"))
        return 1;
    for (String adyacent : startNode.adyacents) {
        if (Character.isUpperCase(adyacent.charAt(0))) {
            count += findPaths(graph, adyacent, new HashSet<>(visited), canRepeat);
        } else {
            if (visited.contains(adyacent) && canRepeat && !adyacent.equals("start")) {
                count += findPaths(graph, adyacent, new HashSet<>(visited), false);
            } else {
                if (!visited.contains(adyacent)) {
                    count += findPaths(graph, adyacent, new HashSet<>(visited), canRepeat);
                }
            }
        }
    }
    return count;
}
}
