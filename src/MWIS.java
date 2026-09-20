import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MWIS {

    public static void main(String[] args) {

        List<Long> weights = new ArrayList<>();
        // index 0 kullanılmayacak, 1'den başlayacağız (vertex 1, vertex 2, ...)
        weights.add(0L);

        try (BufferedReader reader = new BufferedReader(
                new FileReader("data/mwis.txt"))) {

            String firstLine = reader.readLine().trim();
            int numVertices = Integer.parseInt(firstLine);

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                long w = Long.parseLong(line);
                weights.add(w);
            }

            int n = numVertices;

            // A[i] = maksimum ağırlık, sadece ilk i vertex kullanılarak
            long[] A = new long[n + 1];
            A[0] = 0;
            A[1] = weights.get(1);

            for (int i = 2; i <= n; i++) {
                A[i] = Math.max(A[i - 1], A[i - 2] + weights.get(i));
            }

            // Reconstruction: hangi vertex'ler seçildi?
            Set<Integer> included = new HashSet<>();
            int i = n;
            while (i >= 1) {
                if (i == 1) {
                    // sadece vertex 1 kaldı, A[1] zaten weights.get(1) demekti
                    included.add(1);
                    i = i - 1;
                } else {
                    if (A[i - 1] >= A[i - 2] + weights.get(i)) {
                        // vertex i dahil değil
                        i = i - 1;
                    } else {
                        
                        included.add(i);
                        i = i - 2;
                    }
                }
            }

            
            int[] queryVertices = {1, 2, 3, 4, 17, 117, 517, 997};
            StringBuilder result = new StringBuilder();
            for (int v : queryVertices) {
                if (included.contains(v)) {
                    result.append("1");
                } else {
                    result.append("0");
                }
            }

            System.out.println("Maximum weight: " + A[n]);
            System.out.println("Result bitstring: " + result.toString());

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}