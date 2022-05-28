package com.myapp.chatapp2.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class SplashScreen extends JWindow {

	JProgressBar progressBar;
	public static void main(String[] args) {

		SplashScreen window = new SplashScreen();
		window.startProgress();
	}
	
	Timer timer;
	

	 public void startProgress(){
		
		 timer = new Timer(50, new ActionListener() {
			 int count =0;
			@Override
			public void actionPerformed(ActionEvent e) {
			
				count=count+1;
				progressBar.setValue(count);
				
				if(count>100) {
					setVisible(false);
					dispose();
					timer.stop();
					UserScreen userScreen = new UserScreen();
					userScreen.setVisible(true);
					
					
				}
				
				
				
			}
			
			
		}
		 
		 );
		 
		 timer.start();
		
	}
	SplashScreen() {
		setBounds(100, 100, 815, 486);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.CYAN);
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("Arial", Font.PLAIN, 14));
		progressBar.setBounds(174, 354, 485, 25);
		getContentPane().add(progressBar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SplashScreen.class.getResource("/pics/chitchat1.jpg")));
		lblNewLabel.setBounds(10, 10, 781, 429);
		getContentPane().add(lblNewLabel);
		setVisible(true);
	}
}
