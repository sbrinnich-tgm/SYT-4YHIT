package brinnichHohenwarter;

public class User extends Thread {
	
	private static String username;
	private static String momIP;
	private static String chatRoom;
	

	public static void main(String[] args) {
		momIP = args[0];
		username = args[1];
		chatRoom = args[2];
		
		
	}
	
	@Override
	public void run(){
		
	}

}
