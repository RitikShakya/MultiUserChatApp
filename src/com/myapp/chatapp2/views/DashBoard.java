package com.myapp.chatapp2.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import com.myapp.chatapp2.DAO.UserDAO;
import com.myapp.chatapp2.DTO.UserDTO;
import com.myapp.chatapp2.utils.UserInfo;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class DashBoard extends JFrame {

	// private static String userid;
	private static String message;
	// static ArrayList<UserDTO> list ;
	// UserScreen userScreen;
	UserDTO userDTO = new UserDTO();
	UserDAO userDAO = new UserDAO();

	public DashBoard(String message) {
		this.message = message;

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(DashBoard.this, "are you sure");
				if (result == JOptionPane.YES_OPTION) {
					setVisible(false);
					dispose();

					Logout();

				}
			}
		});
		// this.list = list;

		// this.userid = userid;
		setIconImage(Toolkit.getDefaultToolkit().getImage(DashBoard.class.getResource("/pics/chitchat.jpg")));

		setBounds(100, 100, 747, 537);
		setTitle(message);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(DashBoard.class.getResource("/pics/chat-app.jpg")));
		lblNewLabel.setBounds(20, 50, 439, 441);
		getContentPane().add(lblNewLabel);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 10, 700, 30);
		getContentPane().add(menuBar);

		JMenu ChatMenu = new JMenu("Chat");
		menuBar.add(ChatMenu);

		JMenuItem startChattmenu = new JMenuItem("StartChat");
		startChattmenu.setSelected(true);

		ChatMenu.add(startChattmenu);
		startChattmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					setVisible(false);
					ClientChat clientChat = new ClientChat();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JMenuItem logoutbtn = new JMenuItem("LogOut");
		ChatMenu.add(logoutbtn);

		logoutbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Logout();

			}
		});

		JMenuItem deletemenu = new JMenuItem("Delete Account");
		ChatMenu.add(deletemenu);

		JLabel lblNewLabel_1 = new JLabel("user");
		lblNewLabel_1.setBounds(538, 115, 100, 183);
		getContentPane().add(lblNewLabel_1);

		deletemenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				deleteUser();

			}
		});

		setVisible(true);

	}

	public void Logout() {

		userDTO.setStatus("false");
		try {
			userDAO.setStatus(userDTO.getStatus(), UserInfo.Username);
			System.out.println("status update");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setVisible(false);
		dispose();
		UserScreen userScreen = new UserScreen();
		userScreen.setVisible(true);

	}

	public void deleteUser() {
		try {
			int result = userDAO.deleteUser();

			if (result > 0) {

				JOptionPane.showMessageDialog(this, "delete success");

				setVisible(false);
				dispose();
				UserScreen userScreen = new UserScreen();
				userScreen.setVisible(true);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// String message =" hello";

		DashBoard dashBoard = new DashBoard(message);

	}
}
