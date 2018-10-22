package java8_impatiant.concurrent.sec06;

/**
 * 잠금은 단순히 잠금 설정 역할만 하는 건 아니다. 잠금은 가시성도 보장한다. Visibility 10.2.1에 가시성에서 큰 고뇌를 안겨줬던 done 변수를 생각해보자.
 * while(!done) ...
 * done 변수를 대상으로 하는 쓰기와 읽기에 모두 잠금을 이용하면, set()으로(만) 하는 업데이트를 get()으로(만) 볼 수 있다.
 */
public class Flag {
    private boolean done;
    public synchronized void set() { done = true; }
    public synchronized boolean get() { return done; } // 지웠다가 넣었다가 하면서 테스트 해보자. 신기함.
}