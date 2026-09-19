import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PrimMST {

    public static void main(String[] args) {

        int n = 0;
        int m = 0;
        ArrayList<int[]>[] graph = null;

        try (BufferedReader reader = new BufferedReader(
                new FileReader("data/edges.txt"))) {

            // Read first line: number of nodes and number of edges
            String firstLine = reader.readLine();
            String[] firstParts = firstLine.split("\\s+");
            n = Integer.parseInt(firstParts[0]);
            m = Integer.parseInt(firstParts[1]);

            // Create the array that will hold the graph
            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            // Read edges and add them to the graph
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\s+");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                int cost = Integer.parseInt(parts[2]);

                graph[u].add(new int[]{v, cost});
                graph[v].add(new int[]{u, cost});
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("Number of nodes: " + n);
        System.out.println("Number of edges: " + m);

        // visited array: tracks which nodes are already in the MST
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        long totalCost = 0;
        int edgesAdded = 0;

        while (edgesAdded < n - 1) {

            long minCost = Long.MAX_VALUE;
            int bestNode = -1;

            for (int v = 1; v <= n; v++) {
                if (visited[v]) {
                    for (int[] edge : graph[v]) {
                        int neighbor = edge[0];
                        int cost = edge[1];

                        if (!visited[neighbor] && cost < minCost) {
                            minCost = cost;
                            bestNode = neighbor;
                        }
                    }
                }
            }

            visited[bestNode] = true;
            totalCost += minCost;
            edgesAdded++;
        }

        System.out.println("MST total cost: " + totalCost);
    }
}