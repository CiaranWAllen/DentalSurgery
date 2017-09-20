import java.io.Serializable;

public class Person implements Serializable{
	String name;
	String address;
	public Person(String a, String b) {
		name = a;
		address = b;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}

}
