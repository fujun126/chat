package com.jiaoxue.ui;

import java.io.ObjectInputStream.GetField;

import javax.swing.JFrame;

public class ChatUi extends JFrame implements UiInterface{

	private static ChatUi  chatUi=new ChatUi();
	public static ChatUi GetChatUi(){
		
		return chatUi;
	}
	
   private 	 ChatUi(){
		init();
	}
	@Override
	public void init() {
	  
		this.setSize(400, 600);
		
		this.setState(JFrame.NORMAL);
		this.addComponent();
		this.addListener();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		
	}

	@Override
	public void addComponent() {
	
		
	}

	@Override
	public void addListener() {

		
	}

	
	
	
}
