package java8_impatiant.concurrent.sec06;

/**
 * synchronize 키워드
 * 자바의 모든 객체에는 고유의 잠금이 포함되어 있다. 그러므로 명시적인 잠ㄱ므을 사용할 필요가 없다. 하지만 고유의 잠금을 이해하려면
 * 명시적인 잠금을 익혀두는 게 좋다.
 * synchronized 키워드는 고유의 잠금을 잠그는 데 사용한다.
 * synchronize는 두 가지 형태로 사용된다.
 * 1. 블록을 잠근다.
 * synchronized (obj) { }
 * 이 코드는 본질적으로 다음 코드를 의미 한다.
 * obj.intrinsicLock.lock();
 * try {  } finally { obj.intrinsicLock.unlock(); }
 * 2. 메서드를 잠근다.
 * public synchronized void method() { }
 * 이 코드는 다음과 같다.
 * public void method() {
 *     this.intrinsicLock.lock();
 *     try { } finally { this.intrinsicLock.unlock(); }
 * }
 *
 * 아래 코드를 보자.
 */
public class Counter {
    private int value;
    public synchronized int increment() {
        value++;
        return value;
    }
}
