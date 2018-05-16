package edu.hm.cs.lauffer.dialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import edu.hm.cs.lauffer.Dialog;
import edu.hm.cs.lauffer.Parameter;

/**
 * Klasse die die Kommunikation mit den Spielern verwaltet. Dateneingabe und
 * Ausgabe findet ueber Netzwerk statt.
 * 
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public class SocketDialog implements Dialog {
	/**
	 * Default fuer char konvertierung.
	 */
	private static final String CHARSET = "US-ASCII";

	/**
	 * Port auf dem Server fuer Spieler A hoert.
	 */
	private static final int PORTPLAYERA = 2001;
	
	/**
	 * Port auf dem Server fuer Spieler B hoert.
	 */
	private static final  int PORTPLAYERB = 2002;
	

	/**
	 * Bool ob Player A oder Player B angesprochen wird.
	 */
	private boolean playA;
	
	
	/**
	 * Reader fuer Input von Player A.
	 */
	private final BufferedReader readerA;
	/**
	 * Reader fuer Input von Player B.
	 */
	private final BufferedReader readerB;

	/**
	 * Writer output an Player A.
	 */
	private final BufferedWriter writerA;
	/**
	 * Writer output an Player A.
	 */
	private final BufferedWriter writerB;
	
	
	/**
	 * Konstruktor, akzeptiert eine Verbindung auf Port 2001, anschliessend auf Port 2002.
	 * @throws IOException SocketException.
	 */
	public SocketDialog() throws IOException {
		final Socket connectA;
		final Socket connectB;
		try(ServerSocket playerA = new ServerSocket(PORTPLAYERA);
				ServerSocket playerB = new ServerSocket(PORTPLAYERB)){
			 connectA = playerA.accept();
			System.out.println("A connected");
			 connectB = playerB.accept();
			System.out.println("B connected");
		}
			
		readerA = new BufferedReader(new InputStreamReader(connectA.getInputStream(), CHARSET));
		readerB = new BufferedReader(new InputStreamReader(connectB.getInputStream(), CHARSET));
		
		writerA = new BufferedWriter(new OutputStreamWriter(connectA.getOutputStream(), CHARSET));
		writerB = new BufferedWriter(new OutputStreamWriter(connectB.getOutputStream(), CHARSET));
		
	}

	@Override
	public int getNumber() throws IOException {
		int result = 0;
		if (playA) {
			System.out.println("bereit zu lesen von A");
			result = readerA.read() - '0';
		} else {
			result = readerB.read() - '0';
		}

		return result;

	}

	@Override
	public void askForNumber(String player, Parameter para) throws IOException {
		if ("a".equalsIgnoreCase(player)) {
			writeTo(writerA, "Player " + player.toUpperCase() + ", your choice " + para.toString() + '\n');
			playA = true;
		} else {
			writeTo(writerB,"Player " + player.toUpperCase() + ", your choice " + para.toString() + '\n');
			playA = false;
		}
	}

	@Override
	public void endOfRound(int roundsPlayed, int playerAScore, int playerBScore) throws IOException {
		writeTo(writerA, "Round " + roundsPlayed + ", Player A: " + playerAScore + ", Player B: " + playerBScore + '\n');

		writeTo(writerB, "Round " + roundsPlayed + ", Player A: " + playerAScore + ", Player B: " + playerBScore + '\n');
	}

	@Override
	public void printWinner(String winner) throws IOException {
		writeTo(writerA, winner + '\n');
		
		writeTo(writerB, winner + '\n');
	}
	
	/**
	 * Schreibt message an den jeweiligen Spieler.
	 * @param writer Writer fuer den Spieler
	 * @param message Nachricht an den Spieler
	 * @throws IOException IOException
	 */
	private void writeTo(BufferedWriter writer, String message) throws IOException{
		writer.write(message);
		writer.flush();
	}
}
