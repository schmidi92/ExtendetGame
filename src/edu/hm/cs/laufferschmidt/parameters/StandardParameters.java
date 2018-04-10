package edu.hm.cs.laufferschmidt.parameters;

import edu.hm.cs.laufferschmidt.Parameter;

public class StandardParameters implements Parameter {

	
	private final int SCORETOWIN = 40;
	private final int MAXCHOICE = 5;
	private final int MINCHOICE = 1;
	
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
