package java8inaction.custom_spliterator;

import java.util.Spliterator;
import java.util.stream.Stream;

public class WordCounter {
    private final int counter;
    private final   boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    /**
     * 공백문자를 만나면 지금까지 탐색한 문자를 단어로 간주하여(공백 문자는 제외) 단어 개수를 증가시킨다.
     * @param c
     * @return
     */
    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ?
                    this :
                    new WordCounter(counter, true);
        } else {
            return lastSpace ?
                    new WordCounter(counter + 1, false) :
                    this;
        }
    }

    public  WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, // 두 WordCounter의 카운트를 더한다.
                                wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
    public static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0 , true),
                                                WordCounter::accumulate,
                                                WordCounter::combine);
        return wordCounter.getCounter();
    }
}
