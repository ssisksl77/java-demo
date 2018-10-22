package parallel;

import java.util.concurrent.atomic.AtomicInteger;

//78498
public class Prime3 implements Runnable {
    private final int n;
    private static int primes = 0;
    // private static volatile int primes = 0;
    // private static AtomicInteger primes = new AtomicInteger(0);

    public Prime3(int n) {
        this.n = n;
    }

    int isPrime() {
        final int m =  (int) Math.sqrt(n^2);
        // System.out.println(m);
        for (int i = 2; i <= m; i++) {
            if (0 == n % i) return 0;
        }
        return 1;
    }

    public int getN() {
        return n;
    }

    public static int getPrimes() {
        return primes;
    }

    @Override
    public void run() {
        primes += isPrime();
    }

    public static void main(String[] args) throws InterruptedException {
        //int[] arr = new int [1000001];
        // System.out.println(arr.length);
        for (int i = 0; i < 1000000; i++) {
            new Thread(new Prime3(i)).start();
        }

        Thread.sleep(10);
        System.out.println(Prime3.getPrimes());
    }
}
