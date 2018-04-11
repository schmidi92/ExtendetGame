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
	 * ServerSocket auf Port 2001.
	 */
	ServerSocket playerA;
	/**
	 * ServerSocket aud Port 2002.
	 */
	ServerSocket playerB;
	/**
	 * Socket verbunden zu Player A;
	 */
	Socket connectA;
	/**
	 * Socket verbunden zu Player B.
	 */
	Socket connectB;
	/**
	 * Bool ob Player A oder Player B angesprochen wird.
	 */
	boolean playA;

	/**
	 * Konstruktor, akzeptiert eine Verbindung auf Port 2001, anschliessend auf Port 2002.
	 * @throws IOException SocketException.
	 */
	public SocketDialog() throws IOException {
		playerA = new ServerSocket(2001);
		connectA = playerA.accept();
		System.out.println("A connected");
		playerB = new ServerSocket(2002);
		connectB = playerB.accept();
		System.out.println("B connected");
	}

	@Override
	public int getNumber() throws IOException {
		int result = 0;
		if (playA) {
			System.out.println("bereit zu lesen von A");
			BufferedReader readerA = new BufferedReader(new InputStreamReader(connectA.getInputStream()));
			result = readerA.read() - '0';
			System.out.println("A Number" + result);
		} else {
			BufferedReader readerB = new BufferedReader(new InputStreamReader(connectB.getInputStream()));
			result = readerB.read() - '0';
			System.out.println("B Number" + result);
		}

		return result;

	}

	@Override
	public void askForNumber(String player, Parameter para) throws IOException {
		if (player.toLowerCase().equals("a")) {
			BufferedWriter writerA = new BufferedWriter(new OutputStreamWriter(connectA.getOutputStream()));
			writeTo(writerA, "Player " + player.toUpperCase() + ", your choice " + para.toString() + '\n');
			playA = true;
		} else {
			BufferedWriter writerB = new BufferedWriter(new OutputStreamWriter(connectB.getOutputStream()));
			writeTo(writerB,"Player " + player.toUpperCase() + ", your choice " + para.toString() + '\n');
			playA = false;
		}
	}

	@Override
	public void endOfRound(int roundsPlayed, int playerAScore, int playerBScore) throws IOException {
		BufferedWriter writerA = new BufferedWriter(new OutputStreamWriter(connectA.getOutputStream()));
		writeTo(writerA, "Round " + roundsPlayed + ", Player A: " + playerAScore + ", Player B: " + playerBScore + '\n');

		BufferedWriter writerB = new BufferedWriter(new OutputStreamWriter(connectB.getOutputStream()));
		writeTo(writerB, "Round " + roundsPlayed + ", Player A: " + playerAScore + ", Player B: " + playerBScore + '\n');
	}

	@Override
	public void printWinner(String winner) throws IOException {
		BufferedWriter writerA = new BufferedWriter(new OutputStreamWriter(connectA.getOutputStream()));
		writeTo(writerA, winner + '\n');
		
		BufferedWriter writerB = new BufferedWriter(new OutputStreamWriter(connectB.getOutputStream()));
		writeTo(writerB, winner + '\n');
	}
	
	private void writeTo(BufferedWriter writer, String message) throws IOException{
		writer.write(message);
		writer.flush();
	}
}
