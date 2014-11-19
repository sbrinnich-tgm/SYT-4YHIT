package brinnichHohenwarter;

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

	public void readMails() {
		con.setSubject(User.userip);
		User.printMessage(getMessages());
	}

	/**
	 * Gibt die MOM Connection zurueck
	 * @return MOM Connection
	 */
	public MOMConnection getConnection() {
		return con;
	}

	private String getMessages(){
		TextMessage text = null;
		String mails = "";
		while(true){
			
			try {
				text = (TextMessage) con.getConsumer().receiveNoWait();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			if(text != null){
				try {
					mails += text.getText() + "\n";
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}else{
				break;
			}
			
		}
		return mails;
	}
	
}
