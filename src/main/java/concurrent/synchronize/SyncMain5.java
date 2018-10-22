package concurrent.synchronize;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 완벽하게 작동하는 것을 알 수 있다.
 * 가장 기본적이고 중요한 락을 안전하게 이용하기 위한 철칙!
 *
 * 동기화를 시킬 때는 필요한 행위가 모두 아토믹이 되야 하며
 * 변경되는 상태를 락으로 동기화 시킬 때는 모두 같은 락(하나의 락)으로 다뤄져야 한다!
 *
 * 잊지 말자!
 */
public class SyncMain5 {
    public void test() {
        final Vector<Integer> v = new Vector<>();
        v.add(1);
        v.add(2);

        CountDownLatch latch = new CountDownLatch(3);

        Runnable r1 = () -> {
            synchronized (v) {
                int last = v.lastElement();
                System.out.println(Thread.currentThread().getName() + " gets " + last);
                try{
                    Thread.sleep(500);
                }  catch (InterruptedException e) {}
                finally {
                    latch.countDown();
                }
                v.addElement(last+1);
            }
        };
        Runnable r2 = () -> {
            synchronized (v) {
                try {
                    int last = v.lastElement();
                    System.out.println(Thread.currentThread().getName() + " gets " + last);
                    v.addElement(last+1);
                }
                finally {
                    latch.countDown();
                }
            }
        };
        Runnable r3 = r2;

        AnotherThread anotherThread = new AnotherThread();
        ExecutorService exec = Executors.newCachedThreadPool();
        System.out.println(v);

        exec.execute(r1);
        anotherThread.jackin(v);
        exec.execute(r2);
        exec.execute(r3);

        exec.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e ) {}
        System.out.println(v);
    }

    public static void main(String[] args) {
        new SyncMain5().test();
//                [1, 2]
//        pool-1-thread-1 gets 2
//        Thread[pool-2-thread-1,5,main]
//        pool-2-thread-1 gets 3
//                [1, 2, 3, 4]
//        pool-1-thread-2 gets 4
//        pool-1-thread-1 gets 5
//                [1, 2, 3, 4, 5, 6]
    }
}
