package edu.hm.cs.laufferschmidt;

import java.io.IOException;

public interface Dialog {
	/**
	 * Wartet auf eingabe durch den Benutzer.
	 * Liest Eingabe ein und returnd den Wert.
	 * @return int Wert den Benutzer eingegeben hat
	 * @throws IOException
	 */
	int getNumber() throws IOException;
	
	/**
	 * Fordert den Benutzer auf eine Eingabe zu machen. 
	 * Mit welcher Zahl der Benutzer die Runde spielen moechte.
	 * 
	 * @param s Benutzer a oder b
	 * @param para Parameterobjekt
	 * @throws IOException
	 */
	void askForNumber(String s,Parameter para) throws IOException;
	
	/**
	 * Zeigt beiden Spielern an, dass die Runde zu ende ist.
	 * Sendet Info an beide Spieler, mit der akutellen Rundenzahl und Punktestatus
	 * @param roundsPlayed Welche runde wurde soeben bendet
	 * @param playerAScore Punkte von Spieler A
	 * @param playerBScore Punkte von Spieler B
	 * @throws IOException
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
