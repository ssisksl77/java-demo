package concurrent.volatile_test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 우리는 volatile이 꽤나 편리한 경우를 알아볼 것이다.
 * 바로 아래와 같은 경우이다. FLAG가 있어서 어떤 상태의 변동을 확인하도록 하였다.
 * 하지만 아래의 코드는 우리가 생각한 것처럼 작동하지 않는다. 왜일까?
 */
public class VolatileTest2 {
    private static boolean FLAG = true;

    public static void main(String[] args) {
        // 이번에는 COUNTER를 더하는 것이 아니라. FLAG가 true인 동안은 무한루프를 도는 것이다.
        // FLAG가 false가 되었을 때 "bye"를 출력하고 멈출 것이다.
        Runnable r = () -> {
            while(FLAG) {

            }
            System.out.println("bye");
        };

        // FLAG를 false로 바꾸기 위한 Runnable을 생성하자.
        Runnable r2 = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("FLAG false!!");
            FLAG = false;
        };

        ExecutorService es = Executors.newCachedThreadPool();


        // 10개의 스레드가 한번에 돌기 시작한다.
        for (int i = 0; i < 10; i++) {
            es.execute(r);
        }
        // FLAG를 false로 바꾸보자.
        es.execute(r2);
        // ExecutorService가 일을 다 끝내고 죽도록 하자.
        es.shutdown();

        /**
         * 잘 꺼지지 않는 경우가 발생한다.
         * volatile을 이용해보자. VolatileTest3으로 가자.
         */

    }

}
