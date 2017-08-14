package com.jiaoxue.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import com.jiaoxue.pojo.User;
import com.jiaoxue.ui.ChatUi;
import com.jiaoxue.ui.MapChatUi;

public class PrivateMessageSend implements Runnable{
   private static PrivateMessageSend  pMessageSend=new PrivateMessageSend();
	private  int  prot=8990;
	private  ServerSocket  ss;
	private  DatagramSocket ds;
	public  PrivateMessageSend(){
		try {
			ds=new DatagramSocket(prot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public static PrivateMessageSend getps(){
		return pMessageSend;
	}
	
	public  void  sendMessage(String ip,String message){
		byte[] b=message.getBytes();
		DatagramPacket dp=new DatagramPacket(b, b.length,new InetSocketAddress(ip, prot));
		try {
			ds.send(dp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public String   readMessage(){
		byte[] b=new byte[1024];
		DatagramPacket dp=new DatagramPacket(b, b.length); 
		try {
			ds.receive(dp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String  msgString=new String(b,0,dp.getLength());
		return msgString;
		
	}

	@Override
	public void run() {
		while(true){
			System.out.println("hahah ");
	     String msg=	readMessage();
	     System.out.println(msg);
	    String ip= msg.substring(0, msg.indexOf(":"));
	    System.out.println(ip);
	    ChatUi cu= MapChatUi.uiMaps.get(ip);
	    System.out.println(cu);
	    if(cu!=null){
	    cu.setMsg(msg.substring(msg.indexOf(":")));
	    }else{
	    	
	      User user=MapChatUi.userMaps.get(ip);
	        cu	=new ChatUi(user);
	        MapChatUi.uiMaps.put(ip, cu);
	        cu.setMsg(msg.substring(msg.indexOf(":")));
	    }
	    
		}
		
	}
	
	
	//获取本机的ip地址 
	public String  getIp(){
     InetAddress addr=null;
     try {
	 addr = InetAddress.getLocalHost();
     } catch (UnknownHostException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
     }
     return addr.getHostAddress().toString();//获得本机IP
		
	}
	

	// 回复上线用户的字符串
	
}
