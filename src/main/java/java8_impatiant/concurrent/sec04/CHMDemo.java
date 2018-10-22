package java8_impatiant.concurrent.sec04;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ConcurrentHashMap Demo
 * 스레드 안전 자료 구조
 * java.util.concurrent 패키지에 있는 컬렉션들은 영리하게 구현되어 있다.
 * 여러 스레드에서 자료 구조의 각기 다른 부분에 접근하면 서로 블로킹 하는 일 없이 동시에 접근 할 수 있게 한다.
 *
 * ConcurrentHashMap은 스레드가 안전한 연산을 할 수 있게 해주는 해시 맵이다. 스레드가 동시에 해당 맵에 연산을 수행할 때
 * 아무리 많은 스레드가 연산을 구행해도 내부가 손상되지 않는다.
 * ConcurrentHashMap은 상당수의 병행 리더(concurrent reader)와 일정 개수의 병행 라이터(concurrent writer)를 효율적으로 지원한다.
 *
 * 하지만 이걸로는 부족하다.
 * 그냥 put을 업데이트 하면 다른 스레드에서 같이 업데이트 할 수가 있다. 그럴 때는 compute메서드를 사용하자.
 * comput(word, (k,v) -> v == null ? 1 : v + 1)
 * compute 메서드는 원자적이다. 따라서 계산을 수행하는 도중에 다른 어떤 스레드에서도 해당 맵 엔트리를 변경할 수 없다.
 *
 * computeIfPresent, computeIfAbsent라는 변형도 있는데, 각각 기존 값이 있거나 없을 때만 새로운 값을 계산한다.
 *
 * putIfAbsent(word, 0L); 은 다음과 같이 초기화를 할 수도 있다.
 *
 * 키가 처음 추가되는 시점에 뭔가 특별한 작업을 해야 할 때가 있다. merge 메서드를 이용하면 이런 상황에서 특.히.편리하다.
 * 이 메서드는 키가 아직 없을 때 사용할 초깃값(initial value)을 파라미터로 받는다.
 * 키가 있을 때는 지정한 함수를 호출해서 기존 값과 초깃값을 결합한다.(compute와 달리 지정한 함수는 키를 처리하지 않는다.)
 * map.merge(word, 1L, (existingValue, newValue) -> existingValue + newValue);
 * 혹은
 * map.merge(word, 1L, Long::sum)
 * 이렇게 해야 한다.
 */
public class CHMDemo {
    public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    public static void process(Path path) {
        try {
            String contents = new String(Files.readAllBytes(path),
                    StandardCharsets.UTF_8);
            for (String word : contents.split("\\PL+")) {
                map.merge(word, 1L, Long::sum);
                // or map.compute(word, (k, v) -> v == null ? 1 : v + 1);
                /** or
                map.putIfAbsent(word, 0L);
                Long oldValue, newValue;
                do {
                    oldValue = map.get(word);
                    newValue = oldValue + 1;
                } while (!map.replace(word, oldValue, newValue));
                */
                /** but not 요건 에러가 난다.
                Long oldValue = map.get(word);
                Long newValue = oldValue == null ? 1 : oldValue + 1;
                map.put(word, newValue); // Error—might not replace oldValue
                 이렇게 하면 다른 스레드에서 같은 카운트(oldValue + 1)를 업데이트 하고 있을 지도 모른다.
                */
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Set<Path> descendants(Path p) throws IOException {
        try (Stream<Path> entries = Files.walk(p)) {
            return entries.filter(Files::isRegularFile).collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processors);
        Path pathToRoot = Paths.get(".");
        for (Path p : descendants(pathToRoot)) {
            executor.execute(() -> process(p));
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println(map);
    }
}
