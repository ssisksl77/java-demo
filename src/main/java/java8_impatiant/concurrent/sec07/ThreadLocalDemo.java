package java8_impatiant.concurrent.sec07;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 스레드 로컬 변수
 * ThreadLocal 헬퍼 클래스를 이용해 각 스레드에 고유 인스턴스를 부여하는 방법으로 공유를 피할 수도 있다.
 * 예를 들어, NumberFormat 클래스의 인스턴스를 스레드에 안전하지 않다. 정적 변수가 있는 상황을 생각해보자.
 * public static final NumberFormat currentcyFormat = NumberFormat.getCurrencyInstance();
 * ...
 * String amountDue = currencyFormat.format(total);
 * 두 스레드가 다음과 같은 연산을 실행하면 동시 접근 때문에 NumberFormat 인스턴스가 사용하는 내부 자료 구조가 손상될 수 있다. (그러면 결과는 쓸모 없어진다)
 *
 *
 *
 * 동기화를 이용할 수도 있지만 비용이 많이 든다. 또한, 대안으로 필요할 때마다 지역 NumberFormat 객체를 생성할 수도 있지만 이 방법도 소모적이다.
 *
 * 스레드발로 인스턴스 한 개를 생성하려면 다음과 같은 코드를 이용해야 한다!!!!
 *
 * public static final ThreadLocal<NumberFormat> currencyFormat = ThreadLocal.withInitial(()-> NumberFormat.getCurrencyInstance());
 * ...
 * String amountDue = currencyFormat.get().format(total);
 * 주어진 스레드에서 처음으로 get을 호출하면 생성자에 전달한 람다가 호출되어 '해당 스레드용' 인스턴스를 생성한다.
 * 이후로는 get 메서드에서 현재 스레드에 속한 인스턴스를 반환한다.
 */
public class ThreadLocalDemo {
    public static final NumberFormat sharedFormatter = NumberFormat.getCurrencyInstance(); // BAD
    public static final ThreadLocal<NumberFormat> currencyFormat = ThreadLocal.withInitial(() -> NumberFormat.getCurrencyInstance()); // GOOD

    public static String dollars(double value) {
        NumberFormat formatter = currencyFormat.get();
        // formatter = sharedFormatter
        // Try this to see what happens...
        return formatter.format(value);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            double value = 1000.01 * i;;
            tasks.add(() -> dollars(value));
        }
        List<Future<String>> result = executor.invokeAll(tasks);
        for (Future<String> f : result)
            System.out.println(f.get());
    }
}