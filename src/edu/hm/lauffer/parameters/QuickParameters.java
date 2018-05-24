package edu.hm.lauffer.parameters;

import edu.hm.lauffer.Parameter;

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
	public boolean isValidNumber(int number,boolean playerB) {
		return isValidNumber(number);
	}
	
	@Override
	public int getMaxChoice() {
		return MAXCHOICE;
	}


	@Override
	public boolean isValidNumber(int number) {
		return number >= getMinChoice() && number <= this.getMaxChoice();
	}


	@Override
	public int getMinChoice() {
		return MINCHOICE;
	}
	
	@Override
	public String toString() {
		return getMinChoice() + " - " + getMaxChoice();
	}
	@Override
	public String toString(boolean playerB) {
		return toString();
	}
}
