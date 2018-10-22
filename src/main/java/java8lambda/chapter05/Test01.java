package java8lambda.chapter05;

import java8lambda.chapter05.dto.FileWriterExample;

import java.io.IOException;

public class Test01 {

    /**
     * peekaboo.txt 파일이 생성되긴 했어도 비어 있다는 것을 알 수 있다. 파이널라이저가 실행되지 않았다.
     * JVM은 충분한 메모리가 있기 때문에 파이널라이저가 필요 없다고 생각한 것이다. 결국 파일은 종료되지 않았고
     * 우리가 작성한 내용은 메모리에서 파일로 플러쉬 되지 않았다.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final FileWriterExample writerExample =
                new FileWriterExample("peekaboo.txt");
        writerExample.writeStuff("peek-a-boo");

    }


}
