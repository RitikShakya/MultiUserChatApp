package com.myapp.chatapp2.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.myapp.chatapp2.DAO.UserDAO;
import com.myapp.chatapp2.Networking.Client;
import com.myapp.chatapp2.Networking.Server;
import com.myapp.chatapp2.Networking.ServerWorker;
import com.myapp.chatapp2.utils.UserInfo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JLabel;

public class ClientChat extends JFrame {
	private JTextField textField;
	Client client  ;
	static ArrayList<String> list = new ArrayList<String>();
	Server server  ;
	UserDAO userDAO = new UserDAO();
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {

		ClientChat window = new ClientChat();

		
	}
	
	
	public void sendMessage() {
		
		String message = textField.getText();
		try {
			client.sendMessage(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//textArea.setText(message);
		
		
	}
	
	
	public void listusers() {
		
		try {
			ArrayList<String> userlist = userDAO.getUsersWithStatus(list);
			
			for(String user : userlist) {
				System.out.println(user);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}


	public ClientChat() throws UnknownHostException, IOException {
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				int result =JOptionPane.showConfirmDialog(ClientChat.this, "are you sure");
				if(result==JOptionPane.YES_OPTION) {
					setVisible(false);
					dispose();
					DashBoard dashBoard = new DashBoard(UserInfo.Username);

					dashBoard.Logout();
					
				}
			}
		});
		setTitle("ChatScreen" + UserInfo.Username);
		//this.list = listusers;
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClientChat.class.getResource("/pics/chitchat.jpg")));
		setBounds(100, 100, 725, 468);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 368, 374);
		getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		client = new Client(textArea);

		textField = new JTextField();
		textField.setBounds(20, 394, 271, 27);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Send ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sendMessage();
			}
		});
		btnNewButton.setBounds(293, 394, 85, 27);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("showusers ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  listusers();
		
			  
				
				}
		});
		btnNewButton_1.setBounds(541, 235, 85, 21);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Go To Dashboard");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				
				DashBoard dashBoard = new DashBoard(UserInfo.Username);
				dashBoard.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(487, 397, 165, 21);
		getContentPane().add(btnNewButton_2);
		
		

		
		setVisible(true);
	}
}
