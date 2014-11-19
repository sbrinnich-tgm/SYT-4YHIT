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

	public void sendMail(String text, String dest) {
		TextMessage msg;
		try {
			con.setSubject(dest);
			msg = con.getSession().createTextMessage("Mail from " + User.username + "[" + User.userip + "]: " + text);
			con.getProducer().send(msg);
			con.closeConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void readMails() {
		User.printMessage(mails);
	}

	/**
	 * Gibt die MOM Connection zurueck
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
