package brinnichHohenwarter;

/**
 * Startet den Chat
 * 
 * @author Niklas Hohenwarter
 * @author Selina Brinnich
 * @version 2014-11-22
 */
public class Main {
	public static void main(String[] args) {
		if(args.length>3 || (args.length<3 && args.length>=1)){
			System.out.println("Ungueltige Parameter! Folgendes Format der Parameter ist gefordert: \n"
					+ "<IP-Adresse der MOM> <Username> <Chatroom>");
		}else if(args.length==3){
			new CLI(args[0],args[1],args[2]);
		}else{
			new GUI();
		}
	}
}
