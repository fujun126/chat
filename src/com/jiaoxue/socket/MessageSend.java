package com.jiaoxue.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class MessageSend implements Runnable{
   
	private  int  prot=8989;
	private  ServerSocket  ss;
	private  DatagramSocket ds;
	public  MessageSend(){
		try {
			ds=new DatagramSocket(prot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 public  void  loginSend(String msg){
		sendMessage(this.getIp().substring(0,this.getIp().lastIndexOf("."))+".255",msg);
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
	
	
	//上线的字符串
	public  String   getLongString(String name){
		return "login:"+name+":"+getIp()+":T";
	}
	
	//下线的字符串
	public String   getOutString(String name){
		return "out:"+name+":"+getIp()+":T";
	}
	
	// 回复上线用户的字符串
	
}
