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
	public static void main(String[] args) {
		momIP = args[0];
		username = args[1];
		chatRoom = args[2];
	}
	
	@Override
	public void run(){
		System.out.println(chat.getMessages());
	}
	
	public static void printMessage(String msg){
		System.out.println(msg);
	}

}
