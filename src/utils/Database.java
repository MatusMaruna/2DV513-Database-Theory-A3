package utils;
import java.sql.*;

public class Database {

	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mm223fj.mynetgear.com:20124/Reddit_nocon","jb223pt","Avyq3Gm");
		}catch(Exception e){
			System.out.println(e);
			
		}
		System.out.println("Connected to Database");
	}
}
