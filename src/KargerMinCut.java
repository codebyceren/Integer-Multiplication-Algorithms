import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class KargerMinCut {

    private static final Random random = new Random();

    public static void main(String[] args) throws IOException {

        String filePath = "data/kargerMinCut.txt";

        Map<Integer, List<Integer>> graph = readGraph(filePath);

        int numberOfRuns = 1000;
        int minimumCut = Integer.MAX_VALUE;

        for (int i = 0; i < numberOfRuns; i++) {

            Map<Integer, List<Integer>> graphCopy = copyGraph(graph);

            int cut = kargerMinCut(graphCopy);

            if (cut < minimumCut) {
                minimumCut = cut;
                System.out.println("New minimum cut: " + minimumCut);
            }
        }

        System.out.println("Minimum cut found: " + minimumCut);
    }

    public static Map<Integer, List<Integer>> readGraph(String filePath)
            throws IOException {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] values = line.trim().split("\\s+");
                // \\s+ splits the line at one or more whitespace characters

                int vertex = Integer.parseInt(values[0]);
                // parseInt converts a String value into an int

                List<Integer> neighbors = new ArrayList<>();

                for (int i = 1; i < values.length; i++) {

                    int neighbor = Integer.parseInt(values[i]);

                    neighbors.add(neighbor);
                }

                graph.put(vertex, neighbors);
            }
        }

        return graph;
    }

    public static Map<Integer, List<Integer>> copyGraph(
            Map<Integer, List<Integer>> graph) {

        Map<Integer, List<Integer>> copy = new HashMap<>();

        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {

            copy.put(
                entry.getKey(),
                new ArrayList<>(entry.getValue())
            );
        }

        return copy;
    }

    public static int kargerMinCut(
            Map<Integer, List<Integer>> graph) {

        while (graph.size() > 2) {

            List<Integer> vertices = new ArrayList<>(graph.keySet());

            int firstVertex = vertices.get(
                    random.nextInt(vertices.size())
            );

            List<Integer> neighbors = graph.get(firstVertex);

            int secondVertex = neighbors.get(
                    random.nextInt(neighbors.size())
            );

            contract(graph, firstVertex, secondVertex);
        }

        int remainingVertex = graph.keySet().iterator().next();

        return graph.get(remainingVertex).size();
    }

    public static void contract(
            Map<Integer, List<Integer>> graph,
            int firstVertex,
            int secondVertex) {

        List<Integer> firstNeighbors = graph.get(firstVertex);
        List<Integer> secondNeighbors = graph.get(secondVertex);

        firstNeighbors.addAll(secondNeighbors);

        graph.remove(secondVertex);

        for (List<Integer> neighbors : graph.values()) {

            for (int i = 0; i < neighbors.size(); i++) {

                if (neighbors.get(i) == secondVertex) {
                    neighbors.set(i, firstVertex);
                }
            }
        }

        graph.get(firstVertex).removeIf(
                neighbor -> neighbor == firstVertex
        );
    }
}