package com.jiaoxue.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtil {

	
	public static  String  getIp(){
	     InetAddress addr=null;
	     try {
		 addr = InetAddress.getLocalHost();
	     } catch (UnknownHostException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
	     }
	     return addr.getHostAddress().toString();//获得本机IP
			
		}
	
}
