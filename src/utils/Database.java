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
			String query = "INSERT INTO Customer (id,name,phone, address,smoker,reservation_id) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement presta = connect().prepareStatement(query);
			presta.setString(1, "");
			presta.setString(2, name);
			presta.setString(3, phone);
			presta.setString(4, address);
			presta.setBoolean(5, smoker);
			presta.setString(6, reservation_id);
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
				System.out.println("id: " + rs.getString("id") + " name: " + rs.getString("name") + 
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
	
	public  void testSearch(){
		
		//ONLY WORKS ON SELECT * ATM
		//DONT PAY TO MUCH ATTENTION TO HOW MESSY THIS FUNCTION WIP
		try {
			int numberOfCollumns=0;
			System.out.println("Write your query here with the correct Syntax");
			Scanner scan = new Scanner(System.in);
			String s = scan.nextLine();
			if(s.contains("Customer"))
			numberOfCollumns=6;
			if(s.contains("Room"))
			numberOfCollumns=5;
			if(s.contains("Reservation"))
			numberOfCollumns=4;
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery(s);
			
			while(rs.next()) {
				
				
				for(int i=1;i<=numberOfCollumns;i++){
					System.out.print("|"+rs.getString(i)+"|  ");
				}
				 System.out.println("\n");
			
			
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
}
