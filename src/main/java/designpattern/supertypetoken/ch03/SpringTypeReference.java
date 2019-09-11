package designpattern.supertypetoken.ch03;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;

public class SpringTypeReference {
	public static void main(String[] args) {
		// 왜 {}를 붙여야 하는 것인가?? 왜 그래야 하는가.
		// 슈퍼타임토큰방식은 무조건 {}(바디)를 붙여야 한다.
		// 익명클래스의 인스턴스를 생성하여, 익명클래스가 상속하고있는 슈퍼클래스의 제네릭 타입파라미터정보를 가져오기 위함이다.
		ParameterizedTypeReference typeRef = new ParameterizedTypeReference<List<Map<Set<Integer>, String>>>() {};
		
		System.out.println(typeRef.getType()); // Spring 3.2 
	}
}
