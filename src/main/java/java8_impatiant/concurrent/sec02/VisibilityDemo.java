package java8_impatiant.concurrent.sec02;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 가시성. 모던 프로세서를 이용할 때는 변수를 읽고 쓰는 것 같은 간단한 작업조차 엄청나게 복잡해질 수 있다.
 *  volatile의 필요성
 */
public class VisibilityDemo {
    // public static boolean done = false;  // volatile을 추가하면 된다!!!
    public static volatile boolean done = false;

    public static void main(String[] args) {
        Runnable hellos = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Hello " + i);
            }
            done = true;
        };

        Runnable bye = () -> {
            int i = 1;
            while (!done) i++;
            System.out.println("BYE " + i);
        };

        Executor exec = Executors.newCachedThreadPool();
        exec.execute(hellos);
        exec.execute(bye);
    }
    /**
     * 내 노트북에서는 Hello 999까지만 출력하고 종료되지는 않았다. 두 번째 테스크를 실행하는 스레드에서 다음 문장의 효과가 보이지 않은 것이다.
     * done = true 이 효과가 먹히지 않았다.
     * 이유는 캐싱(caching)과 명령어 재배치(instruction reordering)와 관련하여 여러 가지를 들 수 있다.
     *
     * 개발자는 done과 같은 메모리 위치를 램 칩의 트랜지스터에서 어딘가에 있는 비트로 생각한다. 하지만 램침은 모던 프로세서보다 몇 배는 느리다.
     * 그래서 프로세서는 필요한 데이터를 레지스터(register)나 보드에 달린 메모리 캐시(memory cache)에 젖아하려고 하고, 결국에는 변경을 다시
     * 메모리에 쓴다. 이러한 캐싱은 프로세서 퍼포먼스(수행 성능)에서 빠질 수 없는 역할 을 한다.
     *  && 캐시된 사본을 동기화 하는 연산이 있긴 하지만, 요청을 받을 때만 한다.
     *
     *  이게 끝이 아니다. 컴파일러, 가상 머신, 프로세서는 프로그램의 의미를 바꾸지 않는 한 연산 속도를 올릴 목적으로 명령어의 순서를 변경할 수 있게
     *  되어 있다!!
     *
     *  x = y와 관련없는 값.
     *  y = x와 관련없는 값.
     *  z = x + y;
     *
     *  처음 두 단계는 세 번째 단계보다는 먼저 일어나야 하지만, 이 둘은 순서가 바쪄도 된다.
     *  프로세서는 처음 두 단계를 병렬로 수행하거나(종종 이렇게 한다.) 두 번째 단계의 입력을 먼저 이용할 수 있으면 순서를 바꾼다.
     *
     *  while(!done) i++;
     *
     *  이 루프는 구현부에서 done을 변경하지 않으므로 다음과 같이 재배치 될 수 있다.
     *  if (!done) while (true) i++;
     *  평생도는거다!
     *
     *  최적화(optimization)는 동시 메모맂 접근이 없다고 가정한다. 동시 메모리 접근이 있을 때는 가상머신이 알아야 한다.
     *  동시 메모리 접근이 있을 때는 가상머신이 일아야 한다. 그래야 가상 머신이 부적절한 재배치를 금지하는 프로세서 명령어를 만들 수 있다.
     *
     *  변수 업데이트가 보이게 보장하는 방법이 몇 가지 있다. 다음은 이 방법을 요약한 것이다.
     *  1. final 변수의 값은 초기화 후에 보인다.
     *  2. static 변수의 초깃값은 정적 초기화(initialization)후에 보인다.
     *  3. volatile 변수의 변경은 보인다.
     *  4. 잠금을 해제하기 전에 일어나는 변경은 같은 잠금을 획득하는 쪽에 보인다.
     *
     *  위 예제에서 done을 volatile 제어자로 선언하면 문제가 사라진다.
     *  private static volatile boolean done;
     *
     *  이렇게 하면 컴파일러는 한 태스크에서 done을 변경했을 때 다른 태스크에도 해당 변경이 보이도록 보장하는 데 필요한 명령어를 만들어낸다.
     *  여기서는 volatile로도 충분하다. 그러나 경쟁조건(다음클래스)에서도 살펴보지만, 공유 변수를 volatile로 선언하는 방법은 일반적으로
     *  해결책이 못된다.
     */
}
