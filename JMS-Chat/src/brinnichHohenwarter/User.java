package brinnichHohenwarter;

public class User extends Thread {
	
	private static String username;
	private static String momIP;
	private static String chatRoom;
	
	private Chat chat;
	
	public User() {
		Chat chat = new Chat(this);
	}
	
	@Override
	public void run(){
		System.out.println(chat.getMessages());
	}

}
