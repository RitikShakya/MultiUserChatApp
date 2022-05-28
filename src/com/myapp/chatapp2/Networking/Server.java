package com.myapp.chatapp2.Networking;

import java.io.IOException;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.myapp.chatapp2.utils.ConfigReader;
import com.myapp.chatapp2.utils.UserInfo;


// what i have added forgot pass with username in correct
// logout feature
// delete account 

//C:\Users\91965\eclipse-workspace\ChatApp2\bin
// com.myapp.chatapp2.Networking

public class Server {

	ArrayList<ServerWorker> listworker = new ArrayList<ServerWorker>();
	//ArrayList<String > users = new ArrayList<String>();
	ServerSocket serverSocket;

	public Server() throws IOException {
		int port = Integer.parseInt(ConfigReader.getValue("PORT"));
		serverSocket = new ServerSocket(port);

		System.out.println("waiting server for clients");
		handleClient();
		//showclient();
		

	}
	


	public void handleClient() throws IOException {

		while(true){ // infinite loopp
		Socket clientSocket = serverSocket.accept();  // handshaking when a client comes

		//clientSocket.
		System.out.println("new client comes "  );
		
		//listclient.add(UserInfo.Username);
		ServerWorker serverWorker = new ServerWorker(clientSocket, this); 
		// thread  . a worker is created for each client
		listworker.add(serverWorker);// added to the list of workers as when we use new in above line new object is created for thread
	
		//serverWorker.setName(serverWorker.Username);
		//System.out.println(" getting name " + serverWorker.Username);
		//users.add(serverWorker.getName());
		serverWorker.start();// to start the thread
		
		}
	}
	
	
	
	
	public static void main(String[] args) throws IOException {

	Server server = new Server();
	
}
	
	
	
	
	

	// single client only
//	public Server() throws IOException {
//		
//		int port = Integer.parseInt(ConfigReader.getValue("PORT"));
//		serverSocket = new ServerSocket(port);
//		System.out.println("Server is ready for connecting waiting for client");
//		
//		Socket socket = serverSocket.accept(); //handshaking
//		
//		System.out.println("client joins server");
//		
//		InputStream input = socket.getInputStream();
//		byte read[] = input.readAllBytes();
//		
//		
//		String message = new String(read);
//		System.out.println("user message " +message);
//		input.close();
//		socket.close();
//	}
//	
//
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//
//		Server server = new Server();
//	}

}
