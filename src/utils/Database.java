package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

	public Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mm223fj.mynetgear.com:20124/Hotel", "jb223pt",
					"test123");
			return con;
		} catch (Exception e) {
			System.err.println("Failed to connect to database !");
			System.out.println(e);

		}
		return null;

	}

	public void insertCustomer(String name, String phone, String address, boolean smoker, String reservation_id) {
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
			String query = "INSERT INTO Reservation (startdate,enddate, room_id) VALUES ( ?, ?, ?)";
			PreparedStatement presta = connect().prepareStatement(query);
			presta.setDate(1, startDate);
			presta.setDate(2, endDate);
			presta.setString(3, room_id);
			presta.execute();

		} catch (Exception e) {
			System.err.println("insertReservation exception");
			System.err.println(e);
		}
	}

	public void listCustomers() {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("Select * FROM Customer");
			while (rs.next()) {
				System.out.println("id: " + rs.getInt("id") + " name: " + rs.getString("name") + " phone: "
						+ rs.getString("phone") + " address: " + rs.getString("address") + " smoker: "
						+ rs.getBoolean("smoker") + " reservation_id: " + rs.getString("reservation_id"));
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
			while (rs.next()) {
				System.out.println("id: " + rs.getString("id") + " startdate: " + rs.getDate("startdate") + " enddate: "
						+ rs.getDate("enddate") + " room_id: " + rs.getString("room_id"));
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
			while (rs.next()) {
				System.out.println("id: " + rs.getString("id") + " smoke: " + rs.getBoolean("smoke") + " size: "
						+ rs.getString("size") + " price: " + rs.getString("price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean getCustomerById(String id) {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("Select * FROM Customer WHERE `id` = " + "\"" + id + "\"");
			if (rs.next()) {
				String temp = rs.getString("reservation_id");
				if (temp == null) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean setCustomerReservationToNull(String id) {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("Select * FROM Customer WHERE `id` = " + "\"" + id + "\"");
			if (rs.next()) {
				String temp = rs.getString("reservation_id");
				if (temp == null) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isCustomerSmoker(String id) {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("Select * FROM Customer WHERE `id` = " + "\"" + id + "\"");
			if (rs.next()) {
				Boolean temp = rs.getBoolean("smoker");
				if (temp) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void listCustomersByName(String name) {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("Select * FROM Customer WHERE `name` = " + "\"" + name + "\"");
			while (rs.next()) {
				System.out.println("id: " + rs.getString("id") + " name: " + rs.getString("name") + " phone: "
						+ rs.getString("phone") + " address: " + rs.getString("address") + " smoker: "
						+ rs.getBoolean("smoker") + " reservation_id: " + rs.getString("reservation_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateCustomerReservation(String id, String reservation_id) {
		try {
			Statement stmt = connect().createStatement();
			stmt.executeUpdate("Update Customer SET `reservation_id` = " + "\"" + reservation_id + "\""
					+ " WHERE `id` = " + "\"" + id + "\"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getReservationId(Date startDate, Date endDate, String room_id) {
		String result = null;
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("Select * FROM Reservation WHERE `startDate` = '" + startDate.toString() + "' AND  `endDate` = '" + endDate.toString() + "' AND `room_id` = '" + room_id + "'");
			while(rs.next()) {
				 result = rs.getString("id");
			}
			return result; 
		} catch (SQLException e) {
			
		}
		return result;
	}

	public void deleteReservation(String id) {
		try {
			Statement stmt = connect().createStatement();
			stmt.executeUpdate("Delete FROM Reservation WHERE `id` = " + "\"" + id + "\"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateReservation(String id, Date startDate, Date endDate, String room_id) {
		try {
			Statement stmt = connect().createStatement();
			stmt.executeUpdate("Update Reservation SET `startdate` = " + "\"" + startDate + "\"" + " WHERE `id` = "
					+ "\"" + id + "\"");
			stmt.executeUpdate("Update Reservation SET `enddate` = " + "\"" + endDate + "\"" + " WHERE `id` = " + "\""
					+ id + "\"");
			stmt.executeUpdate("Update Reservation SET `room_id` = " + "\"" + room_id + "\"" + " WHERE `id` = " + "\""
					+ id + "\"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listAvailableRooms(Date startDate, Date endDate, boolean smoking) {
		int smoke = -1;
		if (smoking) {
			smoke = 1;
		} else {
			smoke = 0;
		}
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id FROM Room WHERE `smoke` = " + smoke
					+ " AND id NOT IN (SELECT `room_id` FROM Reservation WHERE ((startDate BETWEEN '" + startDate.toString()
					+ "' AND '" + endDate.toString() + "' ) OR (endDate BETWEEN '" + startDate.toString() + "' AND '" + endDate.toString() + "' )) )");
			while (rs.next()) {
				System.out.println("Room: " + rs.getString("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> returnListOfAvailableRooms(String startDate, String endDate, boolean smoking) {
		int smoke = -1;
		if (smoking) {
			smoke = 1;
		} else {
			smoke = 0;
		}
		ArrayList<String> result = new ArrayList<String>(); 
		int i = 0; 
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id FROM Room WHERE `smoke` = " + smoke
					+ " AND id NOT IN (SELECT `room_id` FROM Reservation WHERE ((startDate BETWEEN '" + startDate.toString()
					+ "' AND '" + endDate.toString() + "' ) OR (endDate BETWEEN '" + startDate.toString() + "' AND '" + endDate.toString() + "' )) )");
			while (rs.next()) {
				result.add(rs.getString("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void nameRoom() {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT Customer.name,Reservation.room_id FROM Customer INNER JOIN Reservation ON Customer.reservation_id=Reservation.id");
			while (rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

					System.out.print("|" + rs.getMetaData().getColumnLabel(i) + ": " + rs.getString(i) + "|  ");

				}
				System.out.println("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void phoneToRoomOwner() {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT Customer.phone AS number, Reservation.room_id FROM Customer,Reservation WHERE Customer.reservation_id=Reservation.id ");
			while (rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

					System.out.print("|" + rs.getMetaData().getColumnLabel(i) + ": " + rs.getString(i) + "|  ");

				}
				System.out.println("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cheapestAvailableRoom() {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Room.id, Room.price FROM Room ORDER BY price ASC");
			while (rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

					System.out.print("|" + rs.getMetaData().getColumnLabel(i) + ": " + rs.getString(i) + "|  ");

				}
				System.out.println("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void reservationsStarting() {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(Reservation.id) AS ReservationsStarting ,MONTHNAME(startdate) AS MONTH FROM Reservation GROUP BY MONTH(startdate)");
			while (rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

					System.out.print("|" + rs.getMetaData().getColumnLabel(i) + ": " + rs.getString(i) + "|  ");

				}
				System.out.println("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
