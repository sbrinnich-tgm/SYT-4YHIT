package brinnichHohenwarter;

import javax.jms.Message;
import javax.jms.MessageListener;

public class Chat implements MessageListener{
	
	private MOMConnection con;
	
	public Chat(MOMConnection con) {
		this.con = con;
	}

	@Override
	public void onMessage(Message msg) {
		User.printMessage(msg);
	}

}
