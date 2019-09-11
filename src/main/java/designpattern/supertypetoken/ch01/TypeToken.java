package designpattern.nullobject.supertypetoken.ch01;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TypeToken {
	static class Generic<T> {
		T value;
		void set(T t) {}
		T get() { return null; }
	}
	
	static class TypesafeMap {
		Map<Class<?>, Object> map = new HashMap<>();
		void put (Class<?> clazz, Object value) {
			map.put(clazz, value);
		}
		<T> T get (Class<T> clazz) {
			return clazz.cast(map.get(clazz));
		}
	}
//	안전한메소드 
//	<T> T create(Class<T> clazz) throws InstantiationException, IllegalAccessException {
//		return clazz.newInstance();
//	}
	public static void main(String[] args) {
		TypesafeMap m = new TypesafeMap();
		m.put(Integer.class, 1);
		m.put(String.class, "String");
		m.put(List.class, Arrays.asList(1, 2, 3)); // List<Integer>
	
		System.out.println(m.get(Integer.class));
		System.out.println(m.get(String.class));
		System.out.println(m.get(Array.class)); // casting이 명시적으로 쓰지 않고 안전하게 사용할 수 있었다.
		// 이렇게 클래스 정보를 넘겨서 타입안전성을 꽤하는 것을 타입토큰이라고 한다.
	}
}
