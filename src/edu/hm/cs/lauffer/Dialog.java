package edu.hm.cs.lauffer;

import java.io.IOException;

/**
 * Interface fuer Klassen die die Kommunikation mit den Spielern verwaltet. 
 * 
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public interface Dialog {
	/**
	 * Wartet auf eingabe durch den Benutzer.
	 * Liest Eingabe ein und returnd den Wert.
	 * @return int Wert den Benutzer eingegeben hat
	 * @throws IOException Einlesen nicht moeglich
	 */
	int getNumber() throws IOException;
	
	/**
	 * Fordert den Spieler auf eine Eingabe zu machen. 
	 * Mit welcher Zahl der Benutzer die Runde spielen moechte.
	 * 
	 * @param spieler Spieler a oder b
	 * @param playerChoices string Auswahl die an den Spiler gesendet wird
	 * @throws IOException Exception
	 */
	void askForNumber(String spieler,String playerChoices) throws IOException;
	
	/**
	 * Zeigt beiden Spielern an, dass die Runde zu ende ist.
	 * Sendet Info an beide Spieler, mit der akutellen Rundenzahl und Punktestatus
	 * @param roundsPlayed Welche runde wurde soeben bendet
	 * @param playerAScore Punkte von Spieler A
	 * @param playerBScore Punkte von Spieler B
	 * @throws IOException Exception
	 */
	void endOfRound(int roundsPlayed, int playerAScore, int playerBScore) throws IOException;
	
	/**
	 * Zeigt beiden Spielern an, dass die Runde zu Ende ist.
	 * Sende Nachricht wer gewonnen bzw. unentschieden gespielt wurde.
	 * 
	 * @param winner Wer das Spiel gewonnen hat
	 * @throws IOException 
	 */
	void printWinner(String winner) throws IOException;
}
