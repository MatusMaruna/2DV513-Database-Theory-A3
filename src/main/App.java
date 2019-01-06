package main;

import controller.AppController; 
import utils.Database;
public class App {
static AppController app = new AppController();
static Database db = new Database();
public static void main(String[]args) {
System.out.println("Attempting Connection...");
db.connect();
app.mainMenu();
}
}
