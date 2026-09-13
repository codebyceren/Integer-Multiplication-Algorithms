public class SecondLargest {

    public static void main(String[] args) {

        int[] array = {8, 3, 12, 5, 10};

        Result result = findSecondLargest(array);

        System.out.println("Largest: " + result.largest);
        System.out.println("Second largest: " + result.secondLargest);

    }
    private static Result findSecondLargest(int[] array) {

        if(array.length == 1) {
            return new Result(array[0], Integer.MIN_VALUE);
        }

        if(array.length == 2) {
            if(array[0] > array[1]) {
                return new Result(array[0], array[1]);

            }else {
                return new Result(array[1], array[0]);
            }
        }

        int middle = array.length / 2;

        int[] left = new int[middle];
        int[] right = new int[array.length - middle];

        System.arraycopy(array, 0, left, 0, middle);
        // array → source, 0 → start, left → destination, 0 → start, middle → length

        System.arraycopy(array, middle, right, 0, array.length - middle);
        // array → source, middle → start, right → destination, 0 → start, remaining length → length

        Result leftResult = findSecondLargest(left);
        Result rightResult = findSecondLargest(right);

        if(leftResult.largest > rightResult.largest) {

            int secondLargest = Math.max(leftResult.secondLargest, rightResult.largest);
            return new Result(leftResult.largest, secondLargest); 

        } else {

            int secondLargest = Math.max(rightResult.secondLargest, leftResult.largest);
            return new Result(rightResult.largest, secondLargest);
        }

    }
    private static class Result {
        int largest;
        int secondLargest;

        Result(int largest, int secondLargest) {
            this.largest = largest;
            this.secondLargest = secondLargest;
        }
    }

   
}