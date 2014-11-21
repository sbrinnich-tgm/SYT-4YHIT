package brinnichHohenwarter;

/**
 * Startet den Chat
 * 
 * @author niklas hohenwarter
 * @author selina brinnich
 * @version 2014-11-19
 */
public class Main {
	public static void main(String[] args) {
		new User(args[0],args[1],args[2],new CLI());
	}
}
