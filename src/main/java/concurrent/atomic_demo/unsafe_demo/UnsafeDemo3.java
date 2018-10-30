package concurrent.atomic_demo.unsafe_demo;

/**
 * Native Memory Allocation
 * 혹시 만약 배열의 크기를 Integer.MAX_VALUE 값보다 더 크게 생성할 수 있을까?
 * 그런 경우는 정상적인 경우가 아니다. 하지만 만약 그런 경우가 필요하다고 해보자.
 * native memory 할당을 하여 생성을 할 수 있다.
 *
 * Native memory 할당은 Java NIO package에서 제공하는 direct ByteBuffer을 예로 들 수 있다.
 * heap memory와 다르게, native memory는 heap 공간에 있지 않다. 그리고 [
 *
 Native memory allocation is used by for example direct byte buffers that are offered in Java's NIO packages.
 Other than heap memory, native memory is not part of the heap area and [can be used non-exclusively for example for communicating with other processes.
 As a result, Java's heap space is in competition with the native space: the more memory you assign to the JVM, the less native memory is left.
 */
public class UnsafeDemo3 {
}
