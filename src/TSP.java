import java.io.*;
import java.util.*;

public class TSP {

    public static void main(String[] args) throws IOException {

        String filename = "data/tsp.txt";

        double[][] coords = readCities(filename);
        int n = coords.length;

        System.out.println("Number of cities: " + n);

        double best = heldKarp(coords);

        System.out.println("Minimum TSP tour cost: " + best);
        System.out.println(
            "Floor of minimum TSP tour cost: "
            + (long) Math.floor(best)
        );
    }

    static double[][] readCities(String filename) throws IOException {

        try (BufferedReader br =
                new BufferedReader(new FileReader(filename))) {

            int n = Integer.parseInt(br.readLine().trim());

            double[][] coords = new double[n][2];

            for (int i = 0; i < n; i++) {

                StringTokenizer st =
                    new StringTokenizer(br.readLine());

                coords[i][0] =
                    Double.parseDouble(st.nextToken());

                coords[i][1] =
                    Double.parseDouble(st.nextToken());
            }

            return coords;
        }
    }

    static double heldKarp(double[][] coords) {

        int n = coords.length;

        if (n == 1) {
            return 0.0;
        }

        double[][] dist = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                double dx =
                    coords[i][0] - coords[j][0];

                double dy =
                    coords[i][1] - coords[j][1];

                dist[i][j] =
                    Math.sqrt(dx * dx + dy * dy);
            }
        }

        int m = n - 1;
        int numMasks = 1 << m;

        double[][] dp = new double[numMasks][n];

        for (double[] row : dp) {
            Arrays.fill(row, Double.POSITIVE_INFINITY);
        }

        // Start from city 0.
        dp[0][0] = 0.0;

        for (int mask = 0; mask < numMasks; mask++) {

            // Start a path from city 0.
            if (mask == 0) {

                for (int kBit = 0; kBit < m; kBit++) {

                    int k = kBit + 1;
                    int newMask = 1 << kBit;

                    double candidate =
                        dist[0][k];

                    if (candidate < dp[newMask][k]) {
                        dp[newMask][k] = candidate;
                    }
                }
            }

            // Extend existing paths.
            for (int jBit = 0; jBit < m; jBit++) {

                if ((mask & (1 << jBit)) == 0) {
                    continue;
                }

                int j = jBit + 1;

                if (dp[mask][j] ==
                    Double.POSITIVE_INFINITY) {
                    continue;
                }

                for (int kBit = 0; kBit < m; kBit++) {

                    if ((mask & (1 << kBit)) != 0) {
                        continue;
                    }

                    int k = kBit + 1;

                    int newMask =
                        mask | (1 << kBit);

                    double candidate =
                        dp[mask][j] + dist[j][k];

                    if (candidate < dp[newMask][k]) {
                        dp[newMask][k] = candidate;
                    }
                }
            }
        }

        // All cities have been visited.
        int fullMask = numMasks - 1;

        double best =
            Double.POSITIVE_INFINITY;

        for (int jBit = 0; jBit < m; jBit++) {

            int j = jBit + 1;

            double candidate =
                dp[fullMask][j] + dist[j][0];

            if (candidate < best) {
                best = candidate;
            }
        }

        return best;
    }
}