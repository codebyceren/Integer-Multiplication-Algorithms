public class FixedPoint {

    public static void main(String[] args) {

        int[] array = {-3, -1, 1, 3, 5};

        int fixedPoint = findFixedPoint(array, 0, array.length - 1);
        System.out.println("Fixed point: " + fixedPoint);
    }

    private static int findFixedPoint(int[] array, int left, int right) {

        if(left > right) {
            return -1;
        }

        int middle = (left + right) / 2;

        if(array[middle] == middle) {

            return middle;
        }

        if(array[middle] > middle) {
            return findFixedPoint(array, left, middle - 1);
        }

        if(array[middle] < middle) {
            return findFixedPoint(array, middle + 1, right);
        }
        return -1;

    }

}