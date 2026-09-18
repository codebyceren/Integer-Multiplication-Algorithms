import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

public class MedianMaintenance {
    
    public static void main (String[] args) {
        PriorityQueue<Integer> lowerHalf =
            new PriorityQueue<>(Collections.reverseOrder());

        PriorityQueue<Integer> upperHalf = new PriorityQueue<>();

       

        try (BufferedReader reader = new BufferedReader(
            new FileReader ("data/Median.txt")
        )) {

            long medianSum = 0;

            String line;
            while((line = reader.readLine()) != null) {

                int number = Integer.parseInt(line);
                

                if(lowerHalf.isEmpty()) {
                    lowerHalf.add(number);
                } else {
                    if(number <= lowerHalf.peek()) {

                        lowerHalf.add(number);
                    } else {
                        
                        upperHalf.add(number);
                    }

                }

                if(lowerHalf.size() > upperHalf.size() + 1) {
                    upperHalf.add(lowerHalf.poll());
                }  

                if(lowerHalf.size() < upperHalf.size()){
                    lowerHalf.add(upperHalf.poll());
                }
                
                int median = lowerHalf.peek();

                medianSum += median;
                

            }

            System.out.println("medianSum: " + medianSum % 10000);
            

        } catch (FileNotFoundException e) {

            System.out.println("File not found: " + e.getMessage());

        } catch (IOException e) {

            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
