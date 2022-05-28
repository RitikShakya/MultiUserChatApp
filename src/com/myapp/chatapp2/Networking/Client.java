package com.myapp.chatapp2.Networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextArea;

import com.myapp.chatapp2.utils.ConfigReader;
import com.myapp.chatapp2.utils.UserInfo;

public class Client {

	Socket socket;
	 //static ArrayList<String> listclient = new ArrayList<String>();
	
	InputStream in;
	OutputStream out;
	ClientWorker clientWorker ;

	JTextArea jTextArea;
	public Client(JTextArea jTextArea) throws UnknownHostException, IOException {
		
		this.jTextArea = jTextArea;
		//this.listclient = listcList;
		int port = Integer.parseInt(ConfigReader.getValue("PORT"));

		socket = new Socket(ConfigReader.getValue("SERVER_IP"), port);
		System.out.println("Client comes" + UserInfo.Username);
		//listclient.add(UserInfo.Username);
		out = socket.getOutputStream();
		
		in = socket.getInputStream();
	
		recieveMessage();
		
		
		
	}
	
	public void sendMessage(String message) throws IOException {

	
		message = message + "-"+ UserInfo.Username + "\n";
		
		out.write(message.getBytes());
	}
	
	public void recieveMessage() {
		
		clientWorker = new ClientWorker(in, jTextArea);
		clientWorker.start();
		
		
	}

//	public Client() throws UnknownHostException, IOException {
//		int port = Integer.parseInt(ConfigReader.getValue("PORT"));
//
//		socket = new Socket(ConfigReader.getValue("SERVER_IP"), port);
//		System.out.println("Client comes");
//
//		System.out.println("enter message to send to server");
//		Scanner sc = new Scanner(System.in);
//
//		String mesg = sc.nextLine();
//
//		OutputStream out = socket.getOutputStream();
//		out.write(mesg.getBytes()); // convert to bytes out stream uses byte to sen data
//
//		System.out.println("message send to server");
//		sc.close();
//
//		out.close();
//		socket.close();
//	}
//
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		// TODO Auto-generated method stub
//
//		Client client = new Client();
//
//	}

}
