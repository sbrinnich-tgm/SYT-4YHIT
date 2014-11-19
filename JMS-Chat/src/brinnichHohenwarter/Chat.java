package brinnichHohenwarter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Chat implements MessageListener{
	
	private MOMConnection con;
	
	public Chat(MOMConnection con) {
		this.con = con;
		this.init();
	}
	
	private void init(){
		try {
			con.getConsumer().setMessageListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(Message msg) {
		TextMessage text = (TextMessage)msg;
		try {
			User.printMessage(text.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
