public class LocalMinimum {

    public static void main(String[] args) {

        int[] array = {10, 7, 5, 8, 12};

        int localMinimum = findLocalMinimum(array, 0, array.length - 1);
        System.out.println("Local Minimum: " + localMinimum);
    }

    private static int findLocalMinimum(int[] array, int left, int right) {

        if(left == right) {
            return array[left];
        }

        int middle = (left + right) / 2;

        if(array[middle] < array[middle - 1] && array[middle] < array[middle + 1]) {
            return array[middle];
        }

        if(array[middle] > array[middle - 1]) {

            return findLocalMinimum(array, left, middle - 1);
        }

        if(array[middle] > array[middle + 1]) {

            return findLocalMinimum(array, middle + 1, right);
        }
        return -1;

    }
    
}
