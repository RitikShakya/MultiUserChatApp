package com.myapp.chatapp2.DTO;

public class UserDTO {

	private String username ;
	private char[] password;
	private String status="false";
	
	
	public UserDTO() {
		super();
	}

	public UserDTO(String username, char[] password, String status) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public UserDTO(String username, char[] password ) {
		
		this.username = username;
		this.password = password;
		
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}
	
	
}
