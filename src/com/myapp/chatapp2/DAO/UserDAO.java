package com.myapp.chatapp2.DAO;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.myapp.chatapp2.DTO.UserDTO;
import com.myapp.chatapp2.utils.Encryption;
import com.myapp.chatapp2.utils.UserInfo;

public class UserDAO {

	public int register(UserDTO userDTO) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection connection = null;
		Statement stmt = null;
		try {

			connection = ChatDAO.createConnection();

			stmt = connection.createStatement();

			// insert into user values ('ram','pass')

			String username = userDTO.getUsername();
			char[] password = userDTO.getPassword();
			String status = userDTO.getStatus();

			String pass = Encryption.getEncryptPass(new String(password));
			String sql = "insert into users values  ('" + username + "' " + ", '" + pass + "' " + ", '" + status
					+ "' )";
			int result = stmt.executeUpdate(sql);

			return result;

		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}

		}

	}

	public boolean isLogin(UserDTO userDTO) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ChatDAO.createConnection();

			String sql = "select username from users where username =? and password =?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, userDTO.getUsername());
			String password = Encryption.getEncryptPass(new String(userDTO.getPassword()));
			statement.setString(2, password);

			resultSet = statement.executeQuery();

//			System.out.println(resultSet.next());
//			if (resultSet.next()) {
//				System.out.println(resultSet.next());
//
//				String sqlupdate = "Update users set status = ? where username = ?"; // reset pass
//				String status = "true";
//				statement = connection.prepareStatement(sqlupdate);
//				statement.setString(1, status);
//				statement.setString(2, userDTO.getUsername());
//				// System.out.println(username);
//				int result = statement.executeUpdate();
//				
//				//System.out.println(resultSet.next());
//				if(result>1) {
//					System.out.println("status updated ");
//				}
//
//			}
//			
//			System.out.println(resultSet.next());
			return resultSet.next();

		} finally {
			
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}

	
	public void getUsers() {

	}

	public int deleteUser() throws ClassNotFoundException, SQLException {

		Connection connection = null;
		PreparedStatement statement = null;
		// ResultSet resultSet = null;
		int result;

		try {
			connection = ChatDAO.createConnection();

			// System.out.println(UserInfo.Username);
			String sql = "Delete from users where username =?";

			statement = connection.prepareStatement(sql);
			statement.setString(1, UserInfo.Username);
			result = statement.executeUpdate();
			return result;

		} finally {
			if (statement != null) {
				statement.close();

			}
			if (connection != null) {
				connection.close();
			}
		}

	}

	public int updatePass(String username, String pass)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ChatDAO.createConnection();

			String sql = "select username from users where username =? "; // adding the fun if the username is not
																			// correct to reset pass
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);

			resultSet = statement.executeQuery();

			boolean res = resultSet.next();

			if (res) { // if username is correct
				// System.out.println(UserInfo.Username);
				String sqlupdate = "Update users set password = ? where username = ?"; // reset pass
				String password = Encryption.getEncryptPass(pass);
				statement = connection.prepareStatement(sqlupdate);
				statement.setString(1, password);
				statement.setString(2, username);
				System.out.println(username);
				int result = statement.executeUpdate();
				return result;
			} else {
				return -1;
			}

		} finally {
			if (statement != null) {
				statement.close();

			}
			if (connection != null) {
				connection.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}

	}

	
	
	public int setStatus(String status, String userid) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			
			connection = ChatDAO.createConnection();


			// if username is correct
				// System.out.println(UserInfo.Username);
				String sqlupdate = "Update users set status = ? where username = ?"; // reset pass
				statement = connection.prepareStatement(sqlupdate);
				statement.setString(1, status);
				statement.setString(2, userid);
				//System.out.println(username);
				int result = statement.executeUpdate();
				return result;
		
			
			}

		 finally {
			if (statement != null) {
				statement.close();

			}
			if (connection != null) {
				connection.close();
			}
			if (resultSet != null) { 
				resultSet.close();
			}
		}

		
	}
	
	public ArrayList<String> getUsersWithStatus(ArrayList<String> list) throws SQLException, ClassNotFoundException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ChatDAO.createConnection();

			String sql = "select username from users where status = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, "true");


			resultSet = statement.executeQuery();
			
			System.out.println(resultSet.next());

//			
//			System.out.println(resultSet.next());
			while(resultSet.next()) {
				
				 //System.out.println("added");
			
				list.add(resultSet.getString(1));
				
			}
			return list;

		} finally {
			
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		
	}

}
