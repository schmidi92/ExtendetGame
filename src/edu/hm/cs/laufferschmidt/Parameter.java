package edu.hm.cs.laufferschmidt;

/**
 * Klasse die die Parameter des Spiels verwwaltet.
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public interface Parameter {
	
	/**
	 * Liefert den zu erreichenden Punktestand zurueck, wann das Spiel gewonnen ist.
	 * @return zu erreidenden Punktestand. 
	 */
	int getScoreToWin();
	
	/**
	 * Liefert den maximalen Eingabewert zurueck.
	 * @return max Eingabewert
	 */
	int getMaxChoice();
	
	/**
	 * Liefert den minimalen Eingabewert zurueck.
	 * @return min Eingabewert
	 */
	int getMinChoice();
	
	/**
	 * Liefert einen String mit den attributen der Klasse zurueck.
	 * @return Klassenattribute in String verpackt
	 */
	String toString();
	
	/**
	 * Prueft ob Benutzereingabe innerhalb des gueltigen Bereichs liegt.
	 * 
	 * @param number die zu pruefenden Nummer.
	 * @return true Nummer gueltig, false Nummer ausserhalb des gueltigen Bereichs
	 */
	boolean isValidNumber(int number);
}
