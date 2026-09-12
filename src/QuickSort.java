import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickSort {

    public static void main(String[] args) throws FileNotFoundException {

        int[] originalNumbers = readInputFile();

        // Question 1: First element as pivot
        int[] numbersForQuestion1 = originalNumbers.clone();
        long question1 = quickSort(
                numbersForQuestion1,
                0,
                numbersForQuestion1.length - 1,
                1
        );

        // Question 2: Final element as pivot
        int[] numbersForQuestion2 = originalNumbers.clone();
        long question2 = quickSort(
                numbersForQuestion2,
                0,
                numbersForQuestion2.length - 1,
                2
        );

        // Question 3: Median-of-three pivot
        int[] numbersForQuestion3 = originalNumbers.clone();
        long question3 = quickSort(
                numbersForQuestion3,
                0,
                numbersForQuestion3.length - 1,
                3
        );

        System.out.println("Question 1: " + question1);
        System.out.println("Question 2: " + question2);
        System.out.println("Question 3: " + question3);
    }

    private static int[] readInputFile() throws FileNotFoundException {

        File file = new File("data/QuickSort.txt");
        Scanner scanner = new Scanner(file);

        int[] numbers = new int[10000];
        int index = 0;

        while (scanner.hasNextInt()) {
            numbers[index] = scanner.nextInt();
            index++;
        }

        scanner.close();

        return numbers;
    }

    private static long quickSort(
            int[] numbers,
            int left,
            int right,
            int pivotRule) {

        if (left >= right) {
            return 0;
        }

        // A subarray of length m uses m - 1 comparisons.
        long comparisons = right - left;

        int pivotIndex;

        if (pivotRule == 1) {

            // Question 1: First element
            pivotIndex = left;

        } else if (pivotRule == 2) {

            // Question 2: Final element
            pivotIndex = right;

        } else {

            // Question 3: Median-of-three
            pivotIndex = medianOfThree(numbers, left, right);
        }

        // Move the chosen pivot to the first position.
        swap(numbers, left, pivotIndex);

        int finalPivotIndex = partition(numbers, left, right);

        comparisons += quickSort(
                numbers,
                left,
                finalPivotIndex - 1,
                pivotRule
        );

        comparisons += quickSort(
                numbers,
                finalPivotIndex + 1,
                right,
                pivotRule
        );

        return comparisons;
    }

    private static int partition(
            int[] numbers,
            int left,
            int right) {

        int pivot = numbers[left];

        int i = left + 1;

        for (int j = left + 1; j <= right; j++) {

            if (numbers[j] < pivot) {
                swap(numbers, i, j);
                i++;
            }
        }

        // Put the pivot into its final position.
        swap(numbers, left, i - 1);

        return i - 1;
    }

    private static int medianOfThree(
            int[] numbers,
            int left,
            int right) {

        // For odd length: the exact middle element.
        // For even length: the left of the two middle elements.
        int middle = left + (right - left) / 2;

        int first = numbers[left];
        int middleValue = numbers[middle];
        int last = numbers[right];

        // Middle value is the median.
        if ((first <= middleValue && middleValue <= last)
                || (last <= middleValue && middleValue <= first)) {

            return middle;
        }

        // First value is the median.
        if ((middleValue <= first && first <= last)
                || (last <= first && first <= middleValue)) {

            return left;
        }

        // Otherwise, the last value is the median.
        return right;
    }

    private static void swap(
            int[] numbers,
            int first,
            int second) {

        int temp = numbers[first];
        numbers[first] = numbers[second];
        numbers[second] = temp;
    }
}