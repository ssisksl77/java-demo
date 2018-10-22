package java8lambda.chapter05;

import java8lambda.chapter05.dto.FileWriterARM;
import java8lambda.chapter05.dto.FileWriterExample;

import java.io.IOException;

/**
 * close() 메소드를 항상 호출해서 명시적으로 닫자.
 * 아직 갈길이 멀다. 더 나은 방법은 없는가.
 */
public class Test02 {
    /**
     * 예외가 생기면 close가 안될 수 있다.
     * @throws IOException
     */
    void cleanUp1() throws IOException {
        final FileWriterExample writer = new FileWriterExample("peekaboo.txt");
        writer.writeStuff("peek-a-boo");
        writer.close();
    }

    /**
     * 이렇게 하면 예외가 발생해도 클린업이 일어난다는 것을 보장한다.
     * 자바7에서 자동 리소스 관리 (ARM : automatic resource management) 기능을 이용하면 더 좋다.
     * @throws IOException
     */
    void cleanUp2() throws IOException {
        final FileWriterExample writer = new FileWriterExample("peekaboo.txt");
        try {
            writer.writeStuff("peek-a-boo");
        } finally {
            writer.close();
        }
    }

    void cleanUp3() throws Exception {
        try(final FileWriterARM writerARM = new FileWriterARM("peekaboo.txt")) {
            writerARM.writeStuff("peek-a-boo");
            System.out.println("done with the resource...");
        }
    }
    public static void main(String[] args) throws IOException {

    }
}
