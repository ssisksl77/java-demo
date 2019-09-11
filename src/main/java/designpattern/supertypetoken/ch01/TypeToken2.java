package designpattern.supertypetoken.ch01;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 타입토큰의 한
public class TypeToken2 {
	static class TypesafeMap {
		Map<Class<?>, Object> map = new HashMap<>();
		void put (Class<?> clazz, Object value) {
			map.put(clazz, value);
		}
		<T> T get (Class<T> clazz) {
			return clazz.cast(map.get(clazz));
		}
	}
	
	public static void main(String[] args) {
		TypesafeMap m = new TypesafeMap();
		m.put(List.class, Arrays.asList(1, 2, 3));  // 1. List<Integer>.class 가 안된다. !!!!
		m.put(List.class, Arrays.asList("a","b","c"));  // 2. super type token by Neal Gafter
	
		System.out.println(m.get(Integer.class));
		System.out.println(m.get(String.class));
		System.out.println(m.get(Array.class)); // casting이 명시적으로 쓰지 않고 안전하게 사용할 수 있었다.
		// 이렇게 클래스 정보를 넘겨서 타입안전성을 꽤하는 것을 타입토큰이라고 한다.
	}
}
