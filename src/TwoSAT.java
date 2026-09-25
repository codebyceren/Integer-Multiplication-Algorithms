import java.io.*;
import java.util.*;

public class TwoSAT {

    static int n;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] reverseGraph;

    static boolean[] visited;
    static int[] finishingOrder;
    static int orderIndex;

    static int[] component;
    static int componentId;

    public static void main(String[] args) throws IOException {

        StringBuilder answer = new StringBuilder();

        for (int i = 1; i <= 6; i++) {

            String filePath = "data/TwoSAT/2sat" + i + ".txt";

            boolean satisfiable = solve(filePath);

            if (satisfiable) {
                answer.append("1");
                System.out.println("2sat" + i + ": SATISFIABLE");
            } else {
                answer.append("0");
                System.out.println("2sat" + i + ": UNSATISFIABLE");
            }
        }

        System.out.println();
        System.out.println("Answer: " + answer);
    }

    static boolean solve(String filePath) throws IOException {

        BufferedReader reader = new BufferedReader(
                new FileReader(filePath)
        );

        n = Integer.parseInt(reader.readLine().trim());

        int numberOfNodes = 2 * n;

        graph = new ArrayList[numberOfNodes];
        reverseGraph = new ArrayList[numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        String line;

        while ((line = reader.readLine()) != null) {

            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");

            int first = Integer.parseInt(parts[0]);
            int second = Integer.parseInt(parts[1]);

            int a = literalToNode(first);
            int b = literalToNode(second);

            int notA = literalToNode(-first);
            int notB = literalToNode(-second);

            // (a OR b)
            // NOT a -> b
            // NOT b -> a
            addEdge(notA, b);
            addEdge(notB, a);
        }

        reader.close();

        calculateSCCs();

        for (int variable = 1; variable <= n; variable++) {

            int positive = literalToNode(variable);
            int negative = literalToNode(-variable);

            if (component[positive] == component[negative]) {
                return false;
            }
        }

        return true;
    }

    static int literalToNode(int literal) {

        int variable = Math.abs(literal);

        if (literal > 0) {
            return variable - 1;
        } else {
            return n + variable - 1;
        }
    }

    static void addEdge(int from, int to) {

        graph[from].add(to);
        reverseGraph[to].add(from);
    }

    static void calculateSCCs() {

        visited = new boolean[2 * n];
        finishingOrder = new int[2 * n];
        orderIndex = 0;

        for (int node = 0; node < 2 * n; node++) {

            if (!visited[node]) {
                dfsFirst(node);
            }
        }

        visited = new boolean[2 * n];
        component = new int[2 * n];
        componentId = 0;

        for (int i = 2 * n - 1; i >= 0; i--) {

            int node = finishingOrder[i];

            if (!visited[node]) {
                dfsSecond(node);
                componentId++;
            }
        }
    }

    static void dfsFirst(int node) {

        visited[node] = true;

        for (int next : reverseGraph[node]) {

            if (!visited[next]) {
                dfsFirst(next);
            }
        }

        finishingOrder[orderIndex++] = node;
    }

    static void dfsSecond(int node) {

        visited[node] = true;
        component[node] = componentId;

        for (int next : graph[node]) {

            if (!visited[next]) {
                dfsSecond(next);
            }
        }
    }
}