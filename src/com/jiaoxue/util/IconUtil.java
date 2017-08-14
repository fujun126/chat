package com.jiaoxue.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconUtil extends ImageIcon{
 static	IconUtil obj=new IconUtil();
	public static ImageIcon getImageIcon(String path, int width, int height) {
		  if (width == 0 || height == 0) {
		   return new ImageIcon(obj.getClass().getResource(path));
		  }
		  ImageIcon icon = new ImageIcon(obj.getClass().getResource(path));
		  icon.setImage(obj.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT));
		  return icon;
		 }
}
