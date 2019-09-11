package designpattern.supertypetoken.ch02;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 드디어 닐게프터가 가져온기술 
public class SuperTypeToken2 {
	static class TypeReference<T> {
		Type type;
		
		public TypeReference() {
			Type stype = getClass().getGenericSuperclass();
			if (stype instanceof ParameterizedType) {
				this.type = ((ParameterizedType)stype).getActualTypeArguments()[0];
			} else {
				throw new RuntimeException();
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass().getSuperclass() != obj.getClass().getSuperclass())
				return false;
			TypeReference other = (TypeReference) obj;
			if (type == null) {
				if (other.type != null)
					return false;
			} else if (!type.equals(other.type))
				return false;
			return true;
		}
		
		
	}
	
	static class TypesafeMap {
		Map<TypeReference<?>, Object> map = new HashMap<>();
		
		// T로 key값과 value를 묶었기 때문에 다른타입이 들어갈 수 없다.
		<T> void put(TypeReference<T> tr, T value) {
			map.put(tr, value);
		}
		
		// 안전하게 캐스팅하려면, tr.type의 클래스로 캐스팅을 하면 된다.
		// 우리는 tr.type을 키로 그 타입에 해당하는 값을 넣었다고 가정한다.
		@SuppressWarnings("unchecked")
		<T> T get(TypeReference<T> tr) {
			// TypeReference<List<String>> 같은 경우는 수정이 필요하다.
			// return ((Class<T>)tr.type).cast(map.get(tr)); 
			if (tr.type instanceof Class<?>)
				return ((Class<T>)tr.type).cast(map.get(tr));
			else
				return ((Class<T>)((ParameterizedType)tr.type).getRawType()).cast(map.get(tr)); // TR<List<String>>
		}

	}
	public static void main(String[] args) {
		TypeReference t = new TypeReference<String>() {}; // {}을 넣어야돌아감 빼면 안댐
		System.out.println(t.type);
		
		// test new TypesafeMap
		TypesafeMap m = new TypesafeMap();
		m.put(new TypeReference<Integer> () {}, 1);
		m.put(new TypeReference<String> () {}, "sr");
		m.put(new TypeReference<List<Integer>>() {}, Arrays.asList(1,2,3));
		// m.put(new TypeReference<List<String>>() {}, Arrays.asList(1,2,3));
		m.put(new TypeReference<List<String>>() {}, Arrays.asList("A","B","C"));
		
		
		System.out.println(m.get(new TypeReference<Integer>() {}));
		System.out.println(m.get(new TypeReference<String>() {}));
		System.out.println(m.get(new TypeReference<List<Integer>>() {}));
		System.out.println(m.get(new TypeReference<List<String>>() {}));
		// 이렇게 복잡한걸 항상 구현해야 하나? 
		// 스프링에 있다.
	}
}
