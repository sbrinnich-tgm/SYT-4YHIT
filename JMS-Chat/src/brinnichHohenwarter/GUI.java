package brinnichHohenwarter;

import java.awt.Component;
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

/**
 * Eine GUI zur Darstellung von Chatnachrichten und Mails
 * @author Selina Brinnich
 * @author Niklas Hohenwarter
 * @version 2014-11-23
 *
 */
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
	
	private JFrame mailSendFrame;
	private JTextField mailIP;
	private JTextArea mailText;
	private JButton mailSend;
	
	private JFrame mailReadFrame;
	private JTextArea mailMsg;
	private JButton connectCon;

	/**
	 * Initialisiert die GUI und zeigt ein Verbindungs-Frame an
	 */
	public GUI(){
		init();
		connectFrame.setVisible(true);
	}
	
	/**
	 * Initialisiert alle Attribute und erzeugt alle Frames
	 */
	private void init(){
		connectFrame = new JFrame("Connect");
		chatFrame = new JFrame("Chat");
		mailSendFrame = new JFrame("Mail to");
		mailReadFrame = new JFrame("Mailbox");
		
		this.connectIP = new JTextField();
		this.connectUsername = new JTextField();
		this.connectChatroom = new JTextField();
		this.connectCon = new JButton("Connect");
		
		this.chatInput = new JTextField();
		this.chatMsg = new JTextArea();
		this.chatSend = new JButton("Senden");
		
		this.mailIP = new JTextField();
		this.mailText = new JTextArea();
		this.mailSend = new JButton("Senden");
		
		this.mailMsg = new JTextArea();
		
		createConnectFrame();
		createChatFrame();
		createMailSendFrame();
		createMailReadFrame();
	}
	
	/**
	 * Erzeugt ein Frame, in dem man sich zu einer MOM bzw. zu einem Chatroom verbinden kann
	 */
	private void createConnectFrame(){
		connectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		connectFrame.setSize(400,180);
		connectFrame.setResizable(false);
		connectFrame.setLocationRelativeTo(null);
		
		JPanel rootPanel = new JPanel();
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));
		rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel momPanel = new JPanel();
		JPanel roomPanel = new JPanel();
		JPanel userPanel = new JPanel();
		JPanel connectPanel = new JPanel();
		momPanel.setLayout(new BoxLayout(momPanel,BoxLayout.LINE_AXIS));
		momPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		roomPanel.setLayout(new BoxLayout(roomPanel,BoxLayout.LINE_AXIS));
		roomPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		userPanel.setLayout(new BoxLayout(userPanel,BoxLayout.LINE_AXIS));
		userPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		connectPanel.setLayout(new BoxLayout(connectPanel,BoxLayout.LINE_AXIS));
		connectPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		momPanel.add(new JLabel("Server:"));
		momPanel.add(Box.createRigidArea(new Dimension(33,0)));
		connectIP.setMinimumSize(new Dimension(300,28));
		connectIP.setMaximumSize(new Dimension(300,28));
		momPanel.add(connectIP);
		
		roomPanel.add(new JLabel("Chatroom:"));
		roomPanel.add(Box.createRigidArea(new Dimension(15,0)));
		connectChatroom.setMinimumSize(new Dimension(300,28));
		connectChatroom.setMaximumSize(new Dimension(300,28));
		roomPanel.add(connectChatroom);
		
		userPanel.add(new JLabel("Username:"));
		userPanel.add(Box.createRigidArea(new Dimension(12,0)));
		connectUsername.setMinimumSize(new Dimension(300,28));
		connectUsername.setMaximumSize(new Dimension(300,28));
		userPanel.add(connectUsername);
		
		connectPanel.add(Box.createRigidArea(new Dimension(150,0)));
		connectPanel.add(connectCon);
		connectCon.setMinimumSize(new Dimension(100,28));
		connectCon.setMaximumSize(new Dimension(100,28));
		connectCon.addActionListener(this);
		connectCon.setActionCommand("connect");
		
		rootPanel.add(momPanel);
		rootPanel.add(roomPanel);
		rootPanel.add(userPanel);
		rootPanel.add(Box.createRigidArea(new Dimension(0,10)));
		rootPanel.add(connectPanel);

		connectFrame.add(rootPanel);
	}
	
	/**
	 * Erzeugt ein Frame, in dem Chatnachrichten angezeigt und gesendet werden koennen
	 */
	private void createChatFrame(){
		this.chatInput.setMinimumSize(new Dimension(400,28));
		this.chatInput.setMaximumSize(new Dimension(400,28));
		this.chatMsg.setMinimumSize(new Dimension(400,380));
		this.chatMsg.setMaximumSize(new Dimension(400,380));

		this.chatInput.addActionListener(this);
		this.chatInput.setActionCommand("chatInput");
		this.chatSend.addActionListener(this);
		this.chatSend.setActionCommand("chatSend");
		
		this.chatMsg.setEditable(false);
		
		chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatFrame.setSize(400, 400);
		chatFrame.setResizable(false);
		chatFrame.setLocationRelativeTo(null);
		
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
	}
	
	/**
	 * Erzeugt ein Frame, in dem man eine Mail schicken kann
	 */
	private void createMailSendFrame(){
		mailSendFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mailSendFrame.setSize(400, 400);
		mailSendFrame.setResizable(false);
		mailSendFrame.setLocationRelativeTo(chatFrame);
		
		this.mailText.setMinimumSize(new Dimension(400,380));
		this.mailText.setMaximumSize(new Dimension(400,380));
		this.mailIP.setMinimumSize(new Dimension(400,28));
		this.mailIP.setMaximumSize(new Dimension(400,28));

		this.mailSend.addActionListener(this);
		this.mailSend.setActionCommand("mailSend");
		
		JPanel pSend = new JPanel();
		pSend.setLayout(new BoxLayout(pSend,BoxLayout.LINE_AXIS));
		pSend.add(new JLabel("Empfaenger-IP:"));
		pSend.add(Box.createRigidArea(new Dimension(20,0)));
		pSend.add(this.mailIP);
		pSend.add(Box.createRigidArea(new Dimension(30,0)));
		pSend.add(this.mailSend);
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS));
		p.add(pSend);
		p.add(Box.createRigidArea(new Dimension(0,10)));
		p.add(this.mailText);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.LINE_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		mainPanel.add(p);
		
		mailSendFrame.add(mainPanel);
	}
	
	/**
	 * Erzeugt ein Frame, in dem man seine Mails lesen kann
	 */
	private void createMailReadFrame(){
		mailReadFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mailReadFrame.setSize(400, 400);
		mailReadFrame.setResizable(false);
		mailReadFrame.setLocationRelativeTo(chatFrame);
		
		this.mailMsg.setMinimumSize(new Dimension(400,400));
		this.mailMsg.setMaximumSize(new Dimension(400,400));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.LINE_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		mainPanel.add(mailMsg);
		
		mailReadFrame.add(mainPanel);
	}
	
	/**
	 * Stellt die Verbindung zur MOM her
	 * @param momIP IP der MOM
	 * @param username Username, den der User im Chat verwenden moechte
	 * @param chatRoom Chatroom, in den der User beitreten moechte
	 */
	private void connect(String momIP, String username, String chatRoom){
		user = new User(momIP, username, chatRoom, this);
	}

	/**
	 * Gibt neue Chatnachrichten im Chatframe aus
	 */
	@Override
	public void output(String msg) {
		if(msg.equals("init")){
			output("Um deine Mails zu lesen gib bitte /mailbox ein.");
			output("Um eine Mail zu senden gib /mail ein.");
		}else{
			chatMsg.setText(chatMsg.getText()+"\n"+msg);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		if(c.equals("connect")){
			connect(connectIP.getText(),connectUsername.getText(),connectChatroom.getText());
			connectFrame.setVisible(false);
			chatFrame.setVisible(true);
		}else if(c.equals("chatInput") || c.equals("chatSend")){
			if(chatInput.getText().equalsIgnoreCase("/MAIL")){
				mailSendFrame.setVisible(true);
				chatInput.setText("");
			}else if(chatInput.getText().equalsIgnoreCase("/MAILBOX")){
				String[] mails = user.readMails();
				String o = "";
				for(int i = 0; i < mails.length; i++){
					o += mails[i]+"\n";
				}
				mailMsg.setText(o);
				chatInput.setText("");
				mailReadFrame.setVisible(true);
			}else{
				user.sendMessage(chatInput.getText());
				chatInput.setText("");
			}
		}else if(c.equals("mailSend")){
			user.sendMail(mailText.getText(), mailIP.getText());
			mailText.setText("");
			mailIP.setText("");
			mailSendFrame.setVisible(false);
		}
	}
	
}
