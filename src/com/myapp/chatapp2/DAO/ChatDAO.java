package com.myapp.chatapp2.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.myapp.chatapp2.utils.ConfigReader.getValue;

public interface ChatDAO {
	
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName(getValue("DRIVER"));
		
		final String Connection_String = getValue("CONNECTION_STRING");
		final String user =getValue("USER_ID");
		final String pass = getValue("DB_PASSWORD");
		Connection conn = DriverManager.getConnection(Connection_String, user, pass);
		
		if(conn != null) {
			System.out.println("connection created");
			//conn.close();
			
		}
		return conn ;
		
	}
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		
//		ChatDAO chatDAO = new ChatDAO();
//		chatDAO.createConnection();
//	}

}
