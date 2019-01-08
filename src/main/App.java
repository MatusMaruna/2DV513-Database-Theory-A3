package main;

import controller.AppController; 
import utils.Database;
public class App {
static Database db = new Database();
static AppController app = new AppController(db);
public static void main(String[]args) {
System.out.println("Attempting Connection...");
//Check if the database is reachable
db.listCustomersByName("matus");
app.mainMenu();
}
}
