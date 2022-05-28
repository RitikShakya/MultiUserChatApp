package com.myapp.chatapp2.Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.myapp.chatapp2.utils.UserInfo;

public class ServerWorker extends Thread {

	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	//public String Username;
	// private ArrayList<String> listusers = new ArrayList<String>();

	public ServerWorker(Socket clientSocket, Server server) throws IOException {

		this.clientSocket = clientSocket;
		//this.Username = UserInfo.Username;
		this.server = server;
		// to get the data of client
		in = clientSocket.getInputStream();
		// to write send the data to client
		out = clientSocket.getOutputStream();

	}

	@Override
	public void run() {
		// Read data from client and broadcast to all
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in)); // will work for the bytes and
																						// one line of code is read in
																						// this way
		String line;

		try {
			while (true) {

				// read data lineweise from user client
				line = bufferedReader.readLine();

				System.out.println("line read" + line);
				if (line.equalsIgnoreCase("quit")) {
					break;
				}

				// to traverse all the workers created for all the clients
				for (ServerWorker serverWorker : server.listworker) { // listworker is the list of workers created in
																		// server.java

					line = line + "\n";
					serverWorker.out.write(line.getBytes()); // broadcast the message to all the users client
				}
				// write back the data to single user
				// out.write(line.getBytes());

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();

				}
				if (clientSocket != null) {
					clientSocket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}

//
//
//public class ServerWorker extends Thread{
//	
//	@Override
//	public void run() {
//		
//		for(int i=0;i<5;i++) {
//			
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("i" +i + Thread.currentThread()) ;
//		}
//		
//	}
//	public static void main(String[] args) {
//		
//		ServerWorker serverWorker = new ServerWorker();
//		serverWorker.start();
//		
//		for(int j =0;j<5;j++) 
//		{
//			System.out.println("j" + j + Thread.currentThread());
//		}
//		
//		
//	}
//}
//
//
//
//

//public class ServerWorker implements Runnable {
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//
//		for(int i=0;i<5;i++) {
//			System.out.println("i" +i + Thread.currentThread()) ;
//		}
//		
//		
//	}
//	
//	public static void main(String[] args) {
//		
//		ServerWorker job = new ServerWorker(); // a job i created 
//		
//		Thread worker = new Thread(job,"worker1");  // a worker is created and job is assigned to it
//		worker.start(); // worker allowed to perform the work
//		
//		for(int j =0;j<5;j++) 
//		{
//			System.out.println("j" + j + Thread.currentThread());
//		}
//		
//	}
//}
