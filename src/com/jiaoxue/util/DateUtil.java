package com.jiaoxue.util;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil {
	private  static  SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public  static  String  getDateTime(){
		return sdf.format(new Date());
	}
}
