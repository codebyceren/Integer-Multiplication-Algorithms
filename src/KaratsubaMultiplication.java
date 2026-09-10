package src;
import java.math.BigInteger;
public class KaratsubaMultiplication {
    public static void main(String[] args) {

        BigInteger x = new BigInteger(
            "3141592653589793238462643383279502884197169399375105820974944592"
        );

        BigInteger y = new BigInteger(
            "2718281828459045235360287471352662497757247093699959574966967627"
        );

        BigInteger result = karatsuba(x, y);
        System.out.println("Result: " + result);
    }

    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
       if (x.toString().length() == 1 || y.toString().length() == 1) {
            return x.multiply(y);
       }

       int n = x.toString().length();
       int m = n / 2;

       BigInteger tenPowerM = BigInteger.TEN.pow(m);

       BigInteger B = x.remainder(tenPowerM);
       BigInteger A = x.divide(tenPowerM);

       BigInteger D = y.remainder(tenPowerM);
       BigInteger C = y.divide(tenPowerM);

       BigInteger firstPartProduct = karatsuba(A, C);
       BigInteger secondPartProduct = karatsuba(B, D);

       BigInteger sumX = A.add(B);
       BigInteger sumY = C.add(D);

       BigInteger combinedProduct = karatsuba(sumX, sumY);

       BigInteger middleTerm = combinedProduct
        .subtract(secondPartProduct)
        .subtract(firstPartProduct);

       BigInteger tenPowerTwoM = BigInteger.TEN.pow(2 * m);
       BigInteger firstPartShifted = firstPartProduct.multiply(tenPowerTwoM);
       BigInteger middleTermShifted = middleTerm.multiply(tenPowerM);

       BigInteger result = firstPartShifted
        .add(middleTermShifted)
        .add(secondPartProduct);

       return result;

       

    }
    
}
