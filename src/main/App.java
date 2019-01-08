package main;

import controller.AppController; 
import utils.Database;
public class App {
static Database db = new Database();
static AppController app = new AppController(db);
public static void main(String[]args) {
//Check if the database is reachable
app.mainMenu();
}
}
