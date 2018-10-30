package concurrent.atomic_demo.unsafe_demo;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * AtomicInteger를 보다가 Unsafe라는 클래스를 보게 되었다. 이 클래스의 제대로된 구현을 알 수 없었다.
 * 그래서 좀 더 자세히 찾아보려고 한다.
 * 출처들
 * 개요:
 * 		1. http://mishadoff.com/blog/java-magic-part-4-sun-dot-misc-dot-unsafe/
 * 		2. https://www.javaworld.com/article/2952869/java-platform/understanding-sun-misc-unsafe.html
 * 		7. https://dzone.com/articles/understanding-sunmiscunsafe
 * Oracle Java 9 에서 Unsafe를 삭제하려 하지만 문제가 많을 거라는 내용들 : (misc패키지에서 publish API로 변경하려는 것 같음)
 * 		3. https://blog.dripstat.com/removal-of-sun-misc-unsafe-a-disaster-in-the-making/
 * 		4. https://www.javaworld.com/article/2952639/java-se/java-devs-abhor-oracles-plan-to-kill-private-api.html
 * Microsoft CLR 설명:
 * 		5. https://docs.microsoft.com/de-de/previous-versions/visualstudio/visual-studio-2008/chfa2zb8(v=vs.90)
 * 오라클에서는 sun 패키지를 쓰지 말라고 한다.(SUN이 싫은 듯 하다)
 * 		6. https://www.oracle.com/technetwork/java/faq-sun-packages-142232.html
 * 도큐먼트:
 * 		8. http://www.docjar.com/html/api/sun/misc/Unsafe.java.html
 * 싱글턴 위키:
 * 		9. https://en.wikipedia.org/wiki/Singleton_pattern
 * 문장 뒤에 (숫자)는 출처를 말함. EX) ....(1) <- 출처 1번의 내용을 가져온 것.
 * @author yuhnam
 *
 */
public class UnsafeDemo1 {
/**
 *	자바는 꽤나 안전한 언어이다. 많은 프로그래머들이 하는 많은 메모리 실수들을 예방해준다.
 *	하지만 여기서 일부러 실수를 만드는 방법이 있다. 바로 Unsafe.class 를 사용해서이다.(1)
 *
 *	JVM의 가장 큰 경쟁자응 Microsoft의 CLR이라 알 수 있다. CLR은 unsafe(5)를 제공하여 low-level 프로그래밍의 진입로를 만들어줬다.
 *	이것은 JVM에서는 꽤나 힘든 일이다. 만약 자바에서 그러한 고급 기능을 사용하려면 JNI를 사용하려고 할 것인데 이 말은 C언어를 해야 하며
 *	이 말은 특정 플랫폼에 갇혀버린다는 말이다. 하지만 java.misc.Unsafe를 이용하면 Java API를 이용하여 low-level 프로그래밍을 할 수 있게 해준다.
 *	물론 오라클에서는 쓰지 말라고 한다(6). 그럼에도 몇몇 어플은 sun.misc.Unsafe에 의존한다.(7)
 *
 *	Getting hold of an instance of sun.misc.Unsafe Unsafe [인스턴스 이해하기]
 *	sun.misc.Unsafe 클래스는 코어 자바 클래스들용으로 만들어진 녀석이다. 그렇게 때문에
 *	생성자를 private으로 하고 [private Unsafe() {}], 싱글턴 인스턴스도 private이다. [private static final Unsafe theUnsafe = new Unsafe();]
 *	이 인스턴스를 위한 public getter는 public에서 사용되는 것을 막기 위한 시큐리티 체크를 실시한다.(7, 8)
 *	====
 *	public static Unsafe getUnsafe() {
 *    Class cc = sun.reflect.Reflection.getCallerClass(2);
 *    if (cc.getClassLoader() != null)
 *      throw new SecurityException("Unsafe");
 *    return theUnSafe;
 *  }
 *  ====
 *  위 메소드는 현재 스레드의 메소드 스택을 확인해서(메소드는 실행될 때 내부적으로 stack이 생성된다) Class라는 녀석을 가져온다.
 * 	이런 메소드 스택을 확인하는 방법은 sun.reflection.Reflection인데, 주어진 숫자 만큼 call-stack-frame 밑으로 내려가서
 *  이 메소드의 클래스를 리턴한다. 이런 시큐리티 체크는 변경될 수 있다고 한다[이 블로그는 꽤 오래된 녀석이다]. 첫번째 클래스(index 0)은 명백히 Reflection class 자기 자신이다.
 *  두번째(index 1)은 Unsafe 클래스이다. 그러므로 index 2는 당신의 application class가 되는 것이다.
 *  - 왜냐하면 Unsafe#getUsafe() 처럼 실행을 한 것이 분명하기 때문에 메소드 내에 콜스택에 가장 위에 있는 것의 메소드 스택은
 *  1. Reflection(getCallerClass메소드 실행) 2. Unsafe(getUnsafe메소드 실행) 3. Unsafe를 실행하는 나의 어플 내에 있는 메소드
 *
 *  이제 3번에서 classLoader를 실행시킨다. 소스상에서는 null이어야만 한다. null 레퍼런스는 HotSpot virtual machine 위에 bootstrap class loader를 가리킨다.
 *  (이 내용은 {@link Class#getClassLoader()}에 문서화 되어 있다. "some implementations may use null to represent the bootstrap class loader".)
 *  코어 자바 클래스가 아닌 녀석들은 이 클래스로더로 로딩되지 않는다. 그러므로 절대 메소드로 직접 호출할 수 없고 SecurityException 예외나 받을 것이다.
 *  허나 기술적으로는 VM에게 강제적으로 내 어플리케이션 클래스를 bootstrap class loader로 로딩하도록 할 수 있다. -Xbootclasspath를 붙이면 된다고 한다.
 *  하지만 이런 것은 코드 외에 다른 세팅이 필요할 수도 있다고 한다. 이 말은 되도록이면 피해야할 방법이라고 볼 수 있다.(7)
 *
 *  그러므로 아래와 같은 테스트 케이스는 성공한다.
 *  ====
 *  @Test(expected = SecurityException.class)
 *  public void testSingletonGetter() throws Exception {
 *    Unsafe.getUnsafe();
 *  }
 *  ====
 *
 */
    public void getUnsafeException() {
    Unsafe.getUnsafe();
}
    /**  하지만 트릭은 언제나 존재한다. security check이 아주 대충 디자인 된 것으로 보인다. 이것은 singleton anti-pattern(9참고)이다.
     *  reflection의 사용을 막지 않았기 때문에, class의 private member들을 확인한다음에 가져오면 된다.
     *  Unsafe.class의 소스코드 상에서는, singleton insntance가 private static 필드로 만들어진 것을 볼 수 있다. 이름은 theUnsafe다.
     *  안타깝게도, 하지만 이건 오로지 HotSpot virtual machine에서만 유효한다. (다른 곳에서는 다르게 가져와야 한다는 뜻같다, 안될 수도 있고)
     *  하지만 우리에겐, 다른 virtual machine 구현을 사용한다. Android에서는 Unsafe클래스가 THE_ONE이라는 이름으로 불린다.
     *  이런 구현의 실수는 나중에 Unsafe 인스턴스를 가져올 때, 호환성을 제공하기 힘들다.
     *  하지만!, 이 클래스를 사용하는 것을 자체를 걱정하는 게 먼저지 이런 호환성 문제는 아직 걱정할 게 아니다.
     *  HotSpot버전으로 일단 가져와보자.(7)
     *  주석===
     *  실제로 UnSafe를 가져오려고 하니 문제가 생긴다. 아예 가져올 수 없게 되어 있다. Unsafe를 아예 컴파일러에서 없는 취급을 한다.
     *  일단 패키지만을 추가해보자. 안된다. configure problem severity가 뜬다. 여기서 forbidden reference를 warning으로 바꿔주자.
     */
    public void getUnsafe() throws Exception{
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        System.out.println(unsafe);
    }
    /**
     * 혹은 다른 방식으로 가져올 수도 있다. 아래 방식으로 가져오는 것이 더 나을 것 같기도 하다. 바로 새로운 Instance를 가져오는 것이다.
     * 이렇게 하면 안드로이드에서도 가져올 수 있다.
     * 대신 그 값은 singleton이 아닌 것인데 상관없다. 단지 조그만한 heap 공간을 사용할 뿐이다. (7)
     */
    public Unsafe getUnsafeInstance() throws Exception {
        Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        Unsafe unsafe = unsafeConstructor.newInstance();
        System.out.println(unsafe);
        return unsafe;
    }

    /**
     * 이제 어디서 사용하는지 보자. UnsafeTest2를 보자.
     */

    public static void main(String[] args) throws Exception {
        UnsafeDemo1 unsafeTest = new UnsafeDemo1();
        // unsafeTest.getUnsafeException();
        // unsafeTest.getUnsafe();
        unsafeTest.getUnsafeInstance();
    }
}
