package designpattern.nullobject;

public class NullAddress implements Address {
	private static NullAddress single_instance = null;
	
	private NullAddress() {
		
	}
	
	public static NullAddress getInstance() {
		if (single_instance == null) single_instance = new NullAddress();
		
		return single_instance;
		
	}
	
	@Override
	public String getAddress() {
		return "주소없음"; // ""로 해도됨. 티가 나게 하기 위해
	}

}
