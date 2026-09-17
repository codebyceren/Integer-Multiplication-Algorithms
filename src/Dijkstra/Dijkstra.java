package Dijkstra;

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
    
    private Map<Integer, List<Edge>> graph;

    public Dijkstra() {
        graph = new HashMap<>();

        for (int i = 1; i <= 200; i++) {
            graph.put(i, new ArrayList<>());
        }
    }

    public void readGraph() {

        try (BufferedReader reader = new BufferedReader(
            new FileReader("data/dijkstraData.txt"))) {

                String line;
                while((line = reader.readLine()) != null) {

                String[] parts = line.split("\\s+");

                int u = Integer.parseInt(parts[0]);

                for(int i = 1; i < parts.length; i++) {

                    String edgeData = parts[i];
                    String[] edgeParts = edgeData.split(",");

                    int v = Integer.parseInt(edgeParts[0]);
                    int weight = Integer.parseInt(edgeParts[1]);

                    Edge edge = new Edge(v, weight);
                    graph.get(u).add(edge);

                    Edge reverseEdge = new Edge(u,weight);
                    graph.get(v).add(reverseEdge);
                    
                }


        }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

       
    }

    public int[] dijkstra (int source) {

        int[] distances = new int[200];

        for(int i = 0; i < distances.length; i++) {
            distances[i] =  1_000_000;

        }

        distances[source - 1] = 0;

        boolean[] visited = new boolean[200];

        for (int i = 0; i < 200; i++) {
            int minDistance = 1_000_000;
            int currentVertex = -1;

            for(int j = 0; j < distances.length; j++) {

                if(!visited[j] && distances[j] < minDistance ) {

                    minDistance = distances[j];
                    currentVertex = j;
                }

            }

             visited[currentVertex] = true;

            List<Edge> edges = graph.get(currentVertex + 1);

            for (Edge edge : edges) {

                int neighbor = edge.getNeighbor();
                int weight = edge.getWeight();

                if (!visited[neighbor - 1]
                    && distances[currentVertex] + weight < distances[neighbor - 1]) {

                    distances[neighbor - 1] = distances[currentVertex] + weight;
                }
            }
            

        }
        return distances;

    }

    public static void main(String[] args) {

        Dijkstra dijkstra = new Dijkstra();

        dijkstra.readGraph();

        int[] distances = dijkstra.dijkstra(1);

        int[] targets = {7, 37, 59, 82, 99, 115, 133, 165, 188, 197};

        for (int i = 0; i < targets.length; i++) {

            if (i > 0) {
                System.out.print(",");
            }

            System.out.print(distances[targets[i] - 1]);
        }

    }   
}
