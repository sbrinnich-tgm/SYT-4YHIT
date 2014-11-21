package brinnichHohenwarter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI implements UserInterface, ActionListener{
	
	private User user;
	
	private JFrame connectFrame;
	private JTextField connectIP;
	private JTextField connectUsername;
	private JTextField connectChatroom;
	private JFrame chatFrame;
	private JFrame mailFrame;
	private JTextField chatInput;
	private JTextArea chatMsg;
	private JTextField mailIP;
	private JTextArea mailMsg;

	public GUI(){
		init();
	}
	
	private void init(){
		chatFrame = new JFrame("Chat");
		mailFrame = new JFrame("Mail");
		connectFrame = new JFrame("Connect");
		this.connectIP = new JTextField();
		this.connectUsername = new JTextField();
		this.connectChatroom = new JTextField();
		this.chatInput = new JTextField();
		this.mailIP = new JTextField();
		this.chatMsg = new JTextArea();
		this.mailMsg = new JTextArea();
	}
	
	private void createConnectFrame(){
		
	}
	
	private void createChatFrame(){
		
	}
	
	private void createMailFrame(){
		
	}
	
	private void connect(String momIP, String username, String chatRoom){
		user = new User(momIP, username, chatRoom, this);
	}

	@Override
	public String input() {
		return null;
	}

	@Override
	public void output(String msg) {
		chatMsg.setText(chatMsg.getText()+"\n"+msg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
