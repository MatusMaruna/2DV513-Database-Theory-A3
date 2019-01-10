package utils;

import java.sql.*;
import java.util.Scanner;

public class Database {

	public Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mm223fj.mynetgear.com:20124/Hotel", "jb223pt",
					"Avyq3Gm");
			System.out.println("Connected to Database");
			return con;
		} catch (Exception e) {
			System.err.println("Failed to connect to database !");
			System.out.println(e);

		}
		return null;

	}

	public void insertCustomer(String name, String phone, String address,boolean smoker,String reservation_id) {
		try {
			String query = "INSERT INTO Customer (name,phone, address,smoker) VALUES ( ?, ?, ?, ?)";
			PreparedStatement presta = connect().prepareStatement(query);
			presta.setString(1, name);
			presta.setString(2, phone);
			presta.setString(3, address);
			presta.setBoolean(4, smoker);
			presta.execute();

		} catch (Exception e) {
			System.err.println("insertCustomer exception");
			System.err.println(e);
		}
	}
	public void insertReservation(Date startDate, Date endDate, String room_id) { 
		try {
			String query = "INSERT INTO Reservation (id,startdate,enddate, room_id) VALUES (?, ?, ?, ?)";
			PreparedStatement presta = connect().prepareStatement(query);
			presta.setString(1, "");
			presta.setDate(2, startDate);
			presta.setDate(3, endDate);
			presta.setString(4, room_id);
			presta.execute();

		} catch (Exception e) {
			System.err.println("insertReservation exception");
			System.err.println(e);
		}
	}
	/*Currently only prints the name*/
	public void listCustomers() {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("Select * FROM Customer");
			while(rs.next()) {
				System.out.println("id: " + rs.getInt("id") + " name: " + rs.getString("name") + 
						" phone: " + rs.getString("phone") + " address: " + rs.getString("address") + 
						" smoker: " + rs.getBoolean("smoker") + " reservation_id: " + rs.getString("reservation_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void listReservations() {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("Select * FROM Reservation");
			while(rs.next()) {
				System.out.println("id: " + rs.getString("id") + " startdate: " + rs.getDate("startdate") + 
						" enddate: " + rs.getDate("enddate") + " room_id: " + rs.getString("room_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public void listRooms() {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("Select * FROM Room");
			while(rs.next()) {
				System.out.println("id: " + rs.getString("id") + " smoke: " + rs.getBoolean("smoke") + 
						" size: " + rs.getString("size") + " price: " + rs.getString("price") + " availability: " + 
						rs.getBoolean("availability"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void listCustomersByName(String name) {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("Select * FROM Customer WHERE `name` = " + "\"" +name +"\"");
			while(rs.next()) {
				System.out.println("id: " + rs.getString("id") + " name: " + rs.getString("name") + 
						" phone: " + rs.getString("phone") + " address: " + rs.getString("address") + 
						" smoker: " + rs.getBoolean("smoker") + " reservation_id: " + rs.getString("reservation_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void updateCustomerReservation(String id, String reservation_id) {
		try {
			Statement stmt = connect().createStatement();
			stmt.executeUpdate("Update Customer SET `reservation_id` = " + "\"" +reservation_id+"\"" + " WHERE `id` = "+ "\"" + id + "\"" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void deleteReservation(String id) {
		try {
			Statement stmt = connect().createStatement();
			stmt.executeUpdate("Delete FROM Reservation WHERE `id` = " + "\"" +id +"\"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void updateReservation(String id, Date startDate, Date endDate, String room_id) {
		try {
			Statement stmt = connect().createStatement();
			stmt.executeUpdate("Update Reservation SET `startdate` = " + "\"" +startDate+"\"" + " WHERE `id` = "+ "\"" + id + "\"" );
			stmt.executeUpdate("Update Reservation SET `enddate` = " + "\"" +endDate+"\"" + " WHERE `id` = "+ "\"" + id + "\"" );
			stmt.executeUpdate("Update Reservation SET `room_id` = " + "\"" +room_id+"\"" + " WHERE `id` = "+ "\"" + id + "\"" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void listAvailableRooms(Date startDate, Date endDate, boolean smoking) {
		//TODO
	}
		public void nameRoom(){
		//want it to be like this or do you want any kindof input?
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Reservation.room_id as RoomNumber,Customer.name as CurrentGuest FROM Customer,Reservation WHERE Customer.reservation_id=Reservation.id");
			while(rs.next()) {
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
					
					System.out.print("|"+rs.getMetaData().getColumnLabel(i)+": "+rs.getString(i)+"|  ");
					
				}
				System.out.println("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
	//not sure if this is something we could use, lists all reserved rooms and the number to the current guest that currently has the reservation
	//could have a room as input or guest if we want
	public void phoneToRoomOwner(){
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Customer.phone AS number, Reservation.room_id FROM Customer,Reservation WHERE Customer.reservation_id=Reservation.id ");
			while(rs.next()) {
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
					
					System.out.print("|"+rs.getMetaData().getColumnLabel(i)+": "+rs.getString(i)+"|  ");
					
				}
				System.out.println("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	//anything we can do with the price?
	public void cheapestAvailableRoom(){
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Room.id, Room.price FROM Room WHERE Room.availability=0 ORDER BY price ASC");
			while(rs.next()) {
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
					
					System.out.print("|"+rs.getMetaData().getColumnLabel(i)+": "+rs.getString(i)+"|  ");
					
				}
				System.out.println("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	
	public  void keySearch(String s,String db,String where){
		
		//ONLY WORKS ON SELECT * ATM
		//DONT PAY TO MUCH ATTENTION TO HOW MESSY THIS FUNCTION WIP
		try {
		
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append(s+" FROM ");
			sb.append(db+" ");
			sb.append(where);
		
			System.out.println(sb.toString());
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());
			
			while(rs.next()) {
				
				if(s.contains("*")){
					for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
						
						System.out.print("|"+rs.getMetaData().getColumnLabel(i)+": "+rs.getString(i)+"|  ");
						
					}
				}
				else
		        System.out.print("|"+s+": "+rs.getString(s)+"|  ");
				
				System.out.println("\n");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
public  void manualSearch(String s){
		

		try {
			
			
			
			System.out.println(s);
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery(s);
			
			
		
			while(rs.next()) {
				
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
					
					System.out.print("|"+rs.getMetaData().getColumnLabel(i)+": "+rs.getString(i)+"|  ");
					
				}
				
				System.out.println("\n");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
}
