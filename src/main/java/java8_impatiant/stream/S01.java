package java8_impatiant.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class S01 {
    /**
     * 1. 스트림은 요소를 저장하지 않는다. 요소는 스트림을 지원하는 컬렉션에 저장되거나 필요할 때 생성된다.
     * 2. 스트림 연산은 원본을 변경하지 않는다.
     * 3. 스트림은 연산은 가능하면 지연시켜준다.
     *
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // 예를들어 책에 긴 단어가 얼마나 나오는지 모두 센다고 해보자. 우선 긴 단어들을 리스트에 집어넣는다.
        String contents = new String(Files.readAllBytes(
                Paths.get("src/main/alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        // 단어로 분리한다. (비문자를 구분자로 사용한다.)

        int count = 0;
        for (String w : words) {
            if (w.length() > 12) count++;
        }
        System.out.println(count);
        // 스트림을 이용하면 이렇게 사용할 수 있다.
        long count2 = words.stream()
                .filter(w -> w.length() > 12)
                .count();
        System.out.println(count2);
        // 단어 하나만 바꾸면 병렬화 할 수 있다.
        long count3 = words.stream()
                .filter(w -> w.length() > 12)
                .count();
        System.out.println(count3);
    }

}
