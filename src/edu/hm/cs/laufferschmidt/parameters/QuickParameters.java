package edu.hm.cs.laufferschmidt.parameters;

import edu.hm.cs.laufferschmidt.Parameter;

public class QuickParameters implements Parameter {

	private final int SCORETOWIN = 12;
	private final int MAXCHOICE = 3;
	private final int MINCHOICE = 1;

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
		System.out.println("Valid:" + (number >= getMinChoice() && number <= this.getMaxChoice()));
		return number >= getMinChoice() && number <= this.getMaxChoice();
	}

	@Override
	public String toString() {
		return getMinChoice() + " - " + getMaxChoice();
	}
}
