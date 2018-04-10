package edu.hm.cs.laufferschmidt;

/**
 * Klasse die das Regelset des Spiels verwaltet.
 * 
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public interface Rule {
	
	/**
	 * Wertet das Rundenergebnis aus.
	 * Wer hat gewonnen, unentschieden.
	 * 
	 * @param playerAChoice Zahl die Spieler A gewaehlt hat
	 * @param playerBChoice Zahl die Spieler B gewaehlt ha
	 * @return Index 0 Punkte die Spieler A gesammelt hat, Index 1 Punkte die Spieler B gesammelt hat
	 */
	int[] evaluateScores(int playerAChoice,int playerBChoice);
	
	/**
	 * Prueft ob Spiel beendet, ob ein Spieler die noetige Punktzahl zum gewinnen des Spiels erreicht hat.
	 * 
	 * @param playerAScore Punktestand Spieler A
	 * @param playerBScore Punktestand Spieler B
	 * @param para Parameter obejekt zur abfrage der noetigen Punkte
	 * @return true spiel lauft weiter, false spiel beendet spieler hat max Punktzahl erreicht
	 */
	boolean gameStillrunning(int playerAScore, int playerBScore, Parameter para );
	
	
	/**
	 * Wertet aus, welcher Spieler das Spiel gewonnen hat.
	 * 
	 * @param playerAScore Punktestand Spieler A
	 * @param playerBScore Punktestand Spieler B
	 * @return Welcher Spieler gewonnen hat, oder unentschieden
	 */
	String determineWinner(int playerAScore, int playerBScore);
}
