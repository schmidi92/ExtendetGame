package edu.hm.cs.lauffer.parameters;

import edu.hm.cs.lauffer.Parameter;

/**
 * Parameterklasse.
 * Parameter fuer ein schnelles Spiel.
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public class QuickParameters implements Parameter {

	/**
	 * Punktezahl, bei der das Spiel gewonnen ist.
	 */
	private static final int SCORETOWIN = 12;
	/**
	 * Maximaler Wert den ein Spieler auswaehlen kann.
	 */
	private static final int MAXCHOICE = 3;
	/**
	 * Minimaler Wert den ein Spieler auswaehlen kann.
	 */
	private static final int MINCHOICE = 1;

	@Override
	public int getScoreToWin() {
		return SCORETOWIN;
	}

	@Override
	public int getMaxChoice() {
		return MAXCHOICE;
	}

	@Override
	public int getMinChoice() {
		return MINCHOICE;
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
