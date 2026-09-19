import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;


public class TwoSumTargetRange {

    public static void main(String[] args) {

        Set<Long> numbers = new HashSet<>(); 

        long startTime = System.currentTimeMillis();
        
        try (BufferedReader reader = new BufferedReader(
            new FileReader ("data/algo1-programming_prob-2sum.txt")
        )) {

            String line;
            while((line = reader.readLine()) != null) {

                long number = Long.parseLong(line);
                numbers.add(number);

            }

            int count = 0;

            for(long t = -10000 ; t <= 10000 ; t++) {

                if (t % 1000 == 0) {
                    System.out.println("t = " + t + " işleniyor...");
                }

                for (long x : numbers) {
                    long y = t - x;

                    if (numbers.contains(y) && y != x) {
                        count++;
                        break;
                    }
                }

            }
            System.out.println("Count:" + count);

            long endTime = System.currentTimeMillis();
            System.out.println("Toplam süre: " + (endTime - startTime) / 1000.0 + " saniye");
 
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }  
    }
}