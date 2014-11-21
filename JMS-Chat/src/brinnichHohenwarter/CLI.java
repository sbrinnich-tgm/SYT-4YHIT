package brinnichHohenwarter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI implements UserInterface{
	
	BufferedReader bufferedReader;

	public CLI(){
		this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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
