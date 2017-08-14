package com.jiaoxue.ui;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jiaoxue.pojo.User;
import com.jiaoxue.socket.MessageSend;
import com.jiaoxue.socket.PrivateMessageSend;
import com.jiaoxue.util.IconUtil;
import com.jiaoxue.util.PropertiesUtil;

/**
 * @author 付军
 * @version 1.0
 * @date 2017年8月13日 下午11:06:12
 */
public class MainUi extends JFrame implements UiInterface,Runnable{

	private MessageSend ms = new MessageSend();
	private JPanel mianPanel; // 主面板
	private String name = "昵称";
    private Map<String, JLabel> map=new HashMap<String,JLabel>();
	
	
	public MainUi() {

		name = new PropertiesUtil().getString("name");
		this.init();

		ms.loginSend(ms.getLongString(name));
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				ms.loginSend(ms.getOutString(name));
			}
		});
	}

	@Override
	public void init() {

		// 设置标题
		this.setTitle("教学软件");
		// 定义一个高度
		int hight = 800;
		// 设置窗体的大小
		this.setSize(260, hight);

		this.addComponent();
		this.addListener();
		// 设置点击关闭按钮后程序退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗体显示
		this.setVisible(true);

		Thread t1 = new Thread(this);
		t1.start();
		Thread  t2=new Thread(PrivateMessageSend.getps());
		t2.start();
	}

	@Override
	public void addComponent() {

		this.add(this.getMianPanel());
	}

	@Override
	public void addListener() {

	}

	public JPanel getMianPanel() {
		if (mianPanel == null) {
			mianPanel = new JPanel();
		}
		return mianPanel;
	}

	public void setMianPanel(JPanel mianPanel) {
		this.mianPanel = mianPanel;
	}

	@Override
	public void run() {
		while (true) {
			String msg = ms.readMessage();
			System.out.println(msg);
			// 分割字符串
			String[] msga = msg.split(":");
			
			
			// 判断该消息是不是 登陆消息
			if ( msga[0].equals("login")) {
				ImageIcon touxiangIcon = new ImageIcon("file/a.jpg");
				touxiangIcon.setImage(touxiangIcon.getImage()
						.getScaledInstance(60, 60, Image.SCALE_DEFAULT));
				JLabel person = new JLabel(msga[1]+":"+msga[2], touxiangIcon, 0);
				this.addJlabel(person, msg);
				map.put(msga[2], person);
				
				User user=new User();
				user.setIp(msga[2]);
				user.setName(msga[1]);
				
				
				MapChatUi.userMaps.put(msga[2], user);
				getMianPanel().add(person);
				// 判断接受到的ip地址是不是为空 或者是自己的ip的地址 如果是的话 就不在发送消息了
				if (msga[2] != null && msga[3].equals("T")
						&& !msga[2].equals(ms.getIp())) {
					ms.sendMessage(msga[2], "login:" + name + ":"+ms.getIp()+":F");
				}
			} else {
                //删除指定的组件
				getMianPanel().remove(map.get(msga[2]));
			}
			getMianPanel().updateUI();
		}

	}
  
	//给标签元素添加事件  
	public void   addJlabel(Component  com,final String msg){
		com.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] msga = msg.split(":");
				User user=MapChatUi.userMaps.get(msga[2]);
				ChatUi cu=MapChatUi.uiMaps.get(user.getIp());
				if(cu==null){
			      cu=new ChatUi(user);
				MapChatUi.uiMaps.put(user.getIp(),cu);
				}else{
					cu.setVisible(true);
				}
			}
			
		});
		
		
	}
	
}
