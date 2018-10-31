package concurrent.atomic_demo.unsafe_demo;

/**
 * Native Memory Allocation
 * 혹시 만약 배열의 크기를 Integer.MAX_VALUE 값보다 더 크게 생성할 수 있을까?
 * 그런 경우는 정상적인 경우가 아니다. 하지만 만약 그런 경우가 필요하다고 해보자.
 * native memory 할당을 하여 생성을 할 수 있다.
 *
 * Native memory 할당은 Java NIO package에서 제공하는 direct ByteBuffer을 예로 들 수 있다.
 * heap memory와 다르게, native memory는 heap 공간에 있지 않다. 그리고 다른 프로세스와 통신할 때 비독점적으로 쓰일 수 있다.
 * 결과적으로, Java의 힙 공간은 native 공간과 경쟁한다.
 * JVM에 더 많은 메모리를 넣으면, 적은 native memory 공간이 남는다.
 */
public class UnsafeDemo3 {
}
