package edu.hm.cs.laufferschmidt.parameters;

import edu.hm.cs.laufferschmidt.Parameter;

/**
 * Parameterklasse. Parameter varieren von Runde zu Runde.
 * 
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public class ChaosParameters implements Parameter {

	/**
	 * Punktezahl, bei der das Spiel gewonnen ist.
	 */
	private static final int SCORETOWIN = 42;

	/**
	 * Anzahl der unterschiedlichen Parameter.
	 */
	private static final int PARACOUNT = 3;

	/**
	 * Array mit den unterschiedlichen Parametern.
	 */
	private final int[][] para = { { 1, 4 }, { 2, 5 }, { 1, 3, 5 } };

	/**
	 * Hilfsattribut um die Parameter rotieren zu lassen.
	 */
	private int instableCount;
	/**
	 * Boolean welcher Spieler an der Reihe ist.
	 */
	private boolean playerB = false;

	@Override
	public int getScoreToWin() {
		return SCORETOWIN;
	}

	@Override
	public int getMaxChoice() {
		final int round=playerPosition();
		return para[round % PARACOUNT][para[round % PARACOUNT].length - 1];
	}

	@Override
	public int getMinChoice() {
		final int round=playerPosition();
		return para[round % PARACOUNT][0];
	}

	@Override
	public boolean isValidNumber(int number) {
		final int round=playerPosition();
		boolean isValid = false; // bleibt in der Schleife
		if (round % PARACOUNT == 2) {
			isValid = number == para[2][0] || number == para[2][1] || number == para[2][2];
		} else {
			isValid = number >= getMinChoice() && number <= getMaxChoice();
		}
		if (isValid && playerB) {
			++instableCount;
		}
		if(isValid){
			playerB = !playerB;
		}
		
		return isValid;
	}

	@Override
	public String toString() {
		final int round=playerPosition();
		final String resultString;
		if (round % PARACOUNT == 2) {
			resultString = para[2][0] + " / " + para[2][1] + " / " + para[2][2];
		} else {
			resultString = getMinChoice() + " - " + getMaxChoice();
		}
		return resultString;

	}
	/**
	 * gibt die Position des Spielers im Array zurueck.
	 * @return position
	 */
	private int playerPosition(){
		int result=instableCount;
		if(playerB){
			result++;
		}
		return result;
	}
}
