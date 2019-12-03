package concurrent.phaser;

import java.util.concurrent.Phaser;

/**
 * java.util.conccurrent.Phaser
 * - https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Phaser.html
 * A resuable synchronization barrier, similar in functionality to CyclicBarrier and CountDownLatch but supporting more flexible usage
 * CyclicBarrier/CountDownLatch 와 비슷 하지만 좀 더 세밀한 조정이 가능하다.
 * 
 * 1. 등록 : 동기화할 파티원을 맘대로 조율할 수 있다!
 * Registration. unlike the case for other barriers, the number of parties registered to synchronize on a phaser may vary over time.
 * 다른 베리어들과 다르게, Phaser에서 동기화할 수록 등록 되는 파티원(당사자)들의 수는 시간에 따라 달라질 수 있다.
 * 
 * Tasks may be registered at any time 
 * (using methods {@link java.util.concurrent.Phaser#register}, {@link java.util.concurrent.Phaser#bulkRegister(int)}
 * , or forms of constructors establishing initial numbers of parties), and optionally deregistered upon any arrival 
 * (using {@link java.util.concurrent.Phaser#arriveAndDeregister()}).
 * Task들은 어느 시기에서나 레지스터(등록) 될 수 있고, 어느 도착점에서나 맘대로 빠져나올 수 있다.
 * 
 * As is the case with most basic synchronization constructs, registration and deregistration affect only internal counts;
 *   대부분 기본 동기화 구성과 마찬가지로, registration/deregistration는 내부 카운트에만 영향을 준다.
 * they do not establish any further internal bookkeeping, so tasks cannot query whether they are registered.
 * (However, you can introduce such bookkeeping by subclassing this class.)
 *   더 이상 내부 회계장부를 설정하지 않으므로, 작업이 등록되었는지 여부를 쿼리를 가져올 수 없다 (상속하면 될 것)
 * 
 * 2. Synchronization
 * Like a CyclicBarrier, a Phaser may be repeatedly awaited.
 *   CyclicBarrier와 동일하게, Phaser도 await()을 반복적으로 한다.
 * Method arriveAndAwaitAdvance() has effect analogous to CyclicBarrier.await. 
 *   Phaser의 arriveAndAwaitAdvance는 CyclicBarrier.await와 유사하다.
 *   각 phaser 생성에는 연관된 번호가 붙는다. 이 번호는 0에서 시작하며 모든 당사자가 Integer.MAX_VALUE에 도달하면 0으로 줄어든다.
 *   phase number로 사용하여, 1. phaser를 도착할 때 2. [others]를 기다릴 때에 따라 독립적으로 action을 취할 수 있게 된다. 
 *   (위 두 기능을 Arrival, Waiting으로 나눌 수 있다)
 *   - Arrival. arrive(), arriveAndDeregister().
 *     이 메소드들은 블록을 하지 않으며 [arrival phase number]를 리턴한다. 이 번호는 도착이 적용된 Phaser의 phase number이다.
 *     특정 phase에 마지막 파티원이 도착하면, 선택적 조치(optional action)이 수행되고 phase(단계)가 앞으로 나아간다.
 *     이 액션들은 [phase]진행을 트리거하는 당사자가 수행하며, 또한 onAdvance(int,int) 메소드를 오버라이딩하여 종료를 제어한다.
 *     (그러니까 onAdvance를 재구현해놓았다)
 *     이렇게 오버라이딩하는 것은 CyclicBarrier의 장벽동작과 유사하지만 더 많은 유연성을 줄 것이다.
 *   - Waiting. awaitAdvance(int)
 *     이 메소드는 [arrival phase number]를 나타내는 인수가 필요하다. 그리고 phaser가 앞으로 나아가거나 (이미 다른 phase로 가버렸을 때) 리턴한다.
 *     (말그대로 기다리는 거군)
 *     CyclicBarrier와 유사하지만 다른 점은, 다른 스레드가 interrupt로 중단되어도 계속 기다린다는 점이다.
 *     인터럽트가 되도록 하거나 시간 내 기다리다 종료하는 버전도 사용할 수 있지만 그렇다고 phase의 상태가 변경되지는 않는다.
 *     필요하면 forceTermination을 호출 한 후 해당 예외의 핸들러 내에서 연관된 복구를 수행 할 수 있다.
 *     
 *     Phaser는 ForkJoinPool에서도 실행되는 작업에서도 사용할 수 있다. 
 *     이 기능은 다른 사람이 단계 진행을 기다리는 동안 차단 될 때 작업을 실행하기에는 충분한 병렬 처리를 보장한다.
 * 	자세한 건 도큐먼트에서...
 * 
 * 
 * - https://www.baeldung.com/java-phaser
 * 1. Overview
 * The Phaser is a barrier on which the dynamic number of threads need to wait before continuing execution.
 * In the CountDownLatch that number cannot be configured dynamically and needs to be supplied when we're creating the instance.
 *  Phaser는 동적 스레드 수가 실행을 계속 하기 전에 대기해야 하는 장애물이다. (동적 스레드 수란 말은 스레드 수가 계속 바뀜 아무때나)
 * 
 * 2. Phaser API
 * 스레드들은 다음 실행 단계로 가기 전에 barrier에서 기다릴 필요가 있는 로직을 만들 때 사용한다.
 * 여러 개의 실행 phase를 만들어 조정할 수 있으며, 각 프로그램 phase(단계) 마다 Phaser인스턴스를 재사용 할 수 있다.
 * 각 phase(단계)는 다른(다음) phase로 진행을 기다리는 서로 다른 수의 스레드를 가질 수 있다.
 * 
 * 여기에 참여하려면 Phaser 인스턴스에 register()메소드를 이용해서 등록해야 한다.
 * 이렇게 하면 등록파티원의 숫자만 증가하며, 현재 스레드가 등록되어 있는지 확인할 수는 없다. 만약 알고 싶으면 subclass로 구현해야 한다.
 * 
 * 스레드가 barrier에 도달하면 시그널을 보낸다. arriveAndAwaitAdvnace(), 라고 [도착했으며  다음 단계로 나아가길 기다린다] 라는 이름의 메소드다.
 * 그리고 이 메소드는 block 상태가 된다.
 * 
 *  이제 도착 파티원(arrived parties)과 등록된 파티원(registed parties)의 수가 동일해지면, 이제 이 단계(phase)를 넘어서 실행을 계속하게 된다.
 *  그리고 phase number가 증가한다. getPhase() 메서드를 호출하면 현재 phase number를 얻을 수 있다.
 *  
 *  스레드가 작업을 마치면 arriveAndDeregister() 메소드를 호출하여 현재 스레드가 이 특정 단계(phase)에서는 더 이상 설명되어서는 안 된다는 신호를 보내야 한다.
 * 	
 * 
 * 3. Implementing Logic Using Phaser API
 * Let's say that we want to coordinate multiple phases of actions. Three threads will process the first phase, 
 * and two threads will process the second phase.
 * 이제 액션을 여러 단계로 만들어서 조정하자. 3개의 스레드가 있으며
 * 
 * @author yuhnam
 *
 */
/*class LongRunningAction implements Runnable {
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
}*/


public class Test1 {
	
}
