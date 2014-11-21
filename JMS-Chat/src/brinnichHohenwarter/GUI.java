package brinnichHohenwarter;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	private JTextField chatInput;
	private JTextArea chatMsg;
	private JButton chatSend;
	
	private JFrame mailFrame;
	private JTextField mailIP;
	private JTextArea mailMsg;

	public GUI(){
		init();
		createChatFrame();
	}
	
	private void init(){
		chatFrame = new JFrame("Chat");
		mailFrame = new JFrame("Mail");
		connectFrame = new JFrame("Connect");
		
		this.connectIP = new JTextField();
		this.connectUsername = new JTextField();
		this.connectChatroom = new JTextField();
		
		this.chatInput = new JTextField();
		this.chatMsg = new JTextArea();
		this.chatSend = new JButton("Senden");
		
		this.mailIP = new JTextField();
		this.mailMsg = new JTextArea();
	}
	
	private void createConnectFrame(){
		
	}
	
	private void createChatFrame(){
		this.chatInput.setMinimumSize(new Dimension(400,28));
		this.chatInput.setMaximumSize(new Dimension(400,28));
		this.chatMsg.setMinimumSize(new Dimension(400,380));
		this.chatMsg.setMaximumSize(new Dimension(400,380));

		this.chatInput.addActionListener(this);
		this.chatSend.addActionListener(this);
		
		chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatFrame.setSize(400, 400);
		chatFrame.setResizable(false);
		
		JPanel pSend = new JPanel();
		pSend.setLayout(new BoxLayout(pSend,BoxLayout.LINE_AXIS));
		pSend.add(this.chatInput);
		pSend.add(Box.createRigidArea(new Dimension(30,0)));
		pSend.add(this.chatSend);
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS));
		p.add(Box.createRigidArea(new Dimension(0,10)));
		p.add(this.chatMsg);
		p.add(Box.createRigidArea(new Dimension(0,5)));
		p.add(pSend);
		p.add(Box.createRigidArea(new Dimension(0,10)));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.LINE_AXIS));
		mainPanel.add(Box.createRigidArea(new Dimension(10,0)));
		mainPanel.add(p);
		mainPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		chatFrame.add(mainPanel);
		
		chatFrame.setVisible(true);
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
