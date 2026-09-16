import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class SCC {

    public static void main(String[] args) throws IOException {

        int numberOfVertices = 875714;

        BufferedReader reader = new BufferedReader(
            new FileReader("data/SCC.txt")
        );

        ArrayList<Integer>[] graph = new ArrayList[numberOfVertices + 1];
        ArrayList<Integer>[] reverseGraph = new ArrayList[numberOfVertices + 1];

        for (int i = 0; i <= numberOfVertices; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        String line;

        while ((line = reader.readLine()) != null) {

            String[] parts = line.split(" ");

            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);

            graph[u].add(v);
            reverseGraph[v].add(u);
        }

        reader.close();

        // First DFS: find finishing order on the reverse graph
        boolean[] visited = new boolean[numberOfVertices + 1];
        ArrayList<Integer> finishingOrder = new ArrayList<>();

        for (int i = 1; i <= numberOfVertices; i++) {

            if (!visited[i]) {

                Stack<Integer> stack = new Stack<>();
                stack.push(i);
                visited[i] = true;

                while (!stack.isEmpty()) {

                    int current = stack.pop();

                    if (current < 0) {
                        finishingOrder.add(-current);
                        continue;
                    }

                    stack.push(-current); // Mark current for finishing after processing its neighbors

                    for (int neighbor : reverseGraph[current]) {

                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            stack.push(neighbor);
                        }
                    }
                }
            }
        }

        // Second DFS: find SCCs on the original graph
        visited = new boolean[numberOfVertices + 1];

        ArrayList<Integer> sccSizes = new ArrayList<>();

        Collections.reverse(finishingOrder);

        for (int i : finishingOrder) {

            if (!visited[i]) {

                int size = 0;

                Stack<Integer> stack = new Stack<>();
                stack.push(i);
                visited[i] = true;

                while (!stack.isEmpty()) {

                    int current = stack.pop();
                    size++;

                    for (int neighbor : graph[current]) {

                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            stack.push(neighbor);
                        }
                    }
                }

                sccSizes.add(size);
            }
        }

        // Sort SCC sizes from largest to smallest
        sccSizes.sort(Collections.reverseOrder());

        // Print the five largest SCCs
        for (int i = 0; i < 5; i++) {

            if (i < sccSizes.size()) {
                System.out.print(sccSizes.get(i));
            } else {
                System.out.print(0);
            }

            if (i < 4) {
                System.out.print(",");
            }
        }

        System.out.println();
    }
}