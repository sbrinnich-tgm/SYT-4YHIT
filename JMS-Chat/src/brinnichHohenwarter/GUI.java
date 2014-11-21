package brinnichHohenwarter;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	private JButton connectCon;

	public GUI(){
		init();
		createConnectFrame();
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
		this.chatInput.setMinimumSize(new Dimension(400,28));
		this.chatInput.setMaximumSize(new Dimension(400,28));
		this.chatMsg.setMinimumSize(new Dimension(400,380));
		this.chatMsg.setMaximumSize(new Dimension(400,380));

		this.chatInput.addActionListener(this);
		this.chatInput.setActionCommand("chatInput");
		this.chatSend.addActionListener(this);
		this.chatSend.setActionCommand("chatSend");
		
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
		p.add(this.chatMsg);
		p.add(Box.createRigidArea(new Dimension(0,5)));
		p.add(pSend);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.LINE_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		mainPanel.add(p);
		
		chatFrame.add(mainPanel);
		
		chatFrame.setVisible(true);
	}
	
	private void createMailFrame(){
		
	}
	
	private void connect(String momIP, String username, String chatRoom){
		user = new User(momIP, username, chatRoom, this);
	}

	@Override
	public void output(String msg) {
		chatMsg.setText(chatMsg.getText()+"\n"+msg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
