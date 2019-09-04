package designpattern.nullobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Null Object Pattern
 * 
 * 어떤 객체의 기본값을 담당하는 객체(1)
 * 널 객체는 참조하는 값이 없거나 중립적인(빈 값을 의미하는) 행위가 정의된 객체
 * 
 * 1. 동기
 *  Java, C# 등은 null객체를 참조로 사용할 수 있다. 이런 참조의 존재는 객체의 메소드를 실행하기 전에 반드시 null체크를 해야한다. (2)
 * 
 * 2. 소개
 *  객체의 부재를 null참조로 하지않고, 비어있음을 나타내는 객체를 구현하여 사용한다.
 *  이러한 접근의 장점은 객체의 부재를 디폴트 구현으로 넘겨 예상가능하게(그냥 null이 넘어오는 것보다) 하며 사이트이팩트가 없다.
 *  																										(하는일이 없으니까)
 *  널객체를 리턴하면서(예, 빈 리스트), 값이 null인지 아닌지 뿐만아니라, 실제 리스트인지 아닌지 확인할 필요도 없다. 
 *  그냥 쓰던데로 포문을 돌리거나 map을 쓰면 된다. (그러면 저절로 아무것도 안할 것이다)
 *  물론, 널객체를 확인해서 다른 일을 하도록 할 수도 있다.
 *  널 객체 패턴은 또한 테스트를 위한 스텁으로 사용될 수 있다. 만약에 디비 같은 특정 기술이 테스트에 불가할 때 사용한다. (2)
 *  
 * 3. 구현방법 
 *  인터페이스를 생성, 구현을 할 때, 빈 값의 객체 또한 구현한다. (3)
 *  추상클래스를 생성, 빈 값의 객체 또한 구현한다 (4)
 *  
 * 4. 레퍼런스
 * (1) https://en.wikipedia.org/wiki/Behavioral_pattern
 * (2) https://en.wikipedia.org/wiki/Null_object_pattern
 * (3) https://dzone.com/articles/null-object-pattern-in-java
 * (4) https://www.tutorialspoint.com/design_pattern/null_object_pattern.html
 * 
 * @author yuhnam
 *
 */
public class NullObjectMain {
	public static void main(String[] args) {
		List<Address> addresses = getAddresses();
		
		for (Address address : addresses) {
			System.out.println(address.getAddress());
		}
	}

	private static List<Address> getAddresses() {
		// 테스트를 위한 리스트 맵을 제시
		List<Map<String, String>> addressMap = new ArrayList<>();
		addressMap.add(new HashMap<String, String>() {{ 
							this.put("postcode", "우편번호"); 
							this.put("address1", "주소1"); 
							this.put("address2", "주소2");}});
		addressMap.add(null);
		addressMap.add(new HashMap<>());
		
		// 해당 값을 Address 객체로 변경
		List<Address> addressList = new ArrayList<>();
		for (Map<String, String> map : addressMap) {
			if (map != null && map.get("postcode")!= null && map.get("address1") != null && map.get("address2") != null) {
				addressList.add(new UserAddress(map.get("postcode"), map.get("address1"), map.get("address2")));
			} else {
				addressList.add(NullAddress.getInstance());
			}
		}
		return addressList;
	}
}
