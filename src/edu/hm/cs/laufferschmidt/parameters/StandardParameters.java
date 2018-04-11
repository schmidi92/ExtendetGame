package edu.hm.cs.laufferschmidt.parameters;

import edu.hm.cs.laufferschmidt.Parameter;

/**
 * Parameterklasse.
 * Parameter fuer ein standard Spiel.
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public class StandardParameters implements Parameter {

	/**
	 * Punktezahl, bei der das Spiel gewonnen ist.
	 */
	private final int scoreToWin = 40;
	/**
	 * Maximaler Wert den ein Spieler auswaehlen kann.
	 */
	private final int maxChoice = 5;
	/**
	 * Minimaler Wert den ein Spieler auswaehlen kann.
	 */
	private final int minChoice = 1;
	
	@Override
	public int getScoreToWin() {
		return scoreToWin;
	}

	@Override
	public int getMaxChoice() {
		return maxChoice;
	}

	public int getMinChoice() {
		return minChoice;
	}

	@Override
	public boolean isValidNumber(int number) {
		return number >= getMinChoice() && number <= this.getMaxChoice();
	}

	@Override
	public String toString() {
		return getMinChoice() + " - " + getMaxChoice();
	}
}
