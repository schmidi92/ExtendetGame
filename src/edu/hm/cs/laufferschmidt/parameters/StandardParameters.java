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
	private static final int SCORETOWIN = 40;
	/**
	 * Maximaler Wert den ein Spieler auswaehlen kann.
	 */
	private static final int MAXCHOICE = 5;
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
