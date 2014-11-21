package brinnichHohenwarter;

import java.awt.Dimension;
import java.awt.GridLayout;
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
	private JButton connectCon;

	public GUI(){
		init();
		createConnectFrame();
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
		this.connectCon = new JButton("Connect");
	}
	
	private void createConnectFrame(){
		connectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		connectFrame.setSize(400,400);
		connectFrame.setResizable(false);
		
		JPanel rootPanel = new JPanel();
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));
		JPanel momPanel = new JPanel();
		JPanel roomPanel = new JPanel();
		JPanel userPanel = new JPanel();
		momPanel.setLayout(new BoxLayout(momPanel,BoxLayout.LINE_AXIS));
		roomPanel.setLayout(new BoxLayout(roomPanel,BoxLayout.LINE_AXIS));
		userPanel.setLayout(new BoxLayout(userPanel,BoxLayout.LINE_AXIS));
		
		momPanel.add(new JLabel("Server:"));
		momPanel.add(Box.createRigidArea(new Dimension(20,0)));
		connectIP.setMinimumSize(new Dimension(300,28));
		connectIP.setMaximumSize(new Dimension(300,28));
		momPanel.add(connectIP);

		
		roomPanel.add(new JLabel("Chatroom:"));
		roomPanel.add(Box.createRigidArea(new Dimension(20,0)));
		connectChatroom.setMinimumSize(new Dimension(300,28));
		connectChatroom.setMaximumSize(new Dimension(300,28));
		roomPanel.add(connectChatroom);
		
		userPanel.add(new JLabel("User:"));
		userPanel.add(Box.createRigidArea(new Dimension(20,0)));
		connectUsername.setMinimumSize(new Dimension(300,28));
		connectUsername.setMaximumSize(new Dimension(300,28));
		userPanel.add(connectUsername);
		
		rootPanel.add(momPanel);
		rootPanel.add(roomPanel);
		rootPanel.add(userPanel);
		rootPanel.add(connectCon);

		connectFrame.add(rootPanel);
		connectFrame.setVisible(true);
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
