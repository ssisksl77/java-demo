package concurrent.synchronize;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 어라? 안된다
 * 아주 쉽게 프로그램이 무너졌다.
 * 분명 synchronized(this)를 다 했는데!!
 *
 * 문제는 락에 있다.
 * 락이 다르면 안된다. 공유되는 상태의 락은 하나로 관리해야 한다. 현재 락은 3개로 운영되고 있다.
 * SyncMain4, AnotherThread 그리고 Vector
 *
 * 어떤 인스턴스의 락을 사용해야 하나로 통일될까?
 * Vector인스턴스다.
 * 다시 바꿔보자.
 * 모든 락을 synchronized(v)로 바꾸는 것이다.
 * -> SyncMain5.java, AnotherThread2.java
 */
public class SyncMain4 {
    public void test() {
        final Vector<Integer> v = new Vector<>();
        v.add(1);
        v.add(2);

        CountDownLatch latch = new CountDownLatch(3);

        Runnable r1 = () -> {
            synchronized (this) {
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
            synchronized (this) {
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
        new SyncMain4().test();
//        [1, 2]
//        pool-1-thread-1 gets 2
//        Thread[pool-2-thread-1,5,main]
//        pool-2-thread-1 gets 2
//        [1, 2, 3]
//        pool-1-thread-3 gets 3
//        pool-1-thread-2 gets 4
//        [1, 2, 3, 3, 4, 5]
    }
}
