package edu.hm.cs.lauffer.dialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import edu.hm.cs.lauffer.Dialog;
import edu.hm.cs.lauffer.Parameter;

/**
 * Klasse die die Kommunikation mit den Spielern verwaltet. Dateneingabe und
 * Ausgabe findet ueber ein Textfile statt.
 * 
 * @author Markus Schmidt und Jonas Lauffer
 *o
 */
public class FileDialog implements Dialog {


	/**
	 * Reader um textfile auszulesen.
	 */
	private final BufferedReader reader;
	/**
	 * writer um ins Textfile zu schreien.
	 */
	private final BufferedWriter writer;

	/**
	 * Konstruktor. Initialisiert reader und writer
	 * 
	 * @throws IOException
	 *             File konnte nicht gefunden werden
	 */
	public FileDialog() throws IOException {
		
		reader = Files.newBufferedReader(Paths.get(System.getProperty("java.io.tmpdir") + "undercut.in.txt"));
		writer = Files.newBufferedWriter(Paths.get(System.getProperty("java.io.tmpdir") + "undercut.out.txt"));
	}

	@Override
	public int getNumber() throws IOException {

		final int number;
		try {
			number = reader.read()-'0';
			System.out.println("Number read: " + number);
		} catch (NumberFormatException file) {
			throw new IllegalArgumentException("zu wenig Befehle im TextFile");
		}

		System.out.println(number);
		return number;

	}

	@Override
	public void askForNumber(String player, Parameter para) throws IOException {
		writer.write("Player " + player.toUpperCase() + ", your choice " + para.toString() + '\r'+'\n');
		writer.flush();
	}

	@Override
	public void endOfRound(int roundsPlayed, int playerAScore, int playerBScore) throws IOException {
		writer.write("Round " + roundsPlayed + ", Player A: " + playerAScore + ", Player B: " + playerBScore + '\r'+'\n');
		writer.flush();
	}

	@Override
	public void printWinner(String winner) throws IOException {
		writer.write(winner + '\n');
		writer.flush();
	}
}
