package designpattern.nullobject;

public class UserAddress implements Address{
	private String postcode;
	private String address1;
	private String address2;
	
	
	public UserAddress(String postcode, String address1, String address2) {
		super();
		this.postcode = postcode;
		this.address1 = address1;
		this.address2 = address2;
	}

	@Override
	public String getAddress() {
		return "[" + postcode + "] " + address1 + " " + address2;
	}
	
}
