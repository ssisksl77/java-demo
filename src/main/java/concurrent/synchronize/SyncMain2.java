package concurrent.synchronize;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 우린 test() 메소드에 synchronized를 걸었으나 값은 변한 것이 없다.
 * 우린 Vector가 했던 실수를 반복한 것이다.
 * 수행하는 행위를 원자화(Atomic)해야 한다.
 * 그렇다면 synchronized(this)를 이용해서 구간만 동기화 하는 것은 어떨까?
 * -> SyncMain3.java
 */
public class SyncMain2 {
    public synchronized void test() {
        final Vector<Integer> v = new Vector<>();
        v.add(1);
        v.add(2);

        CountDownLatch latch = new CountDownLatch(3);

        Runnable r1 = () -> {
            int last = v.lastElement();
            System.out.println(Thread.currentThread().getName() + " gets " + last);
            try{
                Thread.sleep(500);
            }  catch (InterruptedException e) {}
            finally {
                latch.countDown();
            }
            v.addElement(last+1);
        };
        Runnable r2 = () -> {
            try {
                int last = v.lastElement();
                System.out.println(Thread.currentThread().getName() + " gets " + last);
                v.addElement(last+1);
            }
            finally {
                latch.countDown();
            }
        };
        Runnable r3 = r2;

        ExecutorService exec = Executors.newCachedThreadPool();
        System.out.println(v);

        exec.execute(r1);
        exec.execute(r2);
        exec.execute(r3);

        exec.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e ) {}
        System.out.println(v);
    }

    public static void main(String[] args) {
        new SyncMain2().test();
//        OUTPUT :
//        [1, 2]
//        pool-1-thread-1
//        pool-1-thread-3
//        pool-1-thread-2
//        [1, 2, 3, 4, 3]
    }
}
