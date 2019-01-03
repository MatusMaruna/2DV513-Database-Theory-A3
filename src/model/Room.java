package model;

public class Room {
	private String id;
	private boolean smoke;
	private int size;
	private double price;
	private boolean availability;
	
	public Room (){ 
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isSmoke() {
		return smoke;
	}
	public void setSmoke(boolean smoke) {
		this.smoke = smoke;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

}
