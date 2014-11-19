package brinnichHohenwarter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Implementiert die Funktionen eines Chatrooms
 * User koennen Nachrichten versenden und empfangen
 * 
 * @author niklas hohenwarter
 * @author selina brinnich
 */
public class Chat implements MessageListener{
	
	private MOMConnection con;
	
	/**
	 * Initialisierung fuer den Chat
	 * 
	 * @param con Verbindung zum MOM
	 */
	public Chat(MOMConnection con) {
		this.con = con;
		this.init();
	}
	
	/**
	 * Initialisiert den MessageListener
	 */
	private void init(){
		try {
			con.getConsumer().setMessageListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gibt beim Erhalt einer Nachricht diese auf der Konsole aus
	 */
	@Override
	public void onMessage(Message msg) {
		TextMessage text = (TextMessage)msg;
		try {
			User.printMessage(text.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sendet eine Nachricht an den Chatroom
	 * @param text Nachrichtentext
	 */
	public void sendMessage(String text){
		try {
			TextMessage msg = con.getSession().createTextMessage(text);
			con.getProducer().send(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gibt die MOM Connection zurück
	 * @return MOM Connection
	 */
	public MOMConnection getConnection() {
		return con;
	}

}
