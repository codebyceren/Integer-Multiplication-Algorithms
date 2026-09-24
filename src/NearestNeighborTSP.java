import java.io.*;
import java.util.*;

public class NearestNeighborTSP {

    static class City {
        int index;
        double x;
        double y;

        City(int index, double x, double y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        String filename = "data/nn.txt";

        City[] cities = readCities(filename);
        int n = cities.length;

        boolean[] visited = new boolean[n];

        int current = 0;
        visited[current] = true;

        double tourCost = 0.0;

        for (int step = 1; step < n; step++) {

            int nearestCity = -1;
            double nearestDistanceSquared =
                    Double.POSITIVE_INFINITY;

            for (int city = 0; city < n; city++) {

                if (visited[city]) {
                    continue;
                }

                double dx =
                        cities[current].x - cities[city].x;

                double dy =
                        cities[current].y - cities[city].y;

                double distanceSquared =
                        dx * dx + dy * dy;

                if (distanceSquared < nearestDistanceSquared) {

                    nearestDistanceSquared = distanceSquared;
                    nearestCity = city;

                } else if (distanceSquared == nearestDistanceSquared) {

                    // In case of a tie, choose the city
                    // with the lowest index.
                    if (cities[city].index <
                            cities[nearestCity].index) {

                        nearestCity = city;
                    }
                }
            }

            tourCost += Math.sqrt(nearestDistanceSquared);

            current = nearestCity;
            visited[current] = true;
        }

        // Return to the first city.
        double dx =
                cities[current].x - cities[0].x;

        double dy =
                cities[current].y - cities[0].y;

        tourCost += Math.sqrt(dx * dx + dy * dy);

        System.out.println("Number of cities: " + n);
        System.out.println(
                "Nearest neighbor tour cost: " + tourCost
        );
        System.out.println(
                "Floor of tour cost: " +
                (long) Math.floor(tourCost)
        );
    }

    static City[] readCities(String filename) throws IOException {

        try (BufferedReader br =
                     new BufferedReader(new FileReader(filename))) {

            int n = Integer.parseInt(br.readLine().trim());

            City[] cities = new City[n];

            for (int i = 0; i < n; i++) {

                StringTokenizer st =
                        new StringTokenizer(br.readLine());

                int index =
                        Integer.parseInt(st.nextToken());

                double x =
                        Double.parseDouble(st.nextToken());

                double y =
                        Double.parseDouble(st.nextToken());

                cities[i] = new City(index, x, y);
            }

            return cities;
        }
    }
}