public class UnimodalArray {

    public static void main(String[] args) {

        int[] array = {1, 3, 5, 8, 12, 10, 7, 4};

        int maximum = findMaximum(array, 0, array.length - 1);

        System.out.println("Maximum: " + maximum);

    }
    
    private static int findMaximum(int[] array, int left, int right) {

        if(left == right) {
            return array[left];
        }

        int middle = (left + right) / 2;

        if (array[middle] > array[middle - 1] &&
            array[middle] > array[middle + 1]) {

            return array[middle];
        }

        if (array[middle] < array[middle + 1]) {
            return findMaximum(array, middle + 1, right);
            // array → original array, middle + 1 → start index, right → end index
        }

        return findMaximum(array, left, middle - 1);
        // array → original array, left → start index, middle - 1 → end index
    }
    
}
