package concurrent.synchronize;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnotherThread2 {
    public void jackin(Vector<Integer> v) {
        CountDownLatch latch = new CountDownLatch(1);
        Runnable r = () -> {
            synchronized (v) {
                try {
                    System.out.println(Thread.currentThread());
                    int last = v.lastElement();
                    System.out.println(Thread.currentThread().getName() + " gets " + last);
                    v.addElement(last+1);
                } finally {
                    latch.countDown();
                }
            }
        };

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(r);
        exec.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) { }
        System.out.println(v);
    }
}
