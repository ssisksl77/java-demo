package java8_impatiant.concurrent.sec06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 재진입 가능 잠금
 * 공유 변수가 손상되는 것을 피하려면 한 번에 한 스레드에서만 새로운 값을 계산하고 설정할 수 있게 해야 한다.
 * 인터럽션 없이 온전히 실행해야 하는 코드를 임계영역(critical section)이라고 한다.
 * 임계 영역은 잠금(lock)을 이용해서 구현할 수 있다.
 * 여기서는 ReentrantLock을 이용하지만 synchronize를 사용하기도 한다.
 * 두 차이는 명시적 락이랑 암시적 락이다.
 */
public class LockDemo {
    public static int count;  // 이 녀석들 여러 스레드에서 공유한다
    public static Lock countLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 100; i++) {
            Runnable task = () -> {
                for (int k = 1; k <= 1000; k++) {
                    countLock.lock();  // 락을 걸어서 한 스레드만 계산하도록한다.
                    try {
                        count++; // Critical section (임계영역)
                    } finally {
                        countLock.unlock(); // Make sure the lock is unlocked  꼭 unlock()을 보장해야 한다.
                    }
                }
            };
            executor.execute(task);
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("Final value: " + count);
    }
}