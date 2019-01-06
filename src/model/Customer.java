package model;

public class Customer {
	private String id;
	private String name;
	private String address;
	private String phone;
	private Boolean smoker;
	public Boolean getSmoker() {
		return smoker;
	}
	public void setSmoker(Boolean smoker) {
		this.smoker = smoker;
	}
	public Customer(String name, String address, String phone, Boolean smoker) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.smoker = smoker;
	}
	//private Reservations reservations;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
