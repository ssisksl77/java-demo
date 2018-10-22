package java8_impatiant.concurrent.sec02;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaceConditionDemo {
    public static volatile int count;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 1; i <= 100; i++) {
            int taskId = i;
            Runnable task = () -> {
                for (int j = 1; j <= 1000; j++) {
                    count++;
                }
                System.out.println(taskId + " : " + count);
            };
            exec.execute(task);
        }

        exec.shutdown();
        exec.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("Final Value : " + count);
        // 1000 2000 3000 4000 이렇게 결과를 출력해야 한다.
    }
    /**
     * 마지막 테스크 카운터를 100,000으로 출력하지 않네...
     * 수년 전 개인용 컴퓨터가  싱글CPU일 때는 지금보다 경쟁조건을 발견하기가 더 어려웠다.
     * 하지만 잘못된 값이 몇 초 또는 몇 시간 안에 계산되는지는 중요하지 않다.
     *
     *
     * 10.2.3 안전한 병행성을 실현하는 전략
     *
     * 첫번째 지침 : 가두기(confinement). 상당히 효과적인 전략이다. 하지만 여러 테스트 사이에서 데이터를 공유해야 하는 상황에서는 이 지침을 따르지 말자.
     * 두번째 지침 : 불변성(immutability). 불변 객체를 공유하는 일은 안전하다. 이를테면 태스크에서 결과를 공유 콜렉션에 추가하는 대신! 결과로 구성된
     * 불변 컬렉션을 만들 수 있다. 그리고 또 다른 테스크는 결과들을 또 다른 불변 자료 구조로 결합한다. (간단하지만 주의점이 있다 10.2.4 참고)
     *
     * 세번째 지침 : 잠금 (locking). 한 번에 한 테스트만 자료 구조에 접근할 수 있는 권한을 주는 방법으로 해당 자료 구조가 손상되는 일을 막을 수 있다.
     * 잠금은 병렬성 기회를 줄이므로 많은 비용이 들 수 있다. 예를 들어 결과를 공유 해시 테이블에 넣는 테스크가 많을 때는 업데이트가 수행될 때마다
     * 해당 해시 테이블이 잠긴다. 바로 이 부분이 실제 병목 지점이다.
     * 테스크 대부분이 차례를 기다려야 하는 상황에서는 유용한 일을 하지 않는다.(아니 일을 하지 않는다.) 때로는 데이터를 나눠서(partition) 동시에
     * 서로 다른 조각이 접근할 수 있게 할 수도 있다. 자바 병행성 라이브러리에서 제공하는 몇 가지 자료 구조는 스트림 라이브러리의 병렬 알고리즘처럼 파티셔닝을 한다.
     *
     * 이 작업을 직접 수행하려고 하지 말아야 한다! 올바르게 동작하도록 만들기가 정말 어렵기 때문이다. 대신 자바 라이브러리에서 제공하는 자료 구조와 알고리즘
     * 을 사용하기 바란다.
     *
     *
     * 10.2.4 불변 클래스
     * 한 번 인스턴스를 생성하면 변경할 수 없는 클래스.
     * 처음에는 불변 클래스로 할 수 있는 일이 많지 않아 보이지만 사실은 그렇지 않다.
     * 주의사항:
     * 1. 인스턴스 변수를 final로 선언해야 한다. final로 선언하지 않을 이유도 없을 뿐더러, final로 선언하면 중요한 이점을 얻게 된다. 가상 머신은
     * final 인스턴스 볒ㄴ수가 생성된 후에 이것이 보이도록 보장한다.(가시성 VisibilityDemo 참고)
     * 2. 어떤 메서드도 변경자가 될 수 없다. 메서드를 final 로 만들어서 오버라이드 하지 못하도록 하자.(상속해서 변경자 메서드가 되지 않도록 보장하자)
     * 3. 가변 상태를 유출하지 말자. 참조가 필요하면 복사해서 사본을 반환하거나 전달해야 한다.
     * 4. 생성자에서 this 참조를 노출하지 않아야 한다. 또 다른 메서드를 호출할 때 내부 참조를 전달하지 하지 말아야 한다는 사실은 알고 있었지만,
     * this라면?? this는 생성 후에는 안전하다. 하지만 "생성자 안"에서 this를 노출하면 누군가는 객체가 불완전한 상태에 놓인 상황을 관찰할 수 있다.
     * (생성자에서 this를 매개변수로 이벤트 리스너에 추가 한다던가 하는 행위)
     *
     *
     */
}
