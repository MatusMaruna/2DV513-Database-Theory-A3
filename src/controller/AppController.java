package controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import utils.Database;

public class AppController {
	/* Menu Options */
	static String[] mainOptions = { "Reservation Management", "Create Guest", "Rooms","Search" };
	static String[] reservationOptions = { "Create Reservation", "List Reservations", "List Customers", "Delete Reservation" };
	static String[] roomOptions = { "List Rooms", "Search Rooms" };
	static String[] searchOptions = {"Customer's Reservations", "Rooms and phone numbers", "Cheapest Rooms", "Reservations ending by month"};
	/* Constructor Questions */
	static String[] guestQuestions = { "Enter full name:", "Enter Address:", "Enter Phone:", "Smoker (Y/N):" };
	static String[] reservationQuestions = { "Enter customer id:", "Enter start date YYYY-MM-DD:",
			"Enter end date YYYY-MM-DD:", "Enter Room:" };
	static String[] RoomCollumns = { "id", "smoke","size","price","availability"};
	static String[] ReservationCollumns = { "id", "startdate","enddate","room_id"};
	static String[] CustomerCollumns = { "id", "name","phone","address","smoker","reservation_id"};
	
	Database db; 

	
	public AppController(Database db){
		this.db = db; 
	}
	public void mainMenu() {
		space(2);
		switch (createMenu("Main Menu", mainOptions, false)) {
		case 1:
			reservationMenu();
			break;
		case 2:
			guestMenu();
			break;
		case 3:
			roomMenu();
			break;
		case 4:
			searchMenu();
			break;

		default:
			System.err.println("Invalid Choice!");
			mainMenu();
			break;
		}
	}

	public void reservationMenu() {
		space(2);
		switch (createMenu("Reservation Management", reservationOptions, true)) {
		case 0:
			mainMenu();
			break;
		case 1:
			String[] data = createConstructor(1, reservationQuestions);
			System.out.println(Arrays.toString(data));
			java.util.Date dateA = null;
			java.util.Date dateB = null;
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				dateB = df.parse(data[1]);
				dateA = df.parse(data[2]);
			} catch (ParseException e) {
				
			}
			java.sql.Date sqlDateBefore = new java.sql.Date(dateB.getTime());
			java.sql.Date sqlDateAfter  = new java.sql.Date(dateA.getTime());
			db.insertReservation(sqlDateBefore, sqlDateAfter, data[3]);
			db.updateCustomerReservation(data[0], db.getReservationId(sqlDateBefore, sqlDateAfter, data[3]));
			break;
		case 2:
			//List Reservations
			System.out.println("Fetching Reservations from database...");
			db.listReservations();
			switch (createMenu("", new String[0],true)) {
			default:
				reservationMenu();
				break;
			};
			break;
			
		case 3:
			//List Reservations
			System.out.println("Fetching Reservations from database...");
			db.listCustomers();
			switch (createMenu("", new String[0],true)) {
			default:
				reservationMenu();
				break;
			};
			break;
		case 4:
			// Delete Reservations
			System.out.println("Fetching Reservations from database...");
			db.listReservations();
			String[] reservationDelID = createConstructor(-1, new String[]{"Choose reservation ID:"});
			db.deleteReservation(reservationDelID[0]);
			System.out.println("Reservation " + reservationDelID[0] + " deleted");
			
			break;
		default:
			System.err.println("Invalid Choice!");
			reservationMenu();
			break;
		}
		switch (createMenu("", new String[0],true)) {
		case 0: 
			mainMenu(); 
			break; 
		default:
			System.err.println("Invalid Choice!");
			reservationMenu();
		};
	}

	public void guestMenu() {
		String[] data = createConstructor(2, guestQuestions);
		System.out.println(Arrays.toString(data));
		System.out.println("Inserting guest into database, please wait...");
		if(data[3].toLowerCase().equals("y")){
			db.insertCustomer(data[0], data[2], data[1], true, "NULL");
		} else {
			db.insertCustomer(data[0], data[2], data[1], false, "NULL");
		}
		System.out.println("Insert Complete");
		switch (createMenu("", new String[0],true)) {
		default:
			mainMenu();
			break;
		};
		
	}

	public void roomMenu() {
		System.out.println("Fetching rooms from database...");
		db.listRooms();
		switch (createMenu("", new String[0],true)) {
		case 0: 
			mainMenu(); 
			break; 
		default:
			System.err.println("Invalid Choice!");
			roomMenu();
		};
		

	}
	
	public void searchMenu() {
		switch (createMenu("Search Menu", searchOptions, true)) {
		case 0: 
			mainMenu();
			break;
		case 1: 
			db.nameRoom();
			break; 
		case 2: 
			db.phoneToRoomOwner();
			break; 
		case 3:
			db.cheapestAvailableRoom();
			break;
		case 4:
			db.reservationsStarting();
		default: 
			break;
		}
		switch (createMenu("", new String[0],true)) {
		default:
			searchMenu();
			break;
		};
	}

	/* Creates menu in console */
	public int createMenu(String title, String[] options, boolean ret) {
		System.out.println(title);
		for (int i = 0; i < options.length; i++) {
			System.out.println(i + 1 + ". " + options[i]);
		}
		if (ret) {
			System.out.println("0. Return");
		}
		int input = -1;
		Scanner scan = new Scanner(System.in);
		if (scan.hasNextInt()) {
			input = scan.nextInt();
			if (input > options.length + 1) {
				System.err.println("Invalid option, Try again!");
			}
		}
		return input;
	}

	/* Creates 'constructor' in console */
	public String[] createConstructor(int id, String[] questions) {
		String[] data = new String[questions.length];
		for (int i = 0; i < questions.length; i++) {
			space(2);
			System.out.println(questions[i]);
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			switch (id) {
			case 1:
				// Reservation
				if (validateReservation(i, input, data)) {
					data[i] = input;
				} else {
					System.err.println("Invalid input,Try again!");
					i--;
				}
				break;
			case 2:
				// Guest
				if (validateGuest(i, input)) {
					data[i] = input;
				} else {
					System.err.println("Invalid input, Try again!");
					i--;
				}
				break;
			default:
				data[i] = input;
				break;
			}
			

		}

		return data;

	}

	/* Validators of input */
	public boolean validateGuest(int q, String data) {
		boolean valid = true;
		switch (q + 1) {
		case 1:
			// Name
			if (data.length() <= 2) {
				valid = false;
				System.err.println("Invalid name, please enter atleast 3 characters");
			}
			break;
		case 2:
			// Address
			if (data.length() <= 2) {
				valid = false;
				System.err.println("Invalid address, please enter atleast 3 characters");
			}
			break;
		case 3:
			// Phone
			if (data.length() <= 2) {
				valid = false;
				System.err.println("Invalid phone, please enter atleast 3 numbers");
			}
			break;
		case 4:
			// Smoker
			System.out.println(data.toLowerCase());
			if (!data.toLowerCase().equals("y") && !data.toLowerCase().equals("n")) {
				valid = false;
				System.err.println("Invalid input, please enter Y or N");
			}
			break;
		}
		return valid;

	}

	public boolean validateReservation(int q, String data, String[] datas) {
		boolean valid = true; 
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date;
		switch (q + 1) {
		case 1: 
			if(!db.getCustomerById(data)){
			valid = false; 
			System.err.println("Invalid id, or customer has a reservation");
			}
			break; 
		case 2: 
			df.setLenient(false); 
			try{
				date = df.parse(data);
			} catch (Exception e) {
				valid = false; 
				System.err.println("Invalid date, yyyy-mm-dd");
			}
		
			break; 
		case 3: 
			df.setLenient(false);
			try{
				date = df.parse(data);
			} catch (Exception e) {
				valid = false; 
				System.err.println("Invalid date, yyyy-mm-dd");
				break;
			}
			java.util.Date dateB = null;
			try {
				dateB = df.parse(datas[1]);
			} catch (ParseException e) {
				
			}
			if(date.before(dateB)) {
				valid = false; 
				System.err.println("Invalid date, has to be after startDate");
				break;
			}
			java.sql.Date sqlDateBefore = new java.sql.Date(dateB.getTime());
			java.sql.Date sqlDateAfter  = new java.sql.Date(date.getTime());
			System.out.println("Available Rooms: ");
			db.listAvailableRooms(sqlDateBefore, sqlDateAfter, db.isCustomerSmoker(datas[0]));
		
			break;  
		case 4:
			ArrayList availableRooms = db.returnListOfAvailableRooms(datas[1], datas[2], db.isCustomerSmoker(datas[0]));
			if(!availableRooms.contains(data)) {
				valid = false; 
				System.err.println("That room is not available !");
			}
			break; 
			
		}
		return valid;
	}

	/* Misc */
	public void space(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("\n");
		}
	}
	
}
