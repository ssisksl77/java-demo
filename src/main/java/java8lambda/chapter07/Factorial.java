package java8lambda.chapter07;

import static java8lambda.chapter07.TailCalls.call;
import static java8lambda.chapter07.TailCalls.done;

public class Factorial {
    public static int factorialRec(final int number) {
        if (number == 1) return number;
        else return number * factorialRec(number-1);
    }

    public static TailCall<Integer> factorialTailRec(final int factorial, final int number) {
        if (number == 1) return done(factorial);
        else return call(() -> factorialTailRec(factorial * number, number - 1));
    }

    public static int factorial(final int number) {
        return factorialTailRec(1, number).invoke();
    }

    public static void main(String[] args) {
        System.out.println(factorialRec(10));
        // System.out.println(factorialRec(20000));  // stackoverflow

        System.out.println(factorial(10));
    }
}
