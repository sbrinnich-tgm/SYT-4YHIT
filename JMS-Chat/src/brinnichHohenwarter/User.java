package brinnichHohenwarter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	public User(String momIP, String username, String chatRoom) {
		super("User");
		User.username = username;
		userip = getIp();
		MOMConnection conChat = new MOMConnection(momIP, chatRoom,true);
		MOMConnection conMail = new MOMConnection(momIP, false);
		chat = new Chat(conChat);
		mail = new Mail(conMail);
		this.start();
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
	
	/**
	 * Erledigt staendig anfallende Arbeiten
	 */
	@Override
	public void run(){
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String msg;
		while(true){
			msg = readLine(bufferRead);
			if(msg.equals("EXIT")){
				exitChat();
			}else if(msg.equalsIgnoreCase("MAILBOX")){
				mail.readMails();
			}else if(msg.contains("MAIL") || msg.contains("mail")){
				String[] s = msg.split(" ");
				mail.sendMail(s[2], s[1]);
			}else{
				chat.sendMessage(formatMessage(msg));
			}
		}
	}
	
	/**
	 * Liest eine Zeile von der Konsole ein, sobald der User Enter drueckt
	 * @param reader ein BufferedReader auf die Konsole
	 * @return die eingegebene Zeile
	 */
	private String readLine(BufferedReader reader){
		String msg = "";
		try{
		    msg = reader.readLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return msg;
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
	
	/**
	 * Gibt eine Nachricht auf der Konsole aus
	 * @param msg die Nachricht, die ausgegeben wird
	 */
	public static void printMessage(String msg){
		System.out.println(msg);
	}
	
	public void exitChat(){
		System.out.println("Chatroom wird verlassen...");
		chat.getConnection().closeConnection();
		mail.getConnection().closeConnection();
		System.exit(0);
	}

}
