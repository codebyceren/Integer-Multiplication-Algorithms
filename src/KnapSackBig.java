import java.io.BufferedReader;
import java.io. FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KnapSackBig {

    private static Map<Long, Long> memo = new HashMap<>();
    private static long[] values;
    private static int[] weights;
    private static int W;

    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new FileReader("data/knapsack_big.txt"))) {

          

            String[] firstLine = reader.readLine().trim().split("\\s+");
            W =Integer.parseInt(firstLine[0]);
            int n = Integer.parseInt(firstLine[1]);

            System.out.println("W = " + W);
            System.out.println("n = " + n);

            values = new long[n + 1];
            weights = new int[n + 1];

            String line;
            int i = 1;
            while((line = reader.readLine()) != null) {

                String[] parts = line.trim().split("\\s+");
                values[i] = Long.parseLong(parts[0]);
                weights[i] = Integer.parseInt(parts[1]);
               
                i++;                
            }
            long answer = solve(n, W);
            System.out.println("Optimal value: " + answer);


        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    private static long solve(int item, int cap) {

        if(item == 0) {
            return 0;
        }

        long key = (long) item * (W + 1) + cap;
        if(memo.containsKey(key)) {
            return memo.get(key);
        }

        long result;
        if(weights[item] > cap) {
            return solve(item - 1, cap);
        } else {
            long withoutItem = solve(item -1 , cap);
            long withItem = values[item] + solve(item - 1, cap - weights[item]);
            result = Math.max(withoutItem, withItem);
        }

        memo.put(key, result);
        return result;
    }


}