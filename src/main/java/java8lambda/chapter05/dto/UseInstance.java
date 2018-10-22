package java8lambda.chapter05.dto;

public interface UseInstance<T, X extends Throwable> {
    void accept(T instance) throws X;
}
