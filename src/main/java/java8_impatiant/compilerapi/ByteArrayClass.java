package java8_impatiant.compilerapi;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * 메모리에 바이트 코드 작성하기
 * 즉성에서 클래스를 컴파일할 때는 클래스 파일을 디스크에 저장할 필요가 없다. 메모리에 저장하고 곧바로 로드하면 된다.
 * 다음은 바이트를 담아둘 클래스다.
 */
public class ByteArrayClass extends SimpleJavaFileObject {
    private ByteArrayOutputStream out;

    ByteArrayClass(String name) {
        super(URI.create("bytes:///" + name.replace('.','/') + ".class"),
                Kind.CLASS);
    }

    public byte[] getCode() {
        return out.toByteArray();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        // TODO Auto-generated method stub
        out = new ByteArrayOutputStream();
        return out;
    }
}
