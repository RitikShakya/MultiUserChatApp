package com.myapp.chatapp2.views;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.myapp.chatapp2.DAO.UserDAO;
import com.myapp.chatapp2.DTO.UserDTO;
import com.myapp.chatapp2.utils.UserInfo;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class UserScreen extends JFrame {
	//Logger logger = Logger.getLogger(UserScreen.class);
	private JTextField userID;
	private JPasswordField passwordField;
	

	public static void main(String[] args) {

		UserScreen window = new UserScreen();
		

	}

	UserDAO userDAO = new UserDAO();

	public void register() {
		//logger.debug("registered  method");

		String userid = userID.getText();
		char[] password = passwordField.getPassword();

		
		
		
		
		UserDTO userDTO = new UserDTO(userid, password);

		try {
			int result = userDAO.register(userDTO);

			if (result > 0) {
				JOptionPane.showMessageDialog(this, "Success in register");
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
//			logger.error(e.getMessage());
			System.out.println("db errorr");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error(e.getMessage());
		}

		// System.out.println(userid + " " + password);
	}

	public void login() {
		

		//logger.debug("login method work");
		String userid = userID.getText();
		char[] password = passwordField.getPassword();

		//String status = 
		UserDTO userDTO = new UserDTO(userid, password);

		//UserInfo.list = list;
		try {
			String message = null;
			boolean ans = userDAO.isLogin(userDTO);
			
		
			if (ans) {
				
				
			//list.add(userDTO);
			userDTO.setStatus("true");
			userDAO.setStatus(userDTO.getStatus(),userid);

			System.out.println("status update");
				
				
				message = " welcome" + userid ;
				UserInfo.Username = userid;
				JOptionPane.showMessageDialog(this, message );
				setVisible(false);
				dispose();
				DashBoard board = new DashBoard(message);
				board.setVisible(true);
			} else {
				message = "invalid user";
				JOptionPane.showMessageDialog(this, message);

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.error(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.error(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.error(e.getMessage());
		}

	}

	public UserScreen() {
		setFont(new Font("Arial", Font.BOLD, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserScreen.class.getResource("/pics/chitchat.jpg")));
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setTitle("Login");

		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(170, 28, 102, 27);
		getContentPane().add(lblNewLabel);

		JLabel user_id = new JLabel("User Id");
		user_id.setFont(new Font("Arial", Font.PLAIN, 15));
		user_id.setBounds(79, 85, 85, 24);
		getContentPane().add(user_id);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(79, 131, 85, 13);
		getContentPane().add(lblNewLabel_2);

		userID = new JTextField();
		userID.setBounds(191, 82, 151, 27);
		getContentPane().add(userID);
		userID.setColumns(10);

		JButton loginbtn = new JButton("Login");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				login();
			}
		});
		loginbtn.setBackground(Color.WHITE);
		loginbtn.setBounds(128, 196, 85, 21);
		getContentPane().add(loginbtn);

		JButton registerbtn = new JButton("Register");
		registerbtn.setBackground(Color.WHITE);
		registerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				register();
			}
		});
		registerbtn.setBounds(241, 196, 85, 21);
		getContentPane().add(registerbtn);

		passwordField = new JPasswordField();
		passwordField.setBounds(191, 129, 151, 27);
		getContentPane().add(passwordField);

		JLabel resetPass = new JLabel("Forgot pass ?");
		resetPass.setHorizontalAlignment(SwingConstants.CENTER);
		resetPass.setBounds(205, 240, 67, 13);
		getContentPane().add(resetPass);

		resetPass.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				setVisible(false);
				ForgotPass forgotPass = new ForgotPass();
				forgotPass.setVisible(true);

			}
		});

		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
}
