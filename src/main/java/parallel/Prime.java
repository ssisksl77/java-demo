package parallel;

import java.util.concurrent.*;

/**
 * PROCESS : 8
 * primes: 78498
 */
public class Prime implements Callable<Integer>{
    private final int n;

    int isPrime() {
        final int m =  (int) Math.sqrt(n^2);
        // System.out.println(m);
        for (int i = 2; i <= m; i++) {
            if (0 == n % i) return 0;
        }
        return 1;
    }

    @Override
    public Integer call() throws Exception {
        return isPrime();
    }

    public Prime(int n) {
        this.n = n;
    }

    static int isPrimeStatic(int n) {
        final int m =  (int) Math.sqrt(n^2);
        // System.out.println(m);
        for (int i = 2; i <= m; i++) {
            if (0 == n % i) return 0;
        }
        return 1;
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        int[] arr = new int [1000001];

        System.out.printf("PROCESS : %d\n", Runtime.getRuntime().availableProcessors());
        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int primes = 0;
        long t1 = System.currentTimeMillis();
        for (int i = 2; i < 1000000; i++) {
            Future<Integer> future = es.submit(new Prime(i));

            //primes += future.get();
            arr[i] = future.get();
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            arr[i] = isPrimeStatic(i);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        for (int i = 0; i < arr.length; i++) {
            primes += arr[i];
        }


        System.out.println("primes: " + primes);
        es.shutdown();
        return;
    }


}
