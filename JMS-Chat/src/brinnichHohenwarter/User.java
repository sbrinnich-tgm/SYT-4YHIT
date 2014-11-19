package brinnichHohenwarter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class User extends Thread {
	
	private static String username;
	private static String momIP;
	private static String chatRoom;
	
	private Chat chat;
	private MOMConnection con;
	
	public User(String momIP, String username, String chatRoom) {
		this.momIP = momIP;
		this.username = username;
		this.chatRoom = chatRoom;
		MOMConnection conChat = new MOMConnection(momIP, chatRoom);
		chat = new Chat(conChat);
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
	
	@Override
	public void run(){
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		boolean running = true;
		String msg;
		while(running){
			msg = readLine(bufferRead);
			if(msg.equals("EXIT")){
				System.out.println("Chatroom wird verlassen...");
				System.exit(0);
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
		return username + " [" + getIp() + "]: " + msg;
	}
	
	/**
	 * Gibt eine Nachricht auf der Konsole aus
	 * @param msg die Nachricht, die ausgegeben wird
	 */
	public static void printMessage(String msg){
		System.out.println(msg);
	}

}
