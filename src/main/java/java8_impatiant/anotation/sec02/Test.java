package java8_impatiant.anotation.sec02;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 인터페이스 만들어 보기
 * @Target 애너테이션 요소 타입
 * ANNOTATION_TYPE, PACKAGE, TYPE (클래스, 인터페이스), METHOD, CONSTRUCTOR, FIELD, PARAMETER, LOCAL_VARIABLE,
 * TYPE_PARAMETER, TYPE_USE(타입 사용)
 *
 * @Retention 메타애너테이션은 애너테이션에 접근할 수 있는 위치를 지정한다.
 * 1. RetentionPolicy.SOURCE : 소스 핸들러에는 보이지만 파일에는 포함되지 않는다.
 * 2. RetentionPolicy.CLASS : 클래스 파일에 포함되지만 가상 머신은 해당 애너테이션을 로드하지 않는다. (기본값)
 * 3. RetentionPolicy.RUNTIME : 애너테이션을 실행 시간에 이용할 수 있고, 리플렉션 API를 통해 접근할 수 있다.
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    long timeout() default 0L;
}