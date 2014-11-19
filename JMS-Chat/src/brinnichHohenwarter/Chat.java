package brinnichHohenwarter;

import javax.jms.Message;
import javax.jms.MessageListener;

public class Chat implements MessageListener{
	
	private String messages;
	
	private User user
	
	public Chat(User user) {
		this.user = user;
	}

	@Override
	public void onMessage(Message arg0) {
		messages += user.
	}

	public String getMessages() {
		// TODO Auto-generated method stub
		return null;
	}

}
