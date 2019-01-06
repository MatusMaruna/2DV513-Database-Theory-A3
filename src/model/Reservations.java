package model;

import java.util.Date;

public class Reservations {
	private String id;
	private Date startDate;
	private Date endDate;
	private String room_id;
	

	public Reservations(){
	
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getRoom() {
		return room_id;
	}


	public void setRoom(String room) {
		this.room_id = room_id;
	}

}
