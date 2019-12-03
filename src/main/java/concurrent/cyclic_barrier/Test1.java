package concurrent.cyclic_barrier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

/**
 * 
 * http://sjava.net/tag/cyclicbarrier/
 * Cyclic Barrier는 
 * 
 * https://www.baeldung.com/java-cyclicbarrier-countdownlatch
 * CountDownLatch and CyclicBarrier are used for managing multi-threaded applications.
 * And, they are both intended to express how a given thread or group of threads should wait.
 * 둘 다 스레드 그룹 단위로 wait(서로를 기다리는) 의도를 가진다.
 * 
 * 2.1. CountDownLatch
 * A CountDownLatch is a construct that a thread waits on while other threads count down on the latch until it reaches zero.
 * 카운트다운래치는 다른 스레드들이 latch의 카운트를 내려서 0이 될 때까지 현재 스레드가 기다리는 것이다.
 * 
 * 2.2 CyclicBarrier
 * A CyclicBarrier is a reusable construct where a group of threads waits together until all of the threads arrive. 
 * 사이클베리어는 스레드 그룹이 모두 wait에 도달 할 때까지 기다린다.
 * the barrier is broken and an action can optionally be taken
 * 이 시점에는 장벽이 깨지면 선택적으로 조치를 취할 수 있다.
 * 
 * 2.3. Further Reading
 * - https://www.baeldung.com/java-countdown-latch
 * - https://www.baeldung.com/java-cyclic-barrier
 * 
 * 3. Tasks vs Treads
 * In short, CyclicBarrier maintains a count of threads whereas CountDownLatch maintains a count of tasks.
 * CyclicBarrier는 스레드의 카운드를 유지하고 CountDownLatch는 Task의 카운드를 유지한다.
 * 즉, CyclicBarrier는 모든 스레드가 wait()을 하는 것이 중요하지만 CountDownLatch는 한 업무를 끝내서 countDown()을 호출하는 횟수가 중요하다.
 * 즉 CountDownLatch는 한 곳에서 여러번 해도 상관없지만, CyclicBarrier는 wait()한 번 걸면 멈추기 때문에 여러번 호출 할 수 없다.
 * {@link #code1} CountDownLatch 예제 
 * {@link #code2} CyclicBarrier 예제
 * 
 * 4.Reusability
 * when the barrier trips in CyclicBarrier, the count resets to its original value.
 * CountDownLatch is different because the count never reset.
 * CyclicBarrier에서 barrier에서 wait()을 다 해서 이동할 때, count값이 저절로 원래 값으로 바뀐다.
 * CountDownLatch에서는 절대 리셋되지 않는다.
 * 
 * {@link #code3} CountDownLatch 예제
 * 비록 20개의 스레드가 countDown()을 호출했지만, 한 번 0에 도달하면 다시는 리셋되지 않는다. 
 * 
 * {@link #code4} CyclicBarrier 예제
 * 
 * @author yuhnam
 *
 */
public class Test1 {
	public static void code1 () throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		Thread t = new Thread(() -> {
			countDownLatch.countDown();
			countDownLatch.countDown();
		});
		
		t.start();
		countDownLatch.await();
		
		System.out.println("code1");
		System.out.println(0 == countDownLatch.getCount());
		System.out.println("===");
		
	}
	
	public static void code2 () {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
		Thread t = new Thread(() -> {
			try {
				cyclicBarrier.await();
				cyclicBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		});
		
		t.start();
		System.out.println("code2");
		System.out.println(1 ==  cyclicBarrier.getNumberWaiting());
		System.out.println(cyclicBarrier.isBroken());
		System.out.println("===");
	}
	
	
	
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException, TimeoutException {
//		code1();
//		code2();
//		code3();
		code4();
	}

	private static void code4() throws InterruptedException, BrokenBarrierException, TimeoutException {
		// https://docs.oracle.com/javase/6/docs/api/java/util/concurrent/package-summary.html
		// https://stackoverflow.com/questions/6916385/is-there-a-concurrent-list-in-javas-jdk 참고
		List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7);
		ExecutorService es = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 20; i++) {
			es.execute(() -> {
				int numberWaiting = cyclicBarrier.getNumberWaiting();
				if (numberWaiting >= 0) {
					outputScraper.add("Count Updated"); 
				}
				try {
					cyclicBarrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			});			
		}
		
		es.shutdown();
		System.out.println(outputScraper);
		System.out.println(outputScraper.size() > 7);
		cyclicBarrier.await();
	}

	private static void code3() {
		List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
		CountDownLatch countDownLatch = new CountDownLatch(7);
		ExecutorService es = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 20; i++) {
			es.execute(() -> {
				long prevValue = countDownLatch.getCount();
				countDownLatch.countDown();
				if (countDownLatch.getCount() != prevValue) {
					outputScraper.add("Count Updated");
				}
			});
		}
		
		es.shutdown();
		System.out.println(outputScraper);
		System.out.println(outputScraper.size() <= 7);
	}
}
