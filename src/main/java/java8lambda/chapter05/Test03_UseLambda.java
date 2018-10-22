package java8lambda.chapter05;

import java8lambda.chapter05.dto.FileWriterARM;
import java8lambda.chapter05.dto.FileWriterEAM;

import java.io.IOException;

/**
 * ARM은 올바른 방법이지만 아주 효과적이라고 말하기는 어렵다. 우리가 만든 클래스를 사용 하는 어떤 사람도 그것이
 * AutoCloseable을 구현한다는 것을 알아야 하고 try-with-resource 구조를 사용한다는 점을 기억해야 한다.
 * 우리가 설계한 API를 프로그래머가 컴파일러의 도움으로 사용한다면 이는 올바른 방법이다.
 *
 * 람다 표현식을 사용하면 상당히 쉽게 할 수 있다.
 *
 */
public class Test03_UseLambda {
    public static void main(String[] args) throws IOException {
        FileWriterEAM.use("eam.txt", writerEAM -> writerEAM.writeStuff("swee~~t"));
    }
}
