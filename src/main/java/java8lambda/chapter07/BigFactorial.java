package java8lambda.chapter07;

import java.math.BigInteger;

import static java8lambda.chapter07.TailCalls.call;
import static java8lambda.chapter07.TailCalls.done;

public class BigFactorial {
    public static BigInteger decrement(final BigInteger number) {
        return number.subtract(BigInteger.ONE);
    }

    public static BigInteger multiply(final BigInteger first, final BigInteger second) {
        return first.multiply(second);
    }

    private static TailCall<BigInteger> factorialTailRec(final BigInteger factorial, final BigInteger number) {
        if(number.equals(BigInteger.ONE)) return done(factorial);
        else return call(() -> factorialTailRec(multiply(factorial, number), decrement(number)));
    }

    public static BigInteger factorial(final BigInteger number) {
        return factorialTailRec(BigInteger.ONE, number).invoke();
    }

    /** MAIN **/
    final static BigInteger ONE = BigInteger.ONE;
    final static BigInteger FIVE = new BigInteger("5");
    final static BigInteger TWENTYK = new BigInteger("20000");

    public static void main(String[] args) {
        System.out.println(factorial(FIVE));
        System.out.println(String.format("%.10s...", factorial(TWENTYK)));
    }

}
