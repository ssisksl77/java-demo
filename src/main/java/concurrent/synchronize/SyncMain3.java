package concurrent.synchronize;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 드디어 우리가 원한 무언가를 찾은 것 같다.
 * 참 쉽다!
 *
 * 그런데 정말 그럴까? 이렇게 해보자.
 * AnotherThread라는 녀석을 만들고 Vector를 같이 수정하도록 해보자. 왜냐하면 병렬프로그래밍이니까
 * -> SyncMain4.java , AnotherThread.javañ
 */
public class SyncMain3 {
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
        new SyncMain3().test();
//        OUTPUT :
//        [1, 2]
//        pool-1-thread-1
//        pool-1-thread-3
//        pool-1-thread-2
//        [1, 2, 3, 4, 3]
    }
}
