package brinnichHohenwarter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Mail implements MessageListener{
	
	private MOMConnection con;
	private String mails;
	
	public Mail(MOMConnection conMail) {
		con = conMail;
	}

	public void sendMail(String text) {
		TextMessage msg;
		try {
			msg = con.getSession().createTextMessage(text);
			con.getProducer().send(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void readMails() {
		User.printMessage(mails);
	}

	/**
	 * Gibt die MOM Connection zurück
	 * @return MOM Connection
	 */
	public MOMConnection getConnection() {
		return con;
	}

	@Override
	public void onMessage(Message msg) {
		TextMessage text = (TextMessage)msg;
		mails += text + "\n";
	}
	
}
