package concurrent.volatile_test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * volatile을 이용해볼 것이다.
 * 변한 것은 volatile 키워드를 넣은 것 뿐이다.
 * 이것만으로 제대로 작동할 수 있을까?
 */
public class VolatileTest3 {
    private volatile static boolean FLAG = true;

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
         * 아주 잘 작동하는 것을 알 수 있다.
         * COUNTER와 무슨 차이가 있길래 여기선 잘 된다는 것일까?
         * 여기서는 조회하여 값을 변동하는 내용이 없다.
         *
         * 새로운 값을 생성하여 FLAG값을 바꿀뿐이다. read-write의 내용이 아닌.
         * 그저 write만 하는 것이다!
         *
         * 게다가 이 write은 이전 값이라는 전혀 관계가 없다. FLAG값은 toggle되는 내용이면 안된다.
         * 이전 값을 이용하여 값을 바꾸려면 차라리 synchronize를 사용하는 것이 안전하다.
         */

    }

}
