package java8lambda.chapter05.dto;

import java.util.concurrent.locks.Lock;

/**
 * 이 코드는 Lock 인터페이스에서 동작할 번거로운 작업들을 모두 흡수해버린다.
 * 따라서 코드의 나머지 부분에서 많은 이득을 얻을 수 있다.
 * runLocked() 메서드를 사용하여 크리티컬 섹션을 래핑할 수 있다.
 * Locking에서 어떻게 쓰는지 보자.ㄴ
 */
public class Locker {
    public static void runLocked(Lock lock, Runnable block) {
        lock.lock();
        try {
            block.run();
        } finally {
            lock.unlock();
        }
    }
}
