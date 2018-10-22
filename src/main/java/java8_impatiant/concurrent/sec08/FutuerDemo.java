package java8_impatiant.concurrent.sec08;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 완료 가능한 퓨처
 *
 * 넌블로킹 호출을 다루는 전통적인 방법은 이벤트 핸들러(event handler)를 이용하는 것이다. 개발자가 태스크가 완료된 후에 일어나야 하는 액션에 대응하는
 * 이벤트 핸들러를 등록한다.
 * 문제는 비동기로 작업을 하게 되면 1, 2, 3단계로 수행될 거라고 생각했던 것이 서로 다른 핸들러로 흩어지고, 오류를 찾기 힘드어진다.
 * 예를들어 2단계가 '사용자 로그인'이라 하자.
 * 사용자가 인증 정보를 잘못 입력할 수 있는데 그럴때는 이 단계를 반복해야 한다. 이런 여러가지 제어 흐름을 구현하려면 여러 실패를 겪고 서야 만들어지는데
 * 그 결과물들은 아주 복잡합이 뭉쳐져 있어 아주 참혹할 것이다. 보는 것마저 고통스러울 것이다.
 *
 * CompletableFutuer 클래스는 이벤트 핸들러와 달리 합성할 수 있다.
 *
 * 웹 크롤러를 만들어보자.
 *
 * 여기서 기억해야 할 것은 비동기 이벤트 핸들러끼리 합성하고 싶거나 Future를 반환하는 Callable이 있을 때 그것들끼리 묶고 싶으면
 * CompletableFuture를 이용하자는 것이다. 나중에 쓸 때 도큐먼트를 보면 되니까...
 */
public class FutuerDemo {
    public static CompletableFuture<String> readPage(URL url) {
        return null;
    }
    public static List<URL> getLinks(String page) {
        return null;
    }

    public static void main(String[] args) throws MalformedURLException {
        /** 비동기 이벤트가 이렇게 합쳐질 수 있다.*/
        CompletableFuture<String> contents = readPage(new URL("URL"));
        CompletableFuture<List<URL>> links = contents.thenApply(FutuerDemo::getLinks);
        CompletableFuture<List<URL>> links2 = contents.thenApplyAsync(FutuerDemo::getLinks);  // 비동기 연결.
    }
}
