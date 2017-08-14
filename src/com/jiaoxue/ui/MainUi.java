package com.jiaoxue.ui;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jiaoxue.util.IconUtil;
/**
 * @author 付军
 * @version 1.0
 * @date 2017年8月13日 下午11:06:12
 */
public class MainUi  extends  JFrame implements UiInterface{
	
	
	private JPanel   mianPanel; //主面板
	
	public MainUi(){
		this.init();
	}
	
	@Override
	public void init() {
		
		//设置标题
		this.setTitle("教学软件");
		//定义一个高度
		int hight=800;
		//设置窗体的大小
		this.setSize(260,hight);
		
		this.addComponent();
		this.addListener();
		//设置点击关闭按钮后程序退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗体显示
		this.setVisible(true);
		
	}

	@Override
	public void addComponent() {
		ImageIcon touxiangIcon=	new ImageIcon("file/a.jpg");
		touxiangIcon.setImage(touxiangIcon.getImage().getScaledInstance(60, 60,Image.SCALE_DEFAULT));
			
		JLabel  person1=new JLabel("张三丰",touxiangIcon,0);
		person1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			  ChatUi  chatUi= ChatUi.GetChatUi();
			   chatUi.setVisible(true);
				
			}
		});;
		
		this.getMianPanel().add(person1);
		
		this.add(this.getMianPanel());
	}

	@Override
	public void addListener() {
		
		
	}

	public JPanel getMianPanel() {
		if(mianPanel==null){
			mianPanel=new JPanel();
		}
		return mianPanel;
	}

	public void setMianPanel(JPanel mianPanel) {
		this.mianPanel = mianPanel;
	}
   
	
	
	
}
