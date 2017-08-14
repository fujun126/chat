package com.jiaoxue.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

	
	 Properties   pro=new Properties();
	 File file=new File("file/default.properties");
	 
	 
     public PropertiesUtil(){
    	 
    	 try {
    		 if(!file.getParentFile().exists()){
    			 file.getParentFile().mkdirs();
    		 }
    		 
    		 if(!file.exists()){
    			 pro.setProperty("name", "admin");
    			 pro.store(new FileOutputStream(file),"");
    		 }
			pro.load(new FileInputStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	 System.out.println(pro.getProperty("name"));
     }
	
	public  String getString(String name){
		return pro.getProperty(name);
	}
	
	

	
	
	
}
