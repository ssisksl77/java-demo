package java8_impatiant.compilerapi;

import javax.tools.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 컴파일러 호출하기
 * JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
 * OutputStream outStream = ...;
 * OutputStream errStream = ...;
 * int result = compiler.run(null, outStream, errStream, "-sourcepath", "src", "Test.java");
 *
 * result가 0이면 성공이다.
 *
 * 컴파일 태스크 실행하기
 * CompliationTask 객체로 컴파일 과정을 더 세밀하게 제어할 수 있다. 문자열로 소스를 작성할 때, 클래스 파일을 메모리로 캡쳐할 때, 오류나 경고 메시지를
 * 처리할 때 유용하다.
 *
 * CompilationTask를 얻으려면 앞에서 본 것처럼 compiler 객체를 얻어야 한다.
 * JavaCompiler.CompilationTask task = compiler.getTask(
 *   errorWriter, // null 이면 System.err를 사용한다.
 *   fileManager, // null 이면 표준 파일 관리자를 사용한다.
 *   diagnostics, // null 이면 System.err를 사용한다.
 *   options, // 옵션이 없으면 null
 *   classes, // 애너테이션 처리용, 없으면 null
 *   sources);
 *
 *   마지막 인자 3 개는 Iterable 인스턴스가 다음과 같이 지정할 수 있다.
 *   Iterable<String> options = Arrays.asList("-d", "bin");
 */
public class CompilerDemo {
    public static void main(String[] args) throws ReflectiveOperationException {
        String pointCode = makeClass("Point", "int", "x", "int", "y");
        String rectangleCode = makeClass("Rectangle", "Point", "topLeft", "int",
                "width", "int", "height");
        System.out.println(rectangleCode);

        List<StringSource> sources = Arrays.asList(
                new StringSource("Point", pointCode),
                new StringSource("Rectangle", rectangleCode));
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();

        List<ByteArrayClass> classes = new ArrayList<>();
        StandardJavaFileManager stdFileManager = compiler
                .getStandardFileManager(null, null, null);
        JavaFileManager fileManager = new ForwardingJavaFileManager<JavaFileManager>(
                stdFileManager) {
            @Override
            public JavaFileObject getJavaFileForOutput(Location location,
                                                       String className, JavaFileObject.Kind kind, FileObject sibling)
                    throws IOException {
                if (kind == JavaFileObject.Kind.CLASS) {
                    ByteArrayClass outfile = new ByteArrayClass(className);
                    classes.add(outfile);
                    return outfile;
                } else
                    return super.getJavaFileForOutput(location, className,
                            kind, sibling);
            }
        };
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
                collector, null, null, sources);
        Boolean result = task.call();
        for (Diagnostic<? extends JavaFileObject> d : collector.getDiagnostics()) {
            System.out.println(d);
        }
        System.out.println(result);
        ByteArrayClassLoader loader = new ByteArrayClassLoader(classes);
        Class<?> cl = Class.forName("Rectangle", true, loader);
        System.out.println(Arrays.toString(cl.getDeclaredFields()));
        System.out.println(Arrays.toString(cl.getDeclaredMethods()));
    }

    public static String makeClass(String name, String... typesAndFields) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("public class %s {\n", name));
        for (int i = 0; i < typesAndFields.length; i += 2) {
            String type = typesAndFields[i];
            String field = typesAndFields[i + 1];
            String ufield = field.substring(0, 1).toUpperCase()
                    + field.substring(1);
            result.append(String.format("    private %s %s;\n", type, field));
            result.append(String.format(
                    "    public %s get%s() { return %s; }\n", type, ufield,
                    field));
            result.append(String.format(
                    "    public void set%2$s(%1$s %3$s) { this.%3$s = %3$s; }\n", type, ufield,
                    field));
        }
        result.append("}\n");
        return result.toString();
    }
}