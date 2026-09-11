import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountInversions {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
            new FileReader("data/IntegerArray.txt")
        );

        int[] array = new int[100000];
        int count = 0;
        String line;

        while ((line = reader.readLine()) != null) {
            array[count] = Integer.parseInt(line);
            count++;
        }

        reader.close();

        long inversionCount = countInversions(array);
        System.out.println("Number of inversions: " + inversionCount);

        System.out.println("Number of integers: " + count);
        System.out.println("First number: " + array[0]);
        System.out.println("Second number: " + array[1]);
        System.out.println("Last number: " + array[99999]);
    }

    private static long countInversions(int[] array) {

        if(array.length <= 1) {
            return 0;
        }
        int middle = array.length / 2;

        int[] left = new int[middle];
        int[] right = new int[array.length - middle];

        System.arraycopy(array, 0, left, 0, middle);
        // array → source, 0 → start, left → destination, 0 → start, middle → length

        System.arraycopy(array, middle, right, 0, array.length - middle);
        // array → source, middle → start, right → destination, 0 → start, remaining length → length

        long leftInversions = countInversions(left);
        long rightInversions = countInversions(right);

        long splitInversions = mergeAndCount(array, left, right);
        return leftInversions + rightInversions + splitInversions;
    }

    private static long mergeAndCount(int[] array, int[] left, int[] right) {

        int i = 0;
        int j = 0;
        long inversions = 0;

        int[] merged = new int[left.length + right.length];
        int k = 0;

        while(i < left.length && j < right.length) {
            if(left[i] <= right[j]) {
                merged[k] = left[i];
                i++;
            } else{
                merged[k] = right[j];
                inversions += left.length - i;
                j++;
            }
            k++;
        }

        while (i < left.length) {
            merged[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            merged[k] = right[j];
            j++;
            k++;
        }

        for (int index = 0; index < merged.length; index++) {
            array[index] = merged[index];
        }
        return inversions;
    }
}