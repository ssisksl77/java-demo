package parallel;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Prime2 {

    public static int isPrime(final int n) {
        final int m =  (int) Math.sqrt(n^2);
        // System.out.println(m);
        for (int i = 2; i <= m; i++) {
            if (0 == n % i) return 0;
        }
        return 1;
    }
    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        final int[] arr = new int[1000001];
        IntStream.range(2, 1000000).parallel().filter(i -> Prime2.isPrime(i) == 1).forEach(i -> arr[i] = 1);
        long time2 = System.currentTimeMillis();
        int s = 0;
        for (int i = 0; i < 1000000; i++) {
            s += arr[i];
        }
        System.out.println(time2 - time1);
        System.out.println("test1 : " + s);

        time1 = System.currentTimeMillis();
        IntStream.range(2, 1000000).parallel().filter(i -> Prime2.isPrime(i) == 1).forEach(i -> arr[i] = 1);
        s = Arrays.stream(arr).sum();
        time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        System.out.println("test2 : " + s);

        Arrays.fill(arr,0); // 다시세팅
        time1 = System.currentTimeMillis();
        IntStream.range(2, 1000000).parallel().filter(i -> Prime2.isPrime(i) == 1).forEach(i -> arr[i] = 1);
        s = Arrays.stream(arr).parallel().sum();
        time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        System.out.println("test3 : " + s);

        // 카운트만 원한다면
        time1 = System.currentTimeMillis();
        s = IntStream.range(2,1000000).parallel().map(Prime2::isPrime).sum();
        time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        System.out.println("test4 : " + s);
    }
}
