import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ClusteringBig {

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

    private static void flipBit(char[] chars, int index) {
        chars[index] = (chars[index] == '0') ? '1' : '0';
    }

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(
            new FileReader("data/clustering_big.txt")
        )) {

            String[] firstLine = reader.readLine().trim().split("\\s+");
            int numNodes = Integer.parseInt(firstLine[0]);
            int numBits = Integer.parseInt(firstLine[1]);

            List<String> labels = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] bits = line.trim().split("\\s+");
                StringBuilder sb = new StringBuilder();
                for (String bit : bits) {
                    sb.append(bit);
                }
                labels.add(sb.toString());
            }

            // Set up Union-Find arrays
            int[] parent = new int[numNodes + 1];
            int[] size = new int[numNodes + 1];
            for (int i = 1; i <= numNodes; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            // Map from label -> list of node ids sharing that label
            Map<String, List<Integer>> labelToNodes = new HashMap<>();
            for (int i = 0; i < labels.size(); i++) {
                int nodeId = i + 1;
                labelToNodes.computeIfAbsent(labels.get(i), key -> new ArrayList<>()).add(nodeId);
            }

            // Distance 0: union all nodes sharing the exact same label
            for (List<Integer> sameLabelNodes : labelToNodes.values()) {
                for (int i = 1; i < sameLabelNodes.size(); i++) {
                    union(parent, size, sameLabelNodes.get(0), sameLabelNodes.get(i));
                }
            }

            // Distance 1 and Distance 2: generate candidate neighbors by flipping bits
            for (int i = 0; i < labels.size(); i++) {
                int nodeId = i + 1;
                String label = labels.get(i);
                char[] chars = label.toCharArray();

                for (int b1 = 0; b1 < numBits; b1++) {
                    // Distance 1: flip a single bit
                    flipBit(chars, b1);
                    String neighbor1 = new String(chars);
                    if (labelToNodes.containsKey(neighbor1)) {
                        int otherNode = labelToNodes.get(neighbor1).get(0);
                        union(parent, size, nodeId, otherNode);
                    }
                    flipBit(chars, b1); // revert

                    // Distance 2: flip a second bit as well
                    for (int b2 = b1 + 1; b2 < numBits; b2++) {
                        flipBit(chars, b1);
                        flipBit(chars, b2);
                        String neighbor2 = new String(chars);
                        if (labelToNodes.containsKey(neighbor2)) {
                            int otherNode = labelToNodes.get(neighbor2).get(0);
                            union(parent, size, nodeId, otherNode);
                        }
                        flipBit(chars, b1);
                        flipBit(chars, b2); // revert
                    }
                }
            }

            // Count remaining distinct clusters (roots)
            int numClusters = 0;
            for (int i = 1; i <= numNodes; i++) {
                if (find(parent, i) == i) {
                    numClusters++;
                }
            }

            System.out.println("Max k with spacing >= 3: " + numClusters);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}