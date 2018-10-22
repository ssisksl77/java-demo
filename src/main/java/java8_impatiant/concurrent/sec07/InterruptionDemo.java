package java8_impatiant.concurrent.sec07;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 스레드 인터럽션
 *
 * 주어진 쿼리(질의:query)에서 첫 번째 결과로 만족한다고 해보자. 그러니까 답이 맨처음에 나오면 나머지는 필요없다.
 * 답을 검색하는 일을 여러 태스크로 분산했고, 답을 얻은 태스크가 있다면 나머지는 모두 취소하려 한다.
 *
 * 각 스레드에는 인터럽트 상태(interrupted status)가 포함되는데, 이는 누군가가 해당 스레드를 '인터럽트'하려는 상태를 나타낸다.
 * 인터럽션(interruption)이 무엇을 의미하는지 정확한 정의는 없지만, 개발자 대부분의 인터럽션은 취소 요청을 나타내는 데 이용한다.
 *
 * Runnable task = () -> {
 *     while(...) {
 *         if (Thread.currentThread().isInterrupted()) return;
 *         ...
 *     }
 * }
 *
 * 스레드가 대기, 잠든 도중에 인터럽트가 되면 즉시 재활성화된다. 하지만 이 경우에는 인터럽트 상태가 설정되지 않는다. 그 대신 InterruptedException을
 * 던진다. 이것은 예외이므로 Runnable의 run 메소드 안에서 반.드.시. 잡아야 한다. 보통은 이 예외가 일어나면 run메소드를 끝내는 식으로 처리한다.
 *
 * Runnable task = () -> {
 *     try {
 *         while(...) {
 *             ...
 *             Thread.sleep(1000);
 *         }
 *     } catch (InterruptedException ex) {
 *         // 아무 일도 하지 않는다.
 *     }
 * }
 *
 * 이 방식으로 InterruptedException을 잡아낼 때는 인터럽트 상태를 검사할 필요가 없다. (종료하지 않는 거니까)
 *
 * &&& InterruptedException이 성가시게 보일 수 도 있지만, sleep 같은 메서드를 호출 할 때는 잡아서 숨기지 말아야 한다. 다른 처리를 할 수 없으면
 * 인터럽트 상태라도 설정해줘야 한다.
 * try {
 *     Thread.sleep(1000);
 * } catch (InterruptedException ex) {
 *     Thread.currentThraed().interrupt();
 * }
 * 더 좋은 방법은 해당 예외를 관련 핸들러로 전파하는 것이다.
 *
 * public void mySubTask() throws InterruptedException {
 *     ...
 *     Thread.sleep(1000);
 *     ...
 * }
 */
public class InterruptionDemo {
    public static BigInteger big(long value) { return BigInteger.valueOf(value); }
    public static BigInteger big(String value) { return new BigInteger(value); }

    public static BigInteger isPrime(BigInteger n) {
        BigInteger m = n;
        BigInteger a = big(2);
        while (a.multiply(a).compareTo(m) <= 0) {
            if (Thread.currentThread().isInterrupted()) {
                System.err.println("Interrupted!");
                return null;
            }
            if (m.remainder(a).equals(big(0)))
                throw new RuntimeException();
            else
                a = a.add(big(1));
        }
        return n;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<BigInteger>> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            BigInteger n = big("10000000000").add(big(i));
            tasks.add(() -> isPrime(n));
        }
        BigInteger result = executor.invokeAny(tasks);
        System.out.println(result + " is prime");
    }
}