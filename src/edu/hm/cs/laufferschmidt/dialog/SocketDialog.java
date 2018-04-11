package edu.hm.cs.laufferschmidt.dialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import edu.hm.cs.laufferschmidt.Dialog;
import edu.hm.cs.laufferschmidt.Parameter;

/**
 * Klasse die die Kommunikation mit den Spielern verwaltet. Dateneingabe und
 * Ausgabe findet ueber Netzwerk statt.
 * 
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public class SocketDialog implements Dialog {


	/**
	 * Socket verbunden zu Player A.
	 */
	private Socket connectA;
	/**
	 * Socket verbunden zu Player B.
	 */
	private Socket connectB;
	/**
	 * Bool ob Player A oder Player B angesprochen wird.
	 */
	private boolean playA;
	
	/**
	 * Port auf dem Server fuer Spieler A hoert.
	 */
	private final int portSpielerA = 2001;
	
	/**
	 * Port auf dem Server fuer Spieler B hoert.
	 */
	private final int portSpielerB = 2002;
	
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
		try(ServerSocket playerA = new ServerSocket(portSpielerA);
				ServerSocket playerB = new ServerSocket(portSpielerB)){
			connectA = playerA.accept();
			System.out.println("A connected");
			connectB = playerB.accept();
			System.out.println("B connected");
		}
			
		readerA = new BufferedReader(new InputStreamReader(connectA.getInputStream()));
		readerB = new BufferedReader(new InputStreamReader(connectB.getInputStream()));
		
		writerA = new BufferedWriter(new OutputStreamWriter(connectA.getOutputStream()));
		writerB = new BufferedWriter(new OutputStreamWriter(connectB.getOutputStream()));
		
	}

	@Override
	public int getNumber() throws IOException {
		int result = 0;
		if (playA) {
			System.out.println("bereit zu lesen von A");
//			BufferedReader readerA = new BufferedReader(new InputStreamReader(connectA.getInputStream()));
			result = readerA.read() - '0';
		} else {
//			BufferedReader readerB = new BufferedReader(new InputStreamReader(connectB.getInputStream()));
			result = readerB.read() - '0';
		}

		return result;

	}

	@Override
	public void askForNumber(String player, Parameter para) throws IOException {
		if (player.toLowerCase().equals("a")) {
//			BufferedWriter writerA = new BufferedWriter(new OutputStreamWriter(connectA.getOutputStream()));
			writeTo(writerA, "Player " + player.toUpperCase() + ", your choice " + para.toString() + '\n');
			playA = true;
		} else {
//			BufferedWriter writerB = new BufferedWriter(new OutputStreamWriter(connectB.getOutputStream()));
			writeTo(writerB,"Player " + player.toUpperCase() + ", your choice " + para.toString() + '\n');
			playA = false;
		}
	}

	@Override
	public void endOfRound(int roundsPlayed, int playerAScore, int playerBScore) throws IOException {
//		BufferedWriter writerA = new BufferedWriter(new OutputStreamWriter(connectA.getOutputStream()));
		writeTo(writerA, "Round " + roundsPlayed + ", Player A: " + playerAScore + ", Player B: " + playerBScore + '\n');

//		BufferedWriter writerB = new BufferedWriter(new OutputStreamWriter(connectB.getOutputStream()));
		writeTo(writerB, "Round " + roundsPlayed + ", Player A: " + playerAScore + ", Player B: " + playerBScore + '\n');
	}

	@Override
	public void printWinner(String winner) throws IOException {
//		BufferedWriter writerA = new BufferedWriter(new OutputStreamWriter(connectA.getOutputStream()));
		writeTo(writerA, winner + '\n');
		
//		BufferedWriter writerB = new BufferedWriter(new OutputStreamWriter(connectB.getOutputStream()));
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
