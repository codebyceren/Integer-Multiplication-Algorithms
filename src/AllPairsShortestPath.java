import java.io.*;
import java.util.*;

public class AllPairsShortestPath {

    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws IOException {

        String[] files = {
            "data/allPairShortestPath/g1.txt",
            "data/allPairShortestPath/g2.txt",
            "data/allPairShortestPath/g3.txt"
        };

        Long overallBest = null;

        for (String filename : files) {
            System.out.println("=== Processing " + filename + " ===");

            long[][] dist = readGraph(filename);

            boolean hasNegativeCycle = floydWarshall(dist);

            if (hasNegativeCycle) {
                System.out.println(
                    filename + ": Has a NEGATIVE-COST CYCLE."
                );
                continue;
            }

            long shortestShortestPath = INF;

            for (int i = 0; i < dist.length; i++) {
                for (int j = 0; j < dist.length; j++) {
                    if (dist[i][j] < shortestShortestPath) {
                        shortestShortestPath = dist[i][j];
                    }
                }
            }

            System.out.println(
                filename + ": No negative cycle. "
                + "Shortest shortest path = "
                + shortestShortestPath
            );

            if (overallBest == null ||
                shortestShortestPath < overallBest) {
                overallBest = shortestShortestPath;
            }
        }

        System.out.println();
        System.out.println("=== RESULT ===");

        if (overallBest == null) {
            System.out.println("NULL");
        } else {
            System.out.println(
                "Shortest shortest path = " + overallBest
            );
        }
    }

    static long[][] readGraph(String filename) throws IOException {

        try (BufferedReader br = new BufferedReader(
                new FileReader(filename))) {

            StringTokenizer st =
                new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long[][] dist = new long[n][n];

            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }

            for (int e = 0; e < m; e++) {

                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                long weight = Long.parseLong(st.nextToken());

                if (weight < dist[u][v]) {
                    dist[u][v] = weight;
                }
            }

            return dist;
        }
    }

    static boolean floydWarshall(long[][] dist) {

        int n = dist.length;

        for (int k = 0; k < n; k++) {

            for (int i = 0; i < n; i++) {

                if (dist[i][k] >= INF) {
                    continue;
                }

                for (int j = 0; j < n; j++) {

                    if (dist[k][j] >= INF) {
                        continue;
                    }

                    long throughK =
                        dist[i][k] + dist[k][j];

                    if (throughK < dist[i][j]) {
                        dist[i][j] = throughK;
                    }
                }
            }
        }

        // A negative value on the diagonal
        // means there is a negative-cost cycle.
        for (int i = 0; i < n; i++) {
            if (dist[i][i] < 0) {
                return true;
            }
        }

        return false;
    }
}