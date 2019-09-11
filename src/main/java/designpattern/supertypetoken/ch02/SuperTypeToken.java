package designpattern.supertypetoken.ch02;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class SuperTypeToken {
	static class Sup<T> {
		T value;
	}
	
	// NESTED STATIC
	// reflection시 런타임시 접근할 수 있도록 바이트코드에 제네릭이 남아있음.
	static class Sub extends Sup<List<String>> {  // 여기 이 String은 안 없어짐.
		
	}
	
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {

		
		Sup<String> s = new Sup<>();
		// --------------
		// 런타임에서는 Sup<String>이 아니라 Sup<Object>가 됨...
		// 제네릭이 사라진다. 하지만 제네릭을 살릴 수 있는 방법이 있다.
		System.out.println(s.getClass().getDeclaredField("value").getType());
		
		Sub b = new Sub();
		Type t = b.getClass().getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) t;
		System.out.println(pType.getActualTypeArguments()[0]); // getActualTypeArguments 실제타입이 머냐.
		
		
		// LOCAL CLASS : 스코프를 메서드로 제한.
		class Sub2 extends Sup<List<String>> {
			
		}
		
		Sub2 b2 = new Sub2();
		Type t2 = b2.getClass().getGenericSuperclass();
		ParameterizedType pType2 = (ParameterizedType) t2;
		System.out.println(pType2.getActualTypeArguments()[0]); // getActualTypeArguments 실제타입이 머냐.
		
		
		// 익명클래스 내부적으로 임의의 클래스를 만듬.(상속한 녀석의 타입을 받아버릴 수 있다!)
		Type t3 = (new Sup<List<Integer>>() {}).getClass().getGenericSuperclass();
		ParameterizedType ptype3 = (ParameterizedType) t3;
		System.out.println(ptype3.getActualTypeArguments()[0]);
	}
}
