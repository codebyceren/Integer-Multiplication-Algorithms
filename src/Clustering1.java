import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Clustering1 {

    public static class Edge {
        int node1;
        int node2;
        int cost;

        Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
    }

    private static int find(int[] parent, int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    private static void union(int[] parent, int[] size, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rootX == rootY) {
            return;
        }

        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(
            new FileReader("data/Clustering1.txt")
        )) {

            int numNodes = Integer.parseInt(reader.readLine().trim());

            List<Edge> edges = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int node1 = Integer.parseInt(parts[0]);
                int node2 = Integer.parseInt(parts[1]);
                int cost = Integer.parseInt(parts[2]);
                edges.add(new Edge(node1, node2, cost));
            }

            edges.sort((e1, e2) -> e1.cost - e2.cost);

            int[] parent = new int[numNodes + 1];
            int[] size = new int[numNodes + 1];

            for (int i = 1; i <= numNodes; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            int k = 4;
            int numClusters = numNodes;
            int maxSpacing = -1;

            for (Edge edge : edges) {

                int rootA = find(parent, edge.node1);
                int rootB = find(parent, edge.node2);

                if (rootA == rootB) {
                    continue;
                }

                if (numClusters > k) {
                    union(parent, size, edge.node1, edge.node2);
                    numClusters--;
                } else {
                    maxSpacing = edge.cost;
                    break;
                }
            }

            System.out.println("Number of nodes: " + numNodes);
            System.out.println("Number of edges: " + edges.size());
            System.out.println("Max spacing for k=4: " + maxSpacing);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}