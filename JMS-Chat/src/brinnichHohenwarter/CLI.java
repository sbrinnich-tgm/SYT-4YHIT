package brinnichHohenwarter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI extends Thread implements UserInterface{
	
	BufferedReader bufferedReader;
	User user;

	public CLI(String momIP, String username, String chatRoom){
		this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		user = new User(momIP, username, chatRoom, this);
		this.start();
	}
	
	@Override
	public void run(){
		while(true){
			user.input(input());
		}
	}
	
	/**
	 * Liest eine Zeile von der Konsole ein, sobald der User Enter drueckt
	 * @param reader ein BufferedReader auf die Konsole
	 * @return die eingegebene Zeile
	 */
	@Override
	public String input(){
		String msg = "";
		try{
		    msg = bufferedReader.readLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * Gibt eine Nachricht auf der Konsole aus
	 * @param msg die Nachricht, die ausgegeben wird
	 */
	@Override
	public void output(String msg){
		System.out.println(msg);
	}
	
}
