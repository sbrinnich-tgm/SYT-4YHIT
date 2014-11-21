package brinnichHohenwarter;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Repraesentiert den User eines Chats
 * 
 * @author niklas hohenwarter
 * @author selina brinnich
 * @version 2014-11-19
 */
public class User extends Thread {
	
	private UserInterface userIf;
	
	protected static String username;
	protected static String userip;
	
	private Chat chat;

	private Mail mail;
	
	/**
	 * Erstellt einen User nach den Uebergebenen Parametern
	 * @param momIP IP des MessageBrokers
	 * @param username Benutzername im Chat
	 * @param chatRoom Name des/der Topics/Queue
	 */
	public User(String momIP, String username, String chatRoom, UserInterface userIf) {
		super("User");
		this.userIf = userIf;
		User.username = username;
		userip = getIp();
		MOMConnection conChat = new MOMConnection(momIP, chatRoom,true);
		MOMConnection conMail = new MOMConnection(momIP, false);
		chat = new Chat(conChat,this);
		mail = new Mail(conMail);
		output("Chatroom wird betreten...");
	}
	
	/**
	 * Gibt die IP-Adresse des Users zurueck
	 * @see http://stackoverflow.com/a/18945245
	 * @return die aktuelle IP-Adresse des Users, der diese Anwendung verwendet, in einem String
	 */
	public static String getIp(){
	    String ipAddress = null;
	    Enumeration<NetworkInterface> net = null;
	    try {
	        net = NetworkInterface.getNetworkInterfaces();
	    } catch (SocketException e) {
	        throw new RuntimeException(e);
	    }

	    while(net.hasMoreElements()){
	        NetworkInterface element = net.nextElement();
	        Enumeration<InetAddress> addresses = element.getInetAddresses();
	        while (addresses.hasMoreElements()){
	            InetAddress ip = addresses.nextElement();
	            if (ip instanceof Inet4Address){
	                if (ip.isSiteLocalAddress()){
	                    ipAddress = ip.getHostAddress();
	                }
	            }
	        }
	    }
	    return ipAddress;
	}
	
	public void input(String msg){
		
	}
	
	public String[] readMails(){
		return mail.readMails();
	}
	
	public void sendMail(String msg, String dest){
		mail.sendMail(msg, dest);
	}
	
	public void sendMessage(String msg){
		chat.sendMessage(formatMessage(msg));
	}
	
	public void output(String msg){
		userIf.output(msg);
	}
	
	/**
	 * Formatiert einen String in ein bestimmtes Nachrichten-Format
	 * @param msg die zu formatierende Nachricht
	 * @return ein String im Nachrichten-Format<br> 
	 * <code>Benutzername[IP-Adresse]: Nachricht</code>
	 */
	private String formatMessage(String msg){
		return username + " [" + userip + "]: " + msg;
	}
	
	public void exitChat(){
		userIf.output("Chatroom wird verlassen...");
		mail.getConnection().closeConnection();
		chat.getConnection().closeConnection();
		System.exit(0);
	}

}
