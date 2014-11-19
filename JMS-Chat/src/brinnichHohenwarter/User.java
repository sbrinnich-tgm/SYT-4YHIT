package brinnichHohenwarter;

public class User extends Thread {
	
	private static String username;
	private static String momIP;
	
	private Chat chat;
	
	public User() {
		Chat chat = new Chat(this);
	}
	

	public static void main(String[] args) {
		
	}
	
	@Override
	public void run(){
		System.out.println(chat.getMessages());
	}

}
