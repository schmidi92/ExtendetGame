package edu.hm.lauffer.dialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.hm.lauffer.Dialog;

/**
 * Klasse die die Kommunikation mit den Spielern verwaltet. Dateneingabe und
 * Ausgabe findet ueber Konsole statt.
 * 
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public class StandardDialog implements Dialog{
	
	/**
	 * Default fuer char konvertierung.
	 */
	private static final String CHARSET = "US-ASCII";
	
	@Override
	public int getNumber(boolean playerA, String playerChoices) throws IOException{
		//read liest zahl und enter als 2 unterschiedliche Eingaben
		// enter ('/n') muss ubersprungen werden
		if(playerA) {
			askForNumber("a", playerChoices);
		} else {
			askForNumber("b", playerChoices);
		}
		final BufferedReader input = new BufferedReader(new InputStreamReader(System.in, CHARSET));
		final String line=input.readLine();
		final int result;
		if(line == null){
			throw new IOException();
		} else {
			result = line.charAt(0);
		}
		return result - '0';
		
	}
	@Override
	public void askForNumber(String player,String playerChoices){
		System.out.printf("Player "+player.toUpperCase() +", your choice " + playerChoices+"%n");
	}
	@Override
	public void endOfRound(int roundsPlayed, int playerAScore, int playerBScore){
		System.out.printf("Round %d, Player A: %d, Player B: %d%n", roundsPlayed, playerAScore, playerBScore);
	}
	@Override
	public void printWinner(String winner){
		System.out.println(winner);
	}
}
