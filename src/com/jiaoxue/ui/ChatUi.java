package com.jiaoxue.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream.GetField;
import java.text.MessageFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jiaoxue.pojo.User;
import com.jiaoxue.socket.MessageSend;
import com.jiaoxue.socket.PrivateMessageSend;
import com.jiaoxue.util.DateUtil;
import com.jiaoxue.util.IpUtil;
/**
 * 
 * @author 付军
 * @version 1.0
 * @date 2017年8月14日 上午11:57:33
 */
public class ChatUi extends JFrame implements UiInterface{
	
  private JPanel        jPanel=new JPanel();
  private  JTextArea    jta=new JTextArea();
  private  JLabel       pic;
  private  JScrollPane  jsp=new JScrollPane(jta);
  private  JTextField   jtf=new JTextField(20);
  private  JButton      send=new JButton("发送");
  private   User  user;
   public 	 ChatUi(User user){
	   this.user=user;
		init();
	}
	@Override
	public void init() {
		this.setSize(400, 600);
		
		this.addComponent();
		this.addListener();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void addComponent() {
	    this.add(getPic(),BorderLayout.NORTH);
		this.add(jsp);
		
		jPanel.add(jtf);
		jPanel.add(send);
		this.add(jPanel,BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				send();
			}
		});
	}
	public JLabel getPic() {
		if(pic==null){
		  pic=new JLabel(user.getIp());
		}
		return pic;
	}
	public void setPic(JLabel pic) {
		this.pic = pic;
	}

	
	public void  setMsg(String msg){
		jta.append(msg+"\r\n");
		JScrollBar jsbBar= jsp.getVerticalScrollBar();
		jsbBar.setValue(jsbBar.getMaximum());
	}
	
	public void send(){
		System.out.println("haha");
		String msg=jtf.getText();
		msg=IpUtil.getIp()+":"+msg+"  "+DateUtil.getDateTime();
		PrivateMessageSend.getps().sendMessage(user.getIp(), msg);
		jta.append(jtf.getText()+"\r\n");
		jtf.setText("");
	}
}
