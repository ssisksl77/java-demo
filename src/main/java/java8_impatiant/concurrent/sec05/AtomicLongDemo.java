package java8_impatiant.concurrent.sec05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 원잣값
 * 여러 스레드에서 공유 카운터를 업데이트할 때 해당 업데이트는 스레드에 안전한 방식으로 일어나게 해야 한다.
 * java.util.concurrent.atomic 패키지에는 이를 위한 클래스가 있다. 이 클래스들은 안전하고 효율적인 머신 수준 명령어를 이용해서
 * 정수, long, boolean, 객체 참조, 배열에 적용하는 연산의 원자성을 보장한다.
 *
 * 하지만 여기서 이녀석들도 복잡한 업데이트를 하려하면 잘 동작하지 않는다.
 * 예를들어 서로 다른 스레드에서 관찰하고 있는 가장 큰 값을 추적한다고 해보자.
 *
 * public static AtomicLong largest = new AtomicLong();
 * ... 다른 여러 스레드에서... largest를 서로 바꾸고 있다.
 * largest.set(Math.max(largest.get(), observed)); // 오류! 경쟁조건. 다른 스레드들이 바꾸는 값들을 추적한다고? 이런...
 *
 * 이 업데이트는 원자적이지 않다. 대신 람다 표현식으로 updateAndGet을 호출해서 값을 업데이트 해야 한다.
 * larest.updateAndGet(x -> Math.max(x, observed));
 * 혹은
 * larest.accumulateAndGet(observed, Math::max)
 */
public class AtomicLongDemo {
    public static AtomicLong count = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 1000; i++) {
            int taskId = i;
            Runnable task = () -> {
                for (int k = 1; k <= 10000; k++)
                    count.incrementAndGet();
                System.out.println(taskId + ": " + count);
            };
            executor.execute(task);
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("Final value: " + count);
    }
}