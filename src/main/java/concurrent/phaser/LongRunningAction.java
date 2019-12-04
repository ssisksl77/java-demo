package concurrent.phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * 1. LongRunningAction 클래스가 생성되면, Phaser 인스턴스에 register()를 이용하여 등록한다.
 * 이러면 해당 Phaser의 스레드 수가 증가한다.
 * 
 * 2. arriveAndAwaitAdvance()는 현재 스레드가 barrier에서 기다리도록 한다. 
 * 이전에 말했듯이, register된 파티원들의 수와 도착한 파티원의 수가 같으면 장벽을 넘어서 프로세스를 진행한다.
 * 
 * 3. 프로세스가 끝나면, 현재 스레드는 arriveAndDeregister()로 등록해제(deregister)한다. 
 * 
 */
public class LongRunningAction implements Runnable{
	private String threadName;
    private Phaser ph;
    
    LongRunningAction(String threadName, Phaser ph) {
        this.threadName = threadName;
        this.ph = ph;
        ph.register();
    }
 
    @Override
    public void run() {
        ph.arriveAndAwaitAdvance();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ph.arriveAndDeregister();
    }
    
    public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		/* 
		 * Phaser instance를 생성하는 데 전달인자(argument)로 1을 넣는다. 
		 * 이것은 현재 스레드에서 register() 메소드를 실행하는 것과 같다.
		 * 이것을 하는 이유는, 이제부터 3개의 Worker thread를 만들 것이다.
		 * main thread는 coordinator(조정자)이다. 하여 Phaser는 4개의 스레드가 register된 상태여야 한다.
		 * 
		 * */
		Phaser ph = new Phaser(1);
		System.out.println(ph.getPhase() == 0);
		/*
		 * Phaser class는 부모 인스턴스를 넘길 수 있는 생성자를 가진다.
		 * 이 기능은 꽤나 유용하다. 많은 수의 파티원을 가지고 있으며, 얘네들은 대량의 synchronization contention costs(동기화 경합 비용)이 발생할 경우.
		 * 이런 경우에 Phaser들의 인스턴스들은 하위Phaser그룹이 공통parent를 공유하도록 설정할 수 있다.
		 *  
		 *  Next, let's start three LongRunningAction action threads, which will be waiting on the barrier until we will call the arriveAndAwaitAdvance() method from the main thread.
		 *  이제 LongRunningAction 
		 * */
		 
		
		
		
	}

}
