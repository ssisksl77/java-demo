package concurrent.phaser;

import java.util.concurrent.Phaser;

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

}
