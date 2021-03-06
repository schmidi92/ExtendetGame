package edu.hm.lauffer;

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
	 * @param playerA ist es playerA
	 * @param playerChoice moegliche auswahl des Spielers
	 * @throws IOException Einlesen nicht moeglich
	 */
	int getNumber(boolean playerA, String playerChoice) throws IOException;
	
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
	
	/**
	 * Erstellt und startet runde.
	 * @return int[] arry
	 * @param playerAChoice was playerA eintippen darf
	 * @param playerBChoice was playerB einttippen darf
	 * @throws IOException weil halt.
	 */
	default int[] runAll(String playerAChoice, String playerBChoice) throws IOException{
		final int[] result = new int[2];
		result[0] = getNumber(true, playerAChoice);
		result[1] = getNumber(false, playerBChoice);
		return result;
	}
}
