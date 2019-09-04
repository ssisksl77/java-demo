package designpattern.nullobject.oldversion;

import java.util.ArrayList;
import java.util.List;

public class Address {
	private final String zoneCode;
	private final String address;
	
	public Address(final String zoneCode, final String address) {
		this.zoneCode = zoneCode;
		this.address = address;
	}
	
	public String getAddress() {
		return "Address [zoneCode=" + zoneCode + ", address=" + address + "]";
	}

	public static void main(String[] args) {
		Address address = new Address("91919", "address 1");
		Address address2 = null;
		
		List<Address> list = new ArrayList<>();
		list.add(address);
		list.add(address2);
		
		for (Address add : list) {
			if(add != null)
				System.out.println(add.getAddress());
		}
	}
}
