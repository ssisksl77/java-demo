package concurrent.synchronize;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 동기화란?
 * Java에서 병렬프로그래밍을 생각하면 synchronized 키워드를 먼저 생각할 것이다.
 * 혹은 Reentrant Lock을 생각하는 사람도 많을 것이다.
 * 일단 걸면 모든 스레드들을 한 줄로 세우고 일ㅇ르 시킨다는 점에서 synchronized만 쓰면 모든 것이 해결된다고 믿는다.
 * 성능은 느려질지라도...
 *
 * Java API 중에 Vector를 한 번 보자. 대부분 synchronized 키워드가 붙어 있어서 병렬프로그래밍에 적절하지 않을까 생각된다.
 *
 * 이쯤에서 한 번 Vector class의 메서드 몇 개를 구경해보자.
 *
 * =====================================================
 * public synchronized void addElement(E obj) {
 *   modCount++;
 *   ensureCapacityHelper(elementCount + 1);
 *   elementData[elementCount++] = obj;
 * }
 * =====================================================
 * public synchronized E get(int index) {
 *   if (index >= elementCount)
 *     throw new ArrayIndexOutOfBoundsException(index);
 *
 *   return elementData(index);
 * }
 * =====================================================
 * public synchronized E lastElement() {
 *   if (elementCount == 0) {
 *     throw new NoSuchElementException();
 *   }
 *   return elementData(elementCount - 1);
 * }
 * =====================================================
 *
 * 위 3개 메서드 외에서 대부분 synchronized가 되어 있다.
 * 그렇다면 위의 메서드들을 사용하면 성능은 후달려도, 병렬처리에서 제 값을 얻을 수 있을까?
 *
 * 한 번 해보자.
 * 아래 내용을 실행해보면 우리가 생각 한 것과는 다르다.
 * r1이 샐행되면서 2를 조회한다. 그리고 0.5초 쉰다.
 * r2(혹은 r3)는 2를 조회한다. 3(2+1)을 더한다.
 * r3(혹은 r2)는 3을 조회한다. 4(3+1)을 더한다.
 * r1이 0.5초를 다 쉬고 3(2+1)을 더한다.
 *
 * 이것은 조회와 synchronized되어 있지만 조회-계산-삽입은 synchronized 되어 있지 않기 때문이다.
 * 병렬처리에서는 여러 가지의 action이 한 번에 진행될 때는 atomic해야 한다.
 * 고로 해당 업무를 락으로 막아야 한다. 그럼 어디에 막아야 할까?
 * 메소드에 박아보자!
 * SyncMain2.java...
 */
public class SyncMain {
    public void test() {
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
        new SyncMain().test();
//        OUTPUT :
//        [1, 2]
//        pool-1-thread-1
//        pool-1-thread-3
//        pool-1-thread-2
//        [1, 2, 3, 4, 3]
    }
}
