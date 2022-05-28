package com.myapp.chatapp2.views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserView extends JFrame{
	int count;
	public UserView (){
		count=0;
		setVisible(true);
		setSize(500, 500);
		setResizable(false);
		setTitle("Login");
		
		JLabel welcome = new JLabel("Login");
		welcome.setFont(new Font("Arial", Font.BOLD,40));
			
		Container container = this.getContentPane();
		container.setLayout(null);
		welcome.setBounds(100, 70, 200, 60);
		container.add(welcome);
		
		JButton btn = new JButton("count");
		btn.setBounds(100, 140, 200, 60);
		container.add(btn);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				count++;
				welcome.setText("count " +count);
			}
		});
		
	}
	public static void main(String[] args) {
		
		UserView userView = new UserView();
		
	}

}
