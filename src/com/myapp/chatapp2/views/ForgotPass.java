package com.myapp.chatapp2.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.myapp.chatapp2.DAO.UserDAO;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ForgotPass extends JFrame{

	private JFrame frame;
	private JTextField username;
	private JTextField passnew;

	UserDAO userDAO = new UserDAO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					ForgotPass window = new ForgotPass();
	}

	public ForgotPass() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ForgotPass.class.getResource("/pics/chitchat.jpg")));
		setBounds(100, 100, 609, 371);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FORGOT PASSWORD");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(147, 41, 300, 40);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("UserId");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(109, 108, 109, 33);
		getContentPane().add(lblNewLabel_1);
		
		username = new JTextField();
		//this.username = username;
		username.setBounds(264, 109, 233, 33);
		getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New Password");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(109, 177, 109, 33);
		getContentPane().add(lblNewLabel_2);
		
		passnew = new JTextField();
		//this.passnew = passnew;
		passnew.setColumns(10);
		passnew.setBounds(264, 178, 233, 33);
		getContentPane().add(passnew);
		
		
		JButton btnNewButton = new JButton("RESET PASS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					String username1 = username.getText();
					String newpass = passnew.getText();
					//System.out.println(username1);
					reset(username1,newpass);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch(Exception es) {
					es.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBounds(200, 259, 150, 30);
		getContentPane().add(btnNewButton);
		
		setVisible(true);
		
	}
	
	public void reset(String username, String pass) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
		int result = userDAO.updatePass(username,pass);
		
		//System.out.println(result);
		
		//if()
		
		if(result>0) {
			JOptionPane.showMessageDialog(this, "success");
			
			setVisible(false);
			dispose();
			
			UserScreen userScreen = new UserScreen();
			userScreen.setVisible(true);
		}else if(result==-1){ 
			JOptionPane.showMessageDialog(this, "wrong username");
		}else {
			JOptionPane.showMessageDialog(this, "no update possible");
		}
		
	}
}
