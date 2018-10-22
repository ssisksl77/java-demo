package java8_impatiant.compilerapi;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * 14.1.3 메모리에서 소스 파일 읽기
 * 즉성에서 소스 코드를 생성하면 메몰에서 컴파일할 수 있다. 디스크에 파일로 저장하지 않아도 컴파일할 수 있다.
 * 다음 클래스를 사용해서 코드를 담아둔다.
 *
 * 그런 다음 클래스에 해당하는 코드를 생성해서 StringSource 객체의 리스트를 컴파일러에 전달한다.
 *
 * String pointCode = ...;
 * String rectangleCode = ...;
 * List<StringSource> sources = Arrays.asList(new StringSource("Point", pointCode), new StringSource("Rectangle", rectangleCode));
 * task = compiler.getTask(null, null, null, null, null, sources);
 */
public class StringSource extends SimpleJavaFileObject {
    private String code;

    StringSource(String name, String code) {
        super(URI.create("string:///" + name.replace('.','/') + ".java"),
                Kind.SOURCE);
        this.code = code;
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}