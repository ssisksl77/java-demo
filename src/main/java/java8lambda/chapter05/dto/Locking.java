package java8lambda.chapter05.dto;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 락을 다루는 첫번째 코드이다. 이 코드는 너무 장황하고 쉽게 오류를 발생시킬 수 있으며
 * 유지 보수도 어렵다. 람다 표현식을 사용하여 잠금을 관리하는 작은 클래스를 만들자.(Locker.java)
 */
public class Locking {
    Lock lock = new ReentrantLock();

    protected  void setLock(final Lock mock) {
        lock = mock;
    }

    public void doOp1() {
        lock.lock();

        try {
            // ..critical code
        } finally {
            lock.unlock();
        }
    }

    public void doOp2() {
        Locker.runLocked(lock, () -> {/* critical code... */});
    }

    public void doOp3() {
        Locker.runLocked(lock, () -> {/* critical code ... */});
    }

    public void doOp4() {
        Locker.runLocked(lock, () -> {/* critical code ... */});
    }
}
