package brinnichHohenwarter;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class Mail {
	
	private MOMConnection con;
	
	public Mail(MOMConnection conMail) {
		con = conMail;
	}

	public void sendMail(String text, String dest) {
		TextMessage msg;
		try {
			con.setSubject(dest);
			msg = con.getSession().createTextMessage("Mail from " + User.username + "[" + User.userip + "]: " + text);
			con.getProducer().send(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void readMails() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gibt die MOM Connection zurueck
	 * @return MOM Connection
	 */
	public MOMConnection getConnection() {
		return con;
	}
	
}
