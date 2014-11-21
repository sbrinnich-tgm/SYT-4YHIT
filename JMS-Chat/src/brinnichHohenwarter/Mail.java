package brinnichHohenwarter;

import java.util.ArrayList;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class Mail{
	
	private MOMConnection con;
	
	public Mail(MOMConnection conMail) {
		con = conMail;
	}

	public void sendMail(String text, String dest) {
		TextMessage msg;
		try {
			con.setSubject(dest);
			msg = con.getQueueSession().createTextMessage("Mail from " + User.username + "[" + User.userip + "]: " + text);
			con.getProducer().send(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public String[] readMails() {
		con.setSubject(User.userip);
		String s = " ";
		ArrayList<String> mails = new ArrayList<String>();
		while(s != null){
			s = getNextMail();
			if(s != null){
				mails.add(s);
			}
		}
		
		if(mails.size()<1){
			return new String[]{"Keine neuen Mails."};
		} else {
			String[] m = new String[mails.size()];
			for (int i = 0; i < mails.size(); i++) {
				m[i] = mails.get(i);
			}
			return m;
		}
	}

	/**
	 * Gibt die MOM Connection zurueck
	 * @return MOM Connection
	 */
	public MOMConnection getConnection() {
		return con;
	}

	private String getNextMail(){
		TextMessage text = null;
		String mail = null;

		try {
			text = (TextMessage) con.getConsumer().receiveNoWait();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		if (text != null) {
			try {
				mail = text.getText() + "\n";
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		return mail;
	}
	
}
