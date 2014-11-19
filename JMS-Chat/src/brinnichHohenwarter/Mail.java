package brinnichHohenwarter;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class Mail {
	
	private MOMConnection con;
	
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
		// TODO Auto-generated method stub
		
	}

	
	
}
