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
			// List Reservations
			break;
		case 4:
			
	       
			break;
		case 5:
			// Delete Reservation
			break;
		default:
			System.err.println("Invalid Choice!");
			reservationMenu();
			break;
		}
	}

	public void guestMenu() {
		String[] data = createConstructor(2, guestQuestions);
		System.out.println(Arrays.toString(data));
		//db.
	}

	public void roomMenu() {
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
		System.out.println("1: Search manually");
		System.out.println("2: Search with keywords");
		
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		switch (input) {
		case 1:
			
			db.testSearch();
			
			break;
		case 2:
			System.out.println("some options inc");
			
			break;
		
		default:
			System.out.println("wip");
			break;
		}
	}
}
