package main;

import java.sql.Date;

import controller.AppController; 
import utils.Database;
public class App {
static Database db = new Database();
static AppController app = new AppController(db);
public static void main(String[]args) {
//Check if the database is reachable
	//db.listAvailableRooms("2019-01-03", "2019-01-21", true);
app.mainMenu();
}
}
