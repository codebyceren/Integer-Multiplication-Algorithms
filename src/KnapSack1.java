import java.io.BufferedReader;
import java.io. FileReader;
import java.io.IOException;

public class KnapSack1 {

    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new FileReader("data/knapsack1.txt"))) {

            String[] firstLine = reader.readLine().trim().split("\\s+");
            int W =Integer.parseInt(firstLine[0]);
            int n = Integer.parseInt(firstLine[1]);

            System.out.println("W = " + W);
            System.out.println("n = " + n);

            long[] values = new long[n + 1];
            int[] weights = new int[n + 1];

            String line;
            int i = 1;
            while((line = reader.readLine()) != null) {

                String[] parts = line.trim().split("\\s+");
                values[i] = Long.parseLong(parts[0]);
                weights[i] = Integer.parseInt(parts[1]);
               
                i++;                
            }

            long[][] A = new long[n + 1][W + 1];

            for(int item = 1; item <= n; item++) {
                for(int cap = 0; cap <= W; cap++) {

                    if(weights[item] > cap) {
                            A[item][cap] = A[item - 1][cap];
                    } else {
                        long withoutItem = A[item - 1][cap];
                        long withItem = values[item] + A[item - 1][cap - weights[item]];
                        A[item][cap] = Math.max(withoutItem, withItem);
                    }
                }
            }
            System.out.println("Optimal value: " + A[n][W]);

        

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


}