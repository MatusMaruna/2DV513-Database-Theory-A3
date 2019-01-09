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
	
	
	
	public void search(){
		String s="";
		String data="";
		String where="";
		int i=0;
		String[] collumns = null;
		System.out.println("1: Search manually");
		System.out.println("2: Search with keywords");
		
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		scan.nextLine();
		
		switch (input) {
		case 1:
			
			System.out.print("Write your query (use proper syntax)\n");
			s=scan.nextLine();
			db.manualSearch(s);
			break;
			
		case 2:
			System.out.println("Which database do you want to search?");
			System.out.println("1: Room");
			System.out.println("2: Customer");
			System.out.println("3: Reservation");
			System.out.println("4: Multiple");
			
			input=scan.nextInt();
			if(input==1){
				data="Room";
				collumns=RoomCollumns;
			}
			if(input==2){
				data="Customer";
				collumns=CustomerCollumns;
				}
			if(input==3){
				data="Reservation";
				collumns=ReservationCollumns;
			}
			
			System.out.println("What do you want to Select?");
			System.out.println("1: *");
			while(i<collumns.length){
				System.out.println((i+2) +": "+collumns[i]);
				i++;
			}
			
			input=scan.nextInt();
			if(input==1){
				s="*";
			}
			else if(input>0&&input<=collumns.length+2){
				s=collumns[input-2];
					
			}
			else{
				System.out.println("Incorrect input");
				mainMenu();
			}
			
			System.out.println("Do you want to include WHERE?");
			System.out.println("1: Yes");
			System.out.println("2: No");
			
			input=scan.nextInt();
			if(input==1){
				where="WHERE ";
				System.out.print("WHERE ");
				String y=scan.next();
				where+=y;
				db.keySearch(s, data, where);
			}
			else
			db.keySearch(s, data, where);
			
			break;
		
		
				
			
		
		default:
			System.out.println("\n\n\nIncorrect input");
			mainMenu();
			break;
		
		}
		scan.close();
		
	}
	
}
