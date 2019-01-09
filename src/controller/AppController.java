package controller;

import java.util.Arrays;
import java.util.Scanner;

import model.Customer;
import utils.Database;

public class AppController {
	/* Menu Options */
	static String[] mainOptions = { "Reservation Management", "Create Guest", "Rooms","Search" };
	static String[] reservationOptions = { "Create Reservation", "Edit Reservation", "List Reservations",
			"Search Reservations", "Delete Reservation" };
	static String[] roomOptions = { "List Rooms", "Search Rooms" };
	/* Constructor Questions */
	static String[] guestQuestions = { "Enter full name:", "Enter Address:", "Enter Phone:", "Smoker (Y/N):" };
	static String[] reservationQuestions = { "Enter customer id:", "Enter start date YYYY/MM/DD:",
			"Enter end date YYYY/MM/DD:", "Enter Room:" };
	static String[] searchQuestions = { "Search manually", "Search with keywords"};
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
			search();
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
			break;
		case 2:
			// Edit Reservation
			break;
		case 3:
			//List Reservations
			System.out.println("Fetching Reservations from database...");
			db.listReservations();
			switch (createMenu("", new String[0],true)) {
			default:
				reservationMenu();
				break;
			};
			break;
		case 4:
			// Search Reservations
			//By Room ?
			//By Customer ?
			break;
		case 5:
			// Delete Reservations
			System.out.println("Enter Reservation ID"); 
			
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
			db.insertCustomer(data[0], data[1], data[2], true, "NULL");
		} else {
			db.insertCustomer(data[0], data[1], data[2], false, "NULL");
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
				if (validateReservation(i, input)) {
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
					// System.err.println("Invalid input, Try again!");
					i--;
				}
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

	public boolean validateReservation(int q, String data) {
		boolean valid = true; 
		switch (q + 1) {
		case 1: 
			//id
			//check if id exists in database
			break; 
		case 2: 
			//startDate
			//check if current date is before startdate
			break; 
		case 3: 
			//endDate
			//check if end date is before startdate
			break; 
		case 4:
			//room
			//check if room is available in the time period
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
