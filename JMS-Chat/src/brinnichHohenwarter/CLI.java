package brinnichHohenwarter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CLI dient der Darstellung von Chatnachrichten und Mails in der Konsole
 * @author Selina Brinnich
 * @author Niklas Hohenwarter
 * @version 2014-11-23
 *
 */
public class CLI extends Thread implements UserInterface{
	
	BufferedReader bufferedReader;
	User user;

	/**
	 * Initialisiert und stellt eine Verbindung zur MOM her
	 * @param momIP die IP der MOM, zu dem eine Verbindung hergestellt werden soll
	 * @param username der Username, den der User im Chat verwenden moechte
	 * @param chatRoom der Chatroom, dem der User beitreten moechte
	 */
	public CLI(String momIP, String username, String chatRoom){
		this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		user = new User(momIP, username, chatRoom, this);
		this.start();
	}
	
	@Override
	public void run(){
		String msg;
		while(true){
			msg = input();
			if(msg.equalsIgnoreCase("/EXIT")){
				user.exitChat();
			}else if(msg.equalsIgnoreCase("/MAILBOX")){
				String[] mails = user.readMails();
				String o = "";
				for(int i = 0; i < mails.length; i++){
					o += mails[i]+"\n";
				}
				output(o);
			}else if(msg.contains("/MAIL") || msg.contains("/mail")){
				String[] s = msg.split(" ");
				user.sendMail(s[2], s[1]);
			}else{
				user.sendMessage(msg);
			}
		}
	}
	
	/**
	 * Liest eine Zeile von der Konsole ein, sobald der User Enter drueckt
	 * @param reader ein BufferedReader auf die Konsole
	 * @return die eingegebene Zeile
	 */
	public String input(){
		String msg = "";
		try{
		    msg = bufferedReader.readLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * Gibt eine Nachricht auf der Konsole aus
	 * @param msg die Nachricht, die ausgegeben wird
	 */
	@Override
	public void output(String msg){
		if(msg.equals("init")){
			output("Um deine Mails zu lesen gib bitte /mailbox ein.");
			output("Um eine Mail zu senden gib /mail <empfaenger-ip> <nachricht> ein.");
		}else{
			System.out.println(msg);
		}
	}
	
}
