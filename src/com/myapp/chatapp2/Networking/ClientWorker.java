package com.myapp.chatapp2.Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

public class ClientWorker extends Thread {

	private InputStream in;

	private JTextArea jTextArea;
	public ClientWorker(InputStream in,JTextArea jTextArea) {
		this.in = in;
		this.jTextArea = jTextArea;

	}

	@Override
	public void run() {

		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;

		try {
		while (true) {
			
				line = br.readLine();
				
				System.out.println("line read client" + line);
				jTextArea.setText(jTextArea.getText()+ line + "\n");
				
			} 
		}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(in!=null) {
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	
}
