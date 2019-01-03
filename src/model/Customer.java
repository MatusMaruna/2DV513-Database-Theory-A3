package model;



public class Customer {
	private String id;
	private String name;
	private String address;
	private String workPhone;
	private String phone;
	private Reservations reservations;
	public Customer (){
		
	}
	//private Reservations reservations;
	public String getId() {
		return id;
	}
	public Reservations getReservations() {
		return reservations;
	}
	public void setReservations(Reservations reservations) {
		this.reservations = reservations;
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
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
