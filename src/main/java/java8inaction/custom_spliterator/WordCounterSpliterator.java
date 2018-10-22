package java8inaction.custom_spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++)); // 현재 문자 소비
        return currentChar < string.length(); // 소비할 문자가 남아있으면 true
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null; // 파싱할 문자열을 순자 처리할 수 있을 만큼 충분히 작아졌음을 알리는 null을 반환
        }
        for (int splitPos = currentSize / 2 + currentSize; splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) { // 공백인지? 아니면 분할위치를 뒤로 이동
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(string.substring(currentChar, splitPos)); // 처음부터 분할위치까지 문자열을 파싱할 새로운 객체 생성
                currentChar = splitPos; // 이 WordCounterSpliterator의 시작위치를 분할위치로 설정.
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
