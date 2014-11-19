package brinnichHohenwarter;

public class User extends Thread {
	
	private static String username;
	private static String momIP;
	private static String chatRoom;
	
	private Chat chat;
	private MOMConnection con;
	
	public User(String momIP, String username, String chatRooom) {
		MOMConnection conChat = new MOMConnection(momIP, chatRoom);
		Chat chat = new Chat(conChat);
	}
	
	@Override
	public void run(){
		System.out.println(chat.getMessages());
	}
	
	public static void printMessage(String msg){
		System.out.println(msg);
	}

}
