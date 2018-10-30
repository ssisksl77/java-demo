package concurrent.atomic_demo.unsafe_demo;

import sun.misc.Unsafe;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Create an Instance of a Class Without Calling a Constructor 생성자 없이 인스턴스 생성하기
 * 지금까지 생성하는데 아주 힘들었다. 이제 Unsafe.class를 활용해볼 때가 되었다.
 * 당신은 class의 생성자를 호출하지 않고 어떤 클래스던 인스턴스를 생성할 수 있는 능력을 가지게 되었다.
 *
 * 글쓴이의 경험을 들어보자.
 * "단순히 나는 어떤 클래스이 모든 메소드 호출을 다른 진짜 인스턴스에 위임을 하고 싶은데
 *  클래스 전부를 우회(proxy)해야만 했다. 그래서 생성자가 더러워 졌었다.
 *
 * Subclass를 만드는 것은 아주 단순했다. 만약 클래스가 어떤 interface를 대표한다면 proxy를 만드는 것은 쉽다.
 * 하지만 꽤나 느린 이 생성자 때문에 나는 막혔다.
 * 이때, Unsafe.class를 이용하여 해냈다.
 *
 */
public class UnsafeDemo2 {
    /**
     * 여기 생성자가 느린 클래스를 생성한다.
     * @author yuhnam
     */
    static class ClassWithExpensiveConstructor {
        private final int value;
        private ClassWithExpensiveConstructor() {
            value = doExpensiveLookup();
        }
        private int doExpensiveLookup() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }
        public int getValue() {
            return value;
        }
    }

    /**
     * Unsafe를 이용하여 ClassWithExpensiveConstructor 인스턴스를 생성한다.
     * 위의 생성자를 호출하지 않고, 단순히 인스턴스를 힙에 직접 할당한다!!!
     *
     * 원래라면 ClassWithExpensiveConstructor를 확장해서 모든 메소드를 오버라이드 해서 사용할 것이다.
     * 그렇게 했다면 인스턴스를 생성할 때, 2초가 걸릴 것이다. 하지만 Unsafe로 가져오니 바로 가져오고 있다.
     *
     * 사실 웃긴 것이 이렇게 하면 위험하지만 실제로 메소드들은 이녀석이 하는 것이 아닌 위임만 할 것이었으니, 괜찮을 것이다.
     *
     * 0이 되는 이유는 getValue() 값이 int값인데 int의 디폴트 값은 0이다.
     * 그리고 이게 접근이 안될 때 쯤에는 알아서 GC가 가져갈 것이다.
     * @throws Exception
     */
    public void testObjectCreation() throws Exception {
        Unsafe unsafe = new UnsafeDemo1().getUnsafeInstance();
        ClassWithExpensiveConstructor instance =
                (ClassWithExpensiveConstructor) unsafe.allocateInstance(ClassWithExpensiveConstructor.class);
        System.out.println(instance.getValue() == 0);
    }

    /**
     * 자바 런타임은 자기자신은 객체를 생성할 때, 생성자를 사용하지 않는다. 예를들어, 역직렬화를 위해 객체를 만들 때 그러하다. (직렬화된 객체를 생성할 때)
     * 그러므로, ReflectionFactory는 아까 예제에서 더 나아ㅏ가서 개별적인 객체 생성에 더 접근할 권한을 준다.
     * 주어진 소스코드가 실행되지 않아서 임의로 변경하였다.
     *
     * 여기서 알아야 할 점은 ReflectionFactory.class는 오직 RuntimePermission이 필요하다 (called reflectionFacotryAcess)
     * 조건을 충족하면 ReflectionFactory의 싱글턴 인스턴스를 받을 수 있다. reflection이 여기선 필요없다.
     *
     * 그렇게 얻게된 ReflectionFactory 생성자를 만들어낼 수 있다. 그리고 주어진 타입의 생성자가 된다.
     * newConstructorForSerialization의 첫번째 매개변수 Class타입, 두번째는 Constructor타입이다. 이 컨스트럭터 타입은 무엇이든 가능하다.
     */
    @SuppressWarnings("unchecked")
    public void testReflectionFactory() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
        Constructor<ClassWithExpensiveConstructor> silentConstructor =
                (Constructor<ClassWithExpensiveConstructor>) rf.newConstructorForSerialization(ClassWithExpensiveConstructor.class, Object.class.getDeclaredConstructor());

        silentConstructor.setAccessible(true);
        System.out.println("testReflectionFacotry : " + silentConstructor.newInstance().getValue());
    }


    /**
     * otherClass라는 녀석을 임의로 만들어서 넣어보자.
     */
    static class OtherClass {
        private final int value;
        private final int unknownValue;

        public OtherClass() {
            System.out.println("test");
            this.value = 10;
            this.unknownValue = 20;
        }
    }

    /**
     * 여기서는 Object.class.getDeclaredConstructor가 아닌 OtherClass를 사용하였다.
     */
    public void testStrangeReflectionFactory() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
        Constructor<ClassWithExpensiveConstructor> silentConstructor = (Constructor<ClassWithExpensiveConstructor>) rf
                        .newConstructorForSerialization(ClassWithExpensiveConstructor.class,
                                                        OtherClass.class.getDeclaredConstructor());
        silentConstructor.setAccessible(true);
        ClassWithExpensiveConstructor instance = silentConstructor.newInstance();
        System.out.println("testStrangeReflectionFactory: " + instance.getValue());
        System.out.println(instance.getClass());  // ClassWithExpensiveConstructor
        System.out.println(instance.getClass().getSuperclass()); // Object
    }

    /**
     * 위 예제에서 가장 마법과 같은 것은!
     * value값이 설정되었다는 것이다. 확실히 ClassWithExpensiveConstructor와 OtherClass가 같은 필드의 네임으로 정의되어 있긴하다.
     * 존재 하지 않는 필드(unknownValue)는 무시된다.
     * OtherClass는 현재 생성된 클래스의 생성 인스턴스 타입 계층에 관여하지 않는다.
     * OtherClass의 생성자는 단순하게 "serialized" 타입을 위해 빌려쓴 것뿐이다.
     *
     * Unsafe#defineClass, Unsafe#defineAnonymousClass, Unsafe#ensureClassInitialized 같은 다른 메소드들도 있는데,
     * 이런 비슷한 것들이 ClassLoader API에 정의되어 있다.
     */

    public static void main(String[] args) throws Exception {
        UnsafeDemo2 unsafe = new UnsafeDemo2();
        //unsafe.testObjectCreation();
        //unsafe.testReflectionFactory();
        unsafe.testStrangeReflectionFactory();
    }
}
